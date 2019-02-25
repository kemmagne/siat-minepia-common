package org.guce.siat.common.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.guce.siat.common.mail.bo.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tadzotsa
 */
public class TaskResendMail {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(TaskResendMail.class);

    private EmailSenderService emailSenderService;
    private String mailsFolder;

    public void resendMail() {
        File mailsFolderFile = new File(mailsFolder);
        Collection<File> filesCollections = FileUtils.listFiles(mailsFolderFile, new String[]{"json"}, false);
        if (CollectionUtils.isNotEmpty(filesCollections)) {
            for (final File file : filesCollections) {
                try {
                    final ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, Object> map = objectMapper.readValue(file, Map.class);
                    emailSenderService.send(map);
                    file.delete();
                } catch (Exception ex) {
                    LOG.error(null, ex);
                }
            }
        }
    }

    public EmailSenderService getEmailSenderService() {
        return emailSenderService;
    }

    public void setEmailSenderService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    public String getMailsFolder() {
        return mailsFolder;
    }

    public void setMailsFolder(String mailsFolder) {
        this.mailsFolder = mailsFolder;
    }

}
