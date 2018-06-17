package org.guce.epayment.core.util.parser;

import javax.xml.bind.JAXBException;
import org.guce.util.JAXBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlParser<T> extends AbstractParser<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlParser.class);

    public XmlParser(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public T parse(String xml) {

        try {
            return JAXBUtil.unmarshall(xml, documentClass);
        } catch (JAXBException ex) {
            LOGGER.error(null, ex);
            return null;
        }
    }

}
