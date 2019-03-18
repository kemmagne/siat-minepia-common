package org.guce.siat.common.mail.jms;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * The Class JmsMailMessageProducer.
 */
@Component
public class JmsMailMessageProducer {

    /**
     * The template.
     */
    @Autowired
    private JmsTemplate template = null;

    /**
     * Send mail.
     *
     * @param map the map
     * @throws JmsException the jms exception
     */
    public void sendMail(final Map<String, String> map) throws JmsException {
        template.send("org.guce.siat.mail", new JmsMailMessageCreatorImpl(map));
    }

    /**
     * @return the template
     */
    public JmsTemplate getTemplate() {
        return template;
    }

    /**
     * @param template the template to set
     */
    public void setTemplate(final JmsTemplate template) {
        this.template = template;
    }

}

