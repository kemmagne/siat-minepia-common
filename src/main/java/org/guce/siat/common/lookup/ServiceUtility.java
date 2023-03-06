package org.guce.siat.common.lookup;

import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

/**
 *
 * @author tadzotsa
 */
public final class ServiceUtility {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceUtility.class);

    private static Properties PROPERTIES;

    private static final AbstractServiceLocator SERVICE_LOCATOR = AbstractServiceLocator.getInstance(getJndiProperties());

    public static <B> B getBean(Class<B> clazz) {
        B service = null;
        try {
            service = ApplicationContextHolder.getApplicationContext().getBean(clazz);
        } catch (BeansException e) {
            LOG.error(e.getMessage(), e);
        }
        return service;
    }

    private static Properties getJndiProperties() {
        if (PROPERTIES == null) {
            PROPERTIES = new Properties();
        }
        return PROPERTIES;
    }

}
