package org.guce.epayment.core.util.parser;

import java.lang.reflect.Constructor;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParserFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(Logger.class);

    public static <T> Optional<Parser> getParser(String format, Class<T> entityClass) {
        try {
            String implementationClassName = Parser.class.getPackage().getName() + "." + format.substring(0, 1).toUpperCase() + format.substring(1) + Parser.class.getSimpleName();
            Class implementationClass = Class.forName(implementationClassName);
            Constructor constructor = implementationClass.getConstructor(Class.class);
            return Optional.of(Parser.class.cast(constructor.newInstance(entityClass)));
        } catch (ClassNotFoundException ex) {
            LOGGER.error(null, ex);
        } catch (Exception ex) {
            LOGGER.error(null, ex);
        }
        return Optional.empty();
    }

}
