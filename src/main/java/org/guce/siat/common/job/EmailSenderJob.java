package org.guce.siat.common.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.guce.siat.common.mail.bo.EmailSenderService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 *
 * @author tadzotsa
 */
public class EmailSenderJob extends QuartzJobBean {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(TaskResendMail.class);

    /**
     * the mails folder
     */
    private String mailsFolder;

    private EmailSenderService emailSenderService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        File mailsFolderFile = new File(mailsFolder);
        mailsFolderFile.mkdirs();
        Collection<File> filesCollections = FileUtils.listFiles(mailsFolderFile, new String[]{"json"}, false);
        if (CollectionUtils.isNotEmpty(filesCollections)) {
            for (final File file : filesCollections) {
                if (file.exists()) {
                    try {
                        synchronized (file) {
                            final ObjectMapper objectMapper = new ObjectMapper();
                            Map<String, Object> map = objectMapper.readValue(file, Map.class);
                            emailSenderService.send(map);
                            if (file.exists()) {
                                file.delete();
                            }
                        }
                    } catch (Exception ex) {
                        LOG.error(null, ex);
                    }
                }
            }
        }
    }

    public void setMailsFolder(String mailsFolder) {
        this.mailsFolder = mailsFolder;
    }

    public void setEmailSenderService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

}
