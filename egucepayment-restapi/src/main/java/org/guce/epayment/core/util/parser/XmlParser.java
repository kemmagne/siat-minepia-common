package org.guce.epayment.core.util.parser;

import java.io.StringReader;
import javax.xml.bind.JAXB;

public class XmlParser<T> extends AbstractParser<T> {

    public XmlParser(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public T parse(String xml) {
        return documentClass.cast(JAXB.unmarshal(new StringReader(xml), documentClass));
    }

}
