package org.guce.siat.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *
 * @author ht
 */
@PropertySource("classpath:global-config.properties")
@Component("propertiesLoader")
public class PropertiesLoader {

    /**
     * The global config file path
     */
    @Value("${global-config.properties}")
    private String globalConfigFilePath;

    private Properties properties;

    /**
     * Make some stufs just after instance creation
     *
     * @throws IOException
     */
    @PostConstruct
    public void init() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(globalConfigFilePath));
    }

    /**
     * Get the value for the property key param. The value is loaded from the
     * properties which are loaded from the global config file
     *
     * @param propertyKey
     *
     * @return the value for the given property key
     */
    public String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey);
    }

}
