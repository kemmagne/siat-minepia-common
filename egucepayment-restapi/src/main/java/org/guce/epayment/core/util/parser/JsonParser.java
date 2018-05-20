package org.guce.epayment.core.util.parser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.guce.epayment.core.utils.CoreUtils;

public class JsonParser<T> extends AbstractParser<T> {

    public JsonParser(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public T parse(String json) {
        try {
            return documentClass.cast(CoreUtils.getObjectMapper().readValue(json, documentClass));
        } catch (IOException ex) {
            Logger.getLogger(JsonParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
