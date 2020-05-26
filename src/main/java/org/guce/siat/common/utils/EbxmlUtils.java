package org.guce.siat.common.utils;

import hk.hku.cecid.ebms.pkg.EbxmlMessage;
import hk.hku.cecid.piazza.commons.util.UtilitiesException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.guce.orchestra.core.OrchestraEbxmlMessage;
import org.guce.orchestra.dao.util.Generator;
import org.guce.orchestra.util.CalendarUtility;
import org.guce.siat.common.utils.ebms.ESBConstants;
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

        map.put(ESBConstants.CONVERSATION_ID, ebxmlMessage.getConversationId());

        final Map<String, byte[]> attachedByteFiles = new HashMap<>();

        while (iterator.hasNext()) {
            attachmentPart = (AttachmentPart) iterator.next();
            String attachmentName = attachmentPart.getContentId().trim();
            attachmentName = attachmentName.replaceAll("<|>", "");
            is = attachmentPart.getDataHandler().getInputStream();
            final byte[] attachmentBytes = IOUtils.toByteArray(is);
            attachedByteFiles.put(attachmentName, attachmentBytes);
        }
        if (!CollectionUtils.isEmpty(attachedByteFiles)) {
            map.put(ESBConstants.ATTACHMENT, attachedByteFiles);
        }

        return map;
    }

    public static OrchestraEbxmlMessage mapToEbxml(Map<String, Object> map) throws SOAPException, IOException, UtilitiesException {

        final byte[] xmlBytes = (byte[]) map.get(ESBConstants.FLOW);
        final Map<String, byte[]> attachments = (Map<String, byte[]>) map.get(ESBConstants.ATTACHMENT);
        final byte[] refMsg = (byte[]) map.get(ESBConstants.MESSAGE);

        EbxmlMessage refEbxmlMsg = (refMsg == null) ? null : getEbxmlMessageFromBytes(refMsg);

        final String service = SiatUtils.getValueFromXml(xmlBytes, "/DOCUMENT/REFERENCE_DOSSIER/SERVICE");
        final String action = SiatUtils.getValueFromXml(xmlBytes, "/DOCUMENT/TYPE_DOCUMENT");
        final String conversationId = SiatUtils.getValueFromXml(xmlBytes, "/DOCUMENT/REFERENCE_DOSSIER/REFERENCE_GUCE");
        final String fromPartyId = SiatUtils.getValueFromXml(xmlBytes, "/DOCUMENT/ROUTAGE/EMETTEUR");
        final String toPartyId = SiatUtils.getValueFromXml(xmlBytes, "/DOCUMENT/ROUTAGE/DESTINATAIRE");
        final String messageId = Generator.generateMessageID();

        OrchestraEbxmlMessage ebxml = new OrchestraEbxmlMessage(fromPartyId, toPartyId, conversationId, service, action, messageId);
        ebxml.getMessageHeader().setServiceType("OCS");
        ebxml.addAckRequested(true);
        if (refEbxmlMsg != null) {
            final String refMessageId = refEbxmlMsg.getMessageId();
            ebxml.getMessageHeader().setRefToMessageId(refMessageId);
            EbxmlMessage ms = new EbxmlMessage();
            ms.addMessageHeader();
            ms.getMessageHeader().setMessageId(refMessageId);
            ms.addAckRequested(true);
            String time = CalendarUtility.date2UTC(Calendar.getInstance().getTime(), Calendar.getInstance().getTimeZone());
            ebxml.addAcknowledgment(time, ms);
        }
        DataHandler dh = new DataHandler(new ByteArrayDataSource(xmlBytes, MediaType.APPLICATION_XML_VALUE));
        ebxml.addPayloadContainer(dh, ebxml.getAction(), ebxml.getDescription());

        if (MapUtils.isNotEmpty(attachments)) {
            for (Map.Entry<String, byte[]> attachment : attachments.entrySet()) {
                String attachmentName = attachment.getKey();
                byte[] attachmentBytes = attachment.getValue();
                dh = new DataHandler(new ByteArrayDataSource(attachmentBytes, MediaType.APPLICATION_OCTET_STREAM_VALUE));
                ebxml.addPayloadContainer(dh, attachmentName, attachmentName);
            }
        }

        return ebxml;
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
        try ( ByteArrayOutputStream ebxmlMessageByteStream = new ByteArrayOutputStream()) {
            ebxmlMessage.getSOAPMessage().writeTo(ebxmlMessageByteStream);
            ebxmlMessage.setBytes(ebxmlMessageByteStream.toByteArray());
        }
    }

    private EbxmlUtils() {
    }

}
