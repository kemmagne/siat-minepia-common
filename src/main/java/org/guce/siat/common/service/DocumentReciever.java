package org.guce.siat.common.service;

import java.text.ParseException;
import java.util.Map;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.guce.siat.common.utils.exception.ValidationException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author tadzotsa
 */
public interface DocumentReciever {

    /**
     * Upload ebxml file.
     *
     * @param ebxmlBytes the ebxml bytes
     * @return the map
     * @throws ValidationException the validation exception
     * @throws ParseException the parse exception
     * @throws TransformerException the transformer exception
     * @throws SOAPException the SOAP exception
     * @throws SAXException the SAX exception
     * @throws ParserConfigurationException the parser configuration exception
     * @throws JAXBException the JAXB exception
     * @throws XPathExpressionException the x path expression exception
     */
    Map<String, Object> uploadEbxmlFile(final Map<String, Object> ebxmlBytes) throws ValidationException, ParseException,
            TransformerException, SOAPException, SAXException, ParserConfigurationException, JAXBException, XPathExpressionException;

    /**
     * Generate aperak c file.
     *
     * @param ebxmlBytes the ebxml bytes
     * @param errorMessage the error message
     * @return the map
     * @throws Exception the exception
     */
    Map<String, Object> generateAperakCFile(final Map<String, Object> ebxmlBytes, final String errorMessage) throws Exception;

    /**
     *
     * @param rootElement
     * @return
     */
    boolean processReceivedAperak(Element rootElement);

}
