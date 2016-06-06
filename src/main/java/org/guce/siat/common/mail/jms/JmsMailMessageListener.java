/***
 *
 */
package org.guce.siat.common.mail.jms;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.guce.siat.common.mail.MailConstants;
import org.guce.siat.common.mail.bo.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * The listener interface for receiving jmsMailMessage events. The class that is interested in processing a
 * jmsMailMessage event implements this interface, and the object created with that class is registered with a component
 * using the component's <code>addJmsMailMessageListener<code> method. When
 * the jmsMailMessage event occurs, that object's appropriate
 * method is invoked.
 */
@Component
public class JmsMailMessageListener implements MessageListener
{

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(JmsMailMessageListener.class);

	/** The counter. */
	@Autowired
	private AtomicInteger counter = null;

	/** The email sender service. */
	@Autowired
	private EmailSenderService emailSenderService;

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	public void onMessage(final Message message)
	{
		try
		{
			if (message instanceof MapMessage)
			{
				final MapMessage m = (MapMessage) message;

				final Map<String, Object> map = new HashMap<String, Object>();

				@SuppressWarnings("unchecked")
				final Enumeration<String> names = m.getMapNames();

				while (names.hasMoreElements())
				{
					final String name = names.nextElement();
					map.put(name, m.getString(name));
				}

				emailSenderService.send(m.getString(MailConstants.SUBJECT), m.getString(MailConstants.FROM),
						m.getString(MailConstants.VMF), map, new String[]
						{ m.getString(MailConstants.EMAIL) });

				counter.incrementAndGet();
			}
		}
		catch (final Exception e)
		{
			LOGGER.error(e.getMessage(), e);
		}

	}

	/**
	 * @return the counter
	 */
	public AtomicInteger getCounter()
	{
		return counter;
	}

	/**
	 * @param counter
	 *           the counter to set
	 */
	public void setCounter(final AtomicInteger counter)
	{
		this.counter = counter;
	}

}
