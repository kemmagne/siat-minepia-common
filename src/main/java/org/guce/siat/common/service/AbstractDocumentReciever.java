package org.guce.siat.common.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.guce.siat.common.dao.FileDao;
import org.guce.siat.common.dao.FileItemDao;
import org.guce.siat.common.dao.ItemFlowDao;
import org.guce.siat.common.mail.MailConstants;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.ItemFlow;
import org.guce.siat.common.utils.XmlXPathUtils;
import org.guce.siat.common.utils.enums.AperakType;
import org.guce.siat.common.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public abstract class AbstractDocumentReciever implements DocumentReciever {

    /**
     * The Constant NEGATIVE_APERAK_MAIL.
     */
    private static final String NEGATIVE_APERAK_MAIL = "negatifAperakReceived.vm";

    /**
     * The file dao.
     */
    @Autowired
    private FileDao fileDao;

    /**
     * The item flow dao.
     */
    @Autowired
    private ItemFlowDao itemFlowDao;
    /**
     * The file item.
     */
    @Autowired
    private FileItemDao fileItemDao;

    /**
     * The mail service.
     */
    @Autowired
    private MailService mailService;

    @Override
    public abstract Map<String, Object> uploadEbxmlFile(Map<String, Object> ebxmlBytes) throws ValidationException, ParseException, TransformerException, SOAPException, SAXException, ParserConfigurationException, JAXBException, XPathExpressionException;

    @Override
    public abstract Map<String, Object> generateAperakCFile(Map<String, Object> ebxmlBytes, String errorMessage) throws Exception;

    @Override
    public boolean processReceivedAperak(Element rootElement) {
        final String documentTypeExpression = "/DOCUMENT/TYPE_DOCUMENT";

        final String referenceSiatExpression = "/DOCUMENT/REFERENCE_DOSSIER/REFERENCE_SIAT";

        final String documentType = XmlXPathUtils.findSingleValue(documentTypeExpression, rootElement);
        final String referenceSiat = XmlXPathUtils.findSingleValue(referenceSiatExpression, rootElement);
        if (org.apache.commons.lang.StringUtils.isNotBlank(documentType) && org.apache.commons.lang.StringUtils.isNotBlank(referenceSiat)) {
            final org.guce.siat.common.model.File siatFile = fileDao.findByRefSiat(referenceSiat);
            final List<FileItem> fileItems = siatFile.getFileItemsList();

            if (AperakType.APERAK_C.name().equals(documentType) || AperakType.APERAK_J.name().equals(documentType)) {
                //rollback the last decision
                String senderMail = null;
                for (final FileItem fileItem : fileItems) {
                    final ItemFlow itemflow = itemFlowDao.findLastOutgoingItemFlowByFileItem(fileItem);
                    senderMail = itemflow.getSender().getEmail();
                    itemflow.setReceived(AperakType.APERAK_C.getCharCode());
                    itemflow.setSent(Boolean.FALSE);
                    itemFlowDao.update(itemflow);

                    fileItem.setDraft(Boolean.TRUE);
                    fileItem.setStep(itemflow.getFlow().getFromStep());
                    fileItemDao.update(fileItem);
                }
                final String templateFileName = NEGATIVE_APERAK_MAIL;
                final Map<String, String> map = new HashMap<>();
                map.put(MailConstants.SUBJECT, "SIAT : Dossier non envoyé");
                map.put(MailConstants.FROM, mailService.getFromValue());
                map.put(MailConstants.EMAIL, senderMail);
                map.put("refSiat", siatFile.getReferenceSiat());
                map.put(MailConstants.VMF, templateFileName);
                mailService.sendMail(map);
                return true;
            } else if (AperakType.APERAK_D.name().equals(documentType) || AperakType.APERAK_F.name().equals(documentType)) {
                for (final FileItem fileItem : fileItems) {
                    final ItemFlow itemFlow = itemFlowDao.findLastOutgoingItemFlowByFileItem(fileItem);
                    if (itemFlow != null) {
                        itemFlow.setReceived(AperakType.APERAK_D.getCharCode());
                        itemFlowDao.update(itemFlow);
                    }
                }
                return true;
            }
        }
        return false;
    }

}
