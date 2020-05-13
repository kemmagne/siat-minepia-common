package org.guce.siat.common.mail.jms;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

/**
 * The Class JmsMailMessageCreatorImpl.
 */
public class JmsMailMessageCreatorImpl implements MessageCreator {

    /**
     * The map.
     */
    private final Map<String, String> map;

    /**
     * Instantiates a new jms mail message creator impl.
     *
     * @param map the map
     */
    public JmsMailMessageCreatorImpl(final Map<String, String> map) {
        super();
        this.map = map;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.jms.core.MessageCreator#createMessage(javax.jms.Session)
     */
    @Override
    public Message createMessage(final Session session) throws JMSException {
        final MapMessage message = session.createMapMessage();
        final Set<String> set = map.keySet();
        for (@SuppressWarnings("rawtypes")
                final Iterator iterator = set.iterator(); iterator.hasNext();) {
            final String key = (String) iterator.next();
            message.setString(key, map.get(key));
        }

        return message;
    }

}

