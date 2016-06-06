package org.guce.siat.common.service.impl;

import java.util.Map;

import org.guce.siat.common.mail.jms.JmsMailMessageProducer;
import org.guce.siat.common.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class MailServiceImpl.
 */
@Service("mailService")
@Transactional(readOnly = true)
@PropertySource("classpath:params-mail-core.properties")
public class MailServiceImpl implements MailService
{

	/** The from. */
	@Value("${mailSender.from}")
	private String from;

	/** The reply to. */
	@Value("${mailSender.replyTo}")
	private String replyTo;

	/** The xml folder. */
	@Value("${xml.folder}")
	private String xmlFolder;


	/** The jms mail message producer. */
	@Autowired
	private JmsMailMessageProducer jmsMailMessageProducer;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.mailService#sendMail(java.util.Map)
	 */
	@Override
	public void sendMail(final Map<String, String> map)
	{
		jmsMailMessageProducer.sendMail(map);

	}

	/**
	 * Gets the jms mail message producer.
	 *
	 * @return the jms mail message producer
	 */
	public JmsMailMessageProducer getJmsMailMessageProducer()
	{
		return jmsMailMessageProducer;
	}

	/**
	 * Sets the jms mail message producer.
	 *
	 * @param jmsMailMessageProducer
	 *           the new jms mail message producer
	 */
	public void setJmsMailMessageProducer(final JmsMailMessageProducer jmsMailMessageProducer)
	{
		this.jmsMailMessageProducer = jmsMailMessageProducer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.MailService#getFromValue()
	 */
	@Override
	public String getFromValue()
	{
		return getFrom();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.service.MailService#getReplyToValue()
	 */
	@Override
	public String getReplyToValue()
	{
		return getReplyTo();
	}

	/**
	 * Gets the from.
	 *
	 * @return the from
	 */
	public String getFrom()
	{
		return from;
	}

	/**
	 * Sets the from.
	 *
	 * @param from
	 *           the new from
	 */
	public void setFrom(final String from)
	{
		this.from = from;
	}

	/**
	 * Gets the reply to.
	 *
	 * @return the reply to
	 */
	public String getReplyTo()
	{
		return replyTo;
	}

	/**
	 * Sets the reply to.
	 *
	 * @param replyTo
	 *           the new reply to
	 */
	public void setReplyTo(final String replyTo)
	{
		this.replyTo = replyTo;
	}

	/**
	 * Gets the xml folder.
	 *
	 * @return the xml folder
	 */
	public String getXmlFolder()
	{
		return xmlFolder;
	}

	/**
	 * Sets the xml folder.
	 *
	 * @param xmlFolder
	 *           the new xml folder
	 */
	public void setXmlFolder(final String xmlFolder)
	{
		this.xmlFolder = xmlFolder;
	}
}
