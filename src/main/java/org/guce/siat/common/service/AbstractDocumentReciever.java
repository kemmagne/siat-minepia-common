package org.guce.siat.common.service;

import java.text.ParseException;
import java.util.Map;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.guce.siat.common.utils.exception.ValidationException;
import org.xml.sax.SAXException;

public abstract class AbstractDocumentReciever implements DocumentReciever {

    @Override
    public abstract Map<String, Object> uploadEbxmlFile(Map<String, Object> ebxmlBytes) throws ValidationException, ParseException, TransformerException, SOAPException, SAXException, ParserConfigurationException, JAXBException, XPathExpressionException;

    @Override
    public abstract Map<String, Object> generateAperakCFile(Map<String, Object> ebxmlBytes, String errorMessage) throws Exception;

}
