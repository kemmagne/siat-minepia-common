package org.guce.siat.common.utils.ged;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.chemistry.opencmis.client.api.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class CimisSession.
 */
public final class CmisSession {

    private static final Logger LOG = LoggerFactory.getLogger(CmisSession.class);

    /**
     * The instance.
     */
    private static Session instance;

    /**
     * Instantiates a new cimis session. Signleton must hide the public
     * Constructor
     */
    private CmisSession() {
        final CmisClient cmisClient = new CmisClient();
        final Properties param = getConnectionParameters();
        instance = cmisClient.getSession(param.getProperty("connectionName"), param.getProperty("userNameRepo"),
                param.getProperty("pwdRepo"), param.getProperty("ipRepo") + param.getProperty("urlAtomRepo"),
                param.getProperty("idRepo"));
    }

    /**
     * Gets the single instance of CimisSession.
     *
     * @return single instance of CimisSession
     */
    public static synchronized Session getInstance() {
        if (instance == null) {
            new CmisSession();
        }
        return instance;
    }

    private Properties getConnectionParameters() {

        final Properties prop = new Properties();
        final String filename = "global-config.properties";
        try (InputStream input = CmisSession.class.getClassLoader().getResourceAsStream(filename)) {

            if (input == null) {
                LOG.error("Sorry, unable to find {}", filename);
                return null;
            }

            prop.load(input);

        } catch (final IOException ex) {
            LOG.error(ex.getMessage(), ex);
        }

        return prop;

    }
}

