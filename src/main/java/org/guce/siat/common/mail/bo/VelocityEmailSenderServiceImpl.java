package org.guce.siat.common.mail.bo;

import java.io.File;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;


/**
 * The Class VelocityEmailSenderServiceImpl.
 */
public class VelocityEmailSenderServiceImpl implements EmailSenderService
{

	/** The velocity engine. */
	private final VelocityEngine velocityEngine;

	/** The mail sender. */
	private final JavaMailSender mailSender;

	/** The msg. */
	private final SimpleMailMessage msg;

	/**
	 * Instantiates a new velocity email sender service impl.
	 *
	 * @param velocityEngine
	 *           the velocity engine
	 * @param mailSender
	 *           the mail sender
	 * @param msg
	 *           the msg
	 */
	@Autowired
	public VelocityEmailSenderServiceImpl(final VelocityEngine velocityEngine, final JavaMailSender mailSender,
			final SimpleMailMessage msg)
	{
		super();
		this.velocityEngine = velocityEngine;
		this.mailSender = mailSender;
		this.msg = msg;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.guce.siat.common.mail.bo.EmailSenderService#send(java.lang.String, java.lang.String, java.lang.String,
	 * java.util.Map, java.lang.String[], java.io.File[])
	 */
	public void send(final String subject, final String from, final String emailBodyPath,
			final Map<String, Object> hTemplateVariables, final String[] mailReceiver, final File... files)
	{

		final MimeMessagePreparator preparator = new MimeMessagePreparator()
		{
			public void prepare(final MimeMessage mimeMessage) throws Exception
			{
				final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, files != null && files.length != 0);
				message.setTo(mailReceiver);
				message.setFrom(from);
				message.setSubject(subject);
				final String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailBodyPath, "UTF-8",
						hTemplateVariables);
				message.setText(body, true);

				for (int i = 0; i < files.length; i++)
				{
					message.addAttachment(files[i].getName(), files[i]);
				}
			}
		};

		mailSender.send(preparator);

	}

	/**
	 * Gets the velocity engine.
	 *
	 * @return the velocity engine
	 */
	public VelocityEngine getVelocityEngine()
	{
		return velocityEngine;
	}

	/**
	 * Gets the mail sender.
	 *
	 * @return the mail sender
	 */
	public JavaMailSender getMailSender()
	{
		return mailSender;
	}

	/**
	 * Gets the msg.
	 *
	 * @return the msg
	 */
	public SimpleMailMessage getMsg()
	{
		return msg;
	}

}
