package org.guce.siat.utility.jaxb.common;

import java.util.Objects;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tadzotsa
 */
public final class PayDocJAXBContextCreator {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(PayDocJAXBContextCreator.class);

    /**
     * The instance.
     */
    private static JAXBContext instance;

    /**
     * Instantiates a new JAXB context creator.
     */
    private PayDocJAXBContextCreator() {
    }

    /**
     * Gets the single instance of JAXBContextCreator.
     *
     * @return single instance of JAXBContextCreator
     */
    public static synchronized JAXBContext getInstance() {
        try {
            if (instance == null) {
                instance = JAXBContext.newInstance(PaymentDocument.class);
            }
        } catch (final JAXBException e) {
            LOG.error(Objects.toString(e), e);
        }
        return instance;
    }

}
