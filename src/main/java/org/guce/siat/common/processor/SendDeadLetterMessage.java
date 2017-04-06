package org.guce.siat.common.processor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang.StringUtils;
import org.guce.siat.common.mail.MailConstants;
import org.guce.siat.common.service.MailService;
import org.guce.siat.common.utils.XmlXPathUtils;
import org.guce.siat.common.utils.ebms.ESBConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * The Class SendDeadLetterMessage.
 */
public class SendDeadLetterMessage implements Processor {

	/**
	 * The Constant LOG.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SendDeadLetterMessage.class);

	/**
	 * The Constant EMAIL_BODY_PASSWORD.
	 */
	private static final String EMAIL_BODY_ADMINISTRATOR = "emailBodyAdministrator.vm";

	/**
	 * The mail service.
	 */
	@Autowired
	private MailService mailService;


	/*
	 * (non-Javadoc)
	 *
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void process(final Exchange exchange) throws Exception {
		final Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		final HashMap<String, Object> ebxml = (exchange.getIn().getBody() != null ? (HashMap<String, Object>) exchange.getIn()
				.getBody() : null);
		LOG.error("### is there an exception ? {}", (cause != null ? "true" : "false"));
		LOG.error("###SendDeadLetterMessage Error message : ", cause);

		final String referenceSiat = retrieveReferenceSiat(ebxml);
		final String display = StringUtils.isNotBlank(referenceSiat) ? "inline" : "none";
		ebxml.put(ESBConstants.DEAD, "1");
		exchange.getOut().setBody(ebxml);
		LOG.error("### exchange Id : {}", exchange.getExchangeId());
		final String templateFileName = EMAIL_BODY_ADMINISTRATOR;
		final Map<String, String> map = new HashMap<String, String>();
		map.put(MailConstants.SUBJECT, "SIAT : Problème Technique");
		map.put(MailConstants.FROM, mailService.getFromValue());
		map.put(MailConstants.EMAIL, mailService.getReplyToValue());
		map.put("exception", Objects.toString(cause));
		map.put("message_id", exchange.getOut().getMessageId());
		map.put("referenceSiat", referenceSiat);
		map.put("display", display);
		map.put(MailConstants.VMF, templateFileName);
		mailService.sendMail(map);

	}

	/**
	 * Retrieve reference siat.
	 *
	 * @param exchange the exchange
	 * @return the string
	 * @throws SOAPException the SOAP exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TransformerException the transformer exception
	 * @throws SAXException the SAX exception
	 * @throws ParserConfigurationException the parser configuration exception
	 */
	private String retrieveReferenceSiat(final HashMap<String, Object> ebxml) throws SOAPException, IOException,
			TransformerException, SAXException, ParserConfigurationException {
		final byte[] flow = (byte[]) ebxml.get(ESBConstants.FLOW);
		final String xmlContent = new String(flow, "ISO-8859-1");
		final Element rootElement = XmlXPathUtils.stringToXMLDOM(xmlContent).getDocumentElement();
		final String referenceSiat = XmlXPathUtils.findSingleValue("/DOCUMENT/REFERENCE_DOSSIER/REFERENCE_SIAT", rootElement);
		return referenceSiat;
	}

}
