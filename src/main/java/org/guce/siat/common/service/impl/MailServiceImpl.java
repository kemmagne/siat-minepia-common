package org.guce.siat.common.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.guce.siat.common.mail.MailConstants;
import org.guce.siat.common.service.MailService;
import org.guce.siat.common.utils.PropertiesConstants;
import org.guce.siat.common.utils.PropertiesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@PropertySource("classpath:global-config.properties")
public class MailServiceImpl implements MailService {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd_HHmm");

    @Autowired
    private PropertiesLoader propertiesLoader;

    /**
     * the messages folder
     */
    @Value("${mails.folder}")
    private String mailsFolder;

    /**
     * The from.
     */
    @Value("${mailSender.from}")
    private String from;

    /**
     * The reply to.
     */
    @Value("${mailSender.replyTo}")
    private String replyTo;

    @PostConstruct
    public void init() {
        mailsFolder = propertiesLoader.getProperty(PropertiesConstants.MAILS_FOLDER);
        from = propertiesLoader.getProperty(PropertiesConstants.MAIL_FROM);
        replyTo = propertiesLoader.getProperty(PropertiesConstants.MAIL_REPLY_TO);
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.mailService#sendMail(java.util.Map)
     */
    @Override
    public void sendMail(final Map<String, String> map) {
        final Map<String, Object> params = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            params.put(key, value);
        }
        backupEmail(map);
    }

    private void backupEmail(final Map<String, String> map) {
        final String backupEmailFileName = String.format("email_%s_%s.json",
                map.get(MailConstants.EMAIL),
                DATE_FORMAT.format(Calendar.getInstance().getTime()));
        final File backupEmailFile = new File(mailsFolder, backupEmailFileName);
        backupEmailFile.getParentFile().mkdirs();
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(backupEmailFile, map);
        } catch (IOException ex1) {
            LOG.error(null, ex1);
        }
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.MailService#getFromValue()
     */
    @Override
    public String getFromValue() {
        return getFrom();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.MailService#getReplyToValue()
     */
    @Override
    public String getReplyToValue() {
        return getReplyTo();
    }

    /**
     * Gets the from.
     *
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the from.
     *
     * @param from the new from
     */
    public void setFrom(final String from) {
        this.from = from;
    }

    /**
     * Gets the reply to.
     *
     * @return the reply to
     */
    public String getReplyTo() {
        return replyTo;
    }

    /**
     * Sets the reply to.
     *
     * @param replyTo the new reply to
     */
    public void setReplyTo(final String replyTo) {
        this.replyTo = replyTo;
    }

}
