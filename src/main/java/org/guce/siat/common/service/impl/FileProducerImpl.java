package org.guce.siat.common.service.impl;

import java.util.Map;

import org.guce.siat.common.service.FileProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


/**
 * The Class FileProducerImpl.
 *
 */
@Service("fileProducer")
public class FileProducerImpl implements FileProducer
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FileProducerImpl.class);

	/** The jms template. */
	@Autowired
	private JmsTemplate jmsTemplate;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.FileProducer#sendFile(java.util.Map)
	 */
	@Override
	public void sendFile(final Map<String, Object> file)
	{
		LOG.info("######## Start sending Message");
		if (jmsTemplate != null)
		{
			jmsTemplate.convertAndSend(file);
			LOG.info("######## Message Sent Successfully");
		}
	}

}
