package org.guce.siat.common.lookup;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tadzotsa
 */
public abstract class AbstractServiceLocator {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractServiceLocator.class);

    private static final Map<String, Object> CACHE = Collections.synchronizedMap(new HashMap<String, Object>());

    private static final String SERVICE_LOCATOR_CLASS_KEY = "siat.service.locator.class";

    public static AbstractServiceLocator getInstance(Properties prop) {
        String className = prop.getProperty(SERVICE_LOCATOR_CLASS_KEY, "org.guce.siat.common.lookup.SpringServiceLocator");
        Object res = null;
        try {
            Object cachedObj = CACHE.get(className);
            if (cachedObj == null) {
                cachedObj = Class.forName(className).newInstance();
                CACHE.put(className, cachedObj);
            }
            res = cachedObj;
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }

        return (AbstractServiceLocator) res;
    }

    public abstract Object lookup(String value) throws NamingException;

}
