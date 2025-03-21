package org.guce.siat.common.service.impl;

import javax.annotation.PostConstruct;
import org.guce.siat.common.service.ApplicationPropretiesService;
import org.guce.siat.common.utils.PropertiesConstants;
import org.guce.siat.common.utils.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class ApplicationPropretiesServiceImpl.
 */
@Service("applicationPropretiesService")
@Transactional(readOnly = true)
@PropertySources(value = {
    @PropertySource("classpath:global-config.properties")})
public class ApplicationPropretiesServiceImpl implements ApplicationPropretiesService {

    @Autowired
    private PropertiesLoader propertiesLoader;

    /**
     * The column separator.
     */
    @Value("${columnSeparator}")
    private String columnSeparator;

    /**
     * The column separator.
     */
    @Value("${customColumnSeparator}")
    private String customColumnSeparator;

    /**
     * The custom row separator.
     */
    @Value("${rowSeparator}")
    private String rowSeparator;

    /**
     * The custom column separator.
     */
    @Value("${customRowSeparator}")
    private String customRowSeparator;

    /**
     * The app env.
     */
    @Value("${application.environment}")
    private String appEnv;

    /**
     * The app version.
     */
    @Value("${application.version}")
    private String appVersion;

    /**
     * The repeatable separator.
     */
    @Value("${repeatableSeparator}")
    private String repeatableSeparator;

    /**
     * The attachement folder.
     */
    private String attachementFolder;

    @PostConstruct
    public void init() {
        attachementFolder = propertiesLoader.getProperty(PropertiesConstants.ATTACHMENT_FOLDER);
    }

    /**
     * Gets the column separator.
     *
     * @return the column separator
     */
    @Override
    public String getColumnSeparator() {
        return columnSeparator;
    }

    /**
     * Gets the row separator.
     *
     * @return the row separator
     */
    @Override
    public String getRowSeparator() {
        return rowSeparator;
    }

    /**
     * Gets the custom column separator.
     *
     * @return the custom column separator
     */
    @Override
    public String getCustomColumnSeparator() {
        return customColumnSeparator;
    }

    /**
     * Gets the custom row separator.
     *
     * @return the custom row separator
     */
    @Override
    public String getCustomRowSeparator() {
        return customRowSeparator;
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.guce.siat.common.service.ApplicationPropretiesService#getAppVersion()
     */
    @Override
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.guce.siat.common.service.ApplicationPropretiesService#getAppEnv()
     */
    @Override
    public String getAppEnv() {
        return appEnv;
    }

    /**
     * Gets the attachement folder.
     *
     * @return the attachementFolder
     */
    @Override
    public String getAttachementFolder() {
        return attachementFolder;
    }

    /**
     * Sets the attachement folder.
     *
     * @param attachementFolder the attachementFolder to set
     */
    public void setAttachementFolder(final String attachementFolder) {
        this.attachementFolder = attachementFolder;
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.guce.siat.common.service.ApplicationPropretiesService#getRepeatableSeparator()
     */
    @Override
    public String getRepeatableSeparator() {
        return repeatableSeparator;
    }

    @Override
    public PropertiesLoader getPropertiesLoader() {
        return propertiesLoader;
    }

}
