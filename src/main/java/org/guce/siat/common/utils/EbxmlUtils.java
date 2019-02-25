package org.guce.siat.common.utils;

import hk.hku.cecid.ebms.pkg.EbxmlMessage;
import hk.hku.cecid.ebms.pkg.EbxmlMessageFactory;
import hk.hku.cecid.ebms.pkg.MessageHeader;
import hk.hku.cecid.piazza.commons.activation.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.activation.DataHandler;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;
import org.apache.commons.io.IOUtils;
import org.guce.siat.common.utils.ebms.ESBConstants;
import org.guce.siat.common.utils.ebms.Generator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author tadzotsa
 */
public class EbxmlUtils {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(EbxmlUtils.class);

    public static Map<String, Object> ebxmlToMap(byte[] ebxml) throws SOAPException, IOException, TransformerException {

        Map<String, Object> map = new HashMap<>();

        map.put(ESBConstants.MESSAGE, ebxml);

        ByteArrayInputStream bis = new ByteArrayInputStream(ebxml);
        EbxmlMessage ebxmlMessage = new EbxmlMessage(bis);
        Iterator iterator = ebxmlMessage.getSOAPMessage().getAttachments();
        AttachmentPart attachmentPart = (AttachmentPart) iterator.next();
        InputStream is = attachmentPart.getDataHandler().getInputStream();
        byte[] xmlBytes = IOUtils.toByteArray(is);
        map.put(ESBConstants.FLOW, xmlBytes);

        final Map<String, byte[]> attachedByteFiles = new HashMap<>();

        while (iterator.hasNext()) {
            attachmentPart = (AttachmentPart) iterator.next();
            String attachmentName = attachmentPart.getContentId().trim();
            attachmentName = attachmentName.replace("<|>", "");
            is = attachmentPart.getDataHandler().getInputStream();
            final byte[] attachmentBytes = IOUtils.toByteArray(is);
            attachedByteFiles.put(attachmentName, attachmentBytes);
        }
        if (!CollectionUtils.isEmpty(attachedByteFiles)) {
            map.put(ESBConstants.ATTACHMENT, attachedByteFiles);
        }

        return map;
    }

    public static byte[] mapToEbxml(Map<String, Object> map) throws SOAPException, IOException {

        final byte[] xmlBytes = (byte[]) map.get(ESBConstants.FLOW);
        final Map<String, byte[]> attachments = (Map<String, byte[]>) map.get(ESBConstants.ATTACHMENT);
        final String service = (String) map.get(ESBConstants.SERVICE);
        final byte[] refMsg = (byte[]) map.get(ESBConstants.MESSAGE);
        final String ebxmlType = (String) map.get(ESBConstants.EBXML_TYPE);

        EbxmlMessage refEbxmlMsg = (refMsg == null) ? null : getEbxmlMessageFromBytes(refMsg);

        final String messageID = Generator.generateMessageID();

        try {
            final SOAPMessage message = EbxmlMessageFactory.newInstance().createMessage();
            AttachmentPart attachmentPart = message.createAttachmentPart();
            ByteArrayDataSource ds = new ByteArrayDataSource(xmlBytes);
            DataHandler dh = new DataHandler(ds);
            attachmentPart.setDataHandler(dh);
            attachmentPart.setContentType(MediaType.APPLICATION_XML_VALUE);
            final String action = SiatUtils.getValueFromXml(xmlBytes, "/DOCUMENT/TYPE_DOCUMENT");
            attachmentPart.addMimeHeader("Content-ID", action);

            for (Map.Entry<String, byte[]> attachment : attachments.entrySet()) {
                String attachmentName = attachment.getKey();
                byte[] attachmentBytes = attachment.getValue();
                attachmentPart = message.createAttachmentPart();
                ds = new ByteArrayDataSource(attachmentBytes);
                dh = new DataHandler(ds);
                attachmentPart.setDataHandler(dh);
                attachmentPart.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
                attachmentPart.addMimeHeader("Content-ID", attachmentName);
                message.addAttachmentPart(attachmentPart);
            }

            message.saveChanges();
            EbxmlMessage ebxmlMessage = new EbxmlMessage(message);

            final String conversationID = SiatUtils.getValueFromXml(xmlBytes, "/DOCUMENT/REFERENCE_DOSSIER/REFERENCE_GUCE");
            final String fromPartyId = SiatUtils.getValueFromXml(xmlBytes, "/DOCUMENT/ROUTAGE/EMETTEUR");
            final String toPartyId = SiatUtils.getValueFromXml(xmlBytes, "/DOCUMENT/ROUTAGE/DESTINATAIRE");

            final MessageHeader msgHeader = ebxmlMessage.addMessageHeader();
            msgHeader.addFromPartyId(fromPartyId);
            msgHeader.addToPartyId(toPartyId);
            ebxmlMessage.getMessageHeader().setMessageId(messageID);
            ebxmlMessage.getMessageHeader().setAction(action);
            ebxmlMessage.getMessageHeader().setConversationId(conversationID);
            ebxmlMessage.getMessageHeader().setCpaId("http://www.guichetunique-cameroun.org/cpa/ebxmlv2");
            ebxmlMessage.getMessageHeader().setService(service);
            ebxmlMessage.getMessageHeader().setServiceType("serviceType");
            ebxmlMessage.getMessageHeader().setRefToMessageId(refEbxmlMsg == null ? null : refEbxmlMsg.getMessageId());
            ebxmlMessage.addAckRequested(true);

            if (ESBConstants.APERAK.equals(ebxmlType)) {
                ebxmlMessage.addAcknowledgment(refEbxmlMsg == null ? null : refEbxmlMsg.getTimestamp(), refEbxmlMsg);
            }

            final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            msgHeader.setTimestamp(dateFormatter.format(new Date()));

            setEbxmlMessageByteStream(ebxmlMessage);

            return ebxmlMessage.getBytes();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }

        return null;
    }

    private static EbxmlMessage getEbxmlMessageFromBytes(final byte[] ebxmlBytes) throws SOAPException, IOException {
        EbxmlMessage ebxmlMessage = null;
        if (ebxmlBytes != null) {

            final ByteArrayInputStream bis = new ByteArrayInputStream(ebxmlBytes);

            ebxmlMessage = new EbxmlMessage(bis);
            setEbxmlMessageByteStream(ebxmlMessage);
        }
        return ebxmlMessage;

    }

    /**
     * Sets the ebxml message byte stream.
     *
     * @param ebxmlMessage the new ebxml message byte stream
     * @throws SOAPException the SOAP exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void setEbxmlMessageByteStream(final EbxmlMessage ebxmlMessage) throws SOAPException, IOException {
        try (ByteArrayOutputStream ebxmlMessageByteStream = new ByteArrayOutputStream()) {
            ebxmlMessage.getSOAPMessage().writeTo(ebxmlMessageByteStream);
            ebxmlMessage.setBytes(ebxmlMessageByteStream.toByteArray());
        }
    }

    private EbxmlUtils() {
    }

}
