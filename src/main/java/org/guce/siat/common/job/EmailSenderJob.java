package org.guce.siat.common.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.guce.siat.common.mail.bo.EmailSenderService;
import org.guce.siat.common.utils.PropertiesConstants;
import org.guce.siat.common.utils.PropertiesLoader;
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

    /**
     * The email sender service
     */
    private EmailSenderService emailSenderService;

    /**
     * The properties loader bean
     */
    private PropertiesLoader propertiesLoader;

    /**
     * This method is just for test purpose
     *
     * @throws JobExecutionException
     */
    public void executeInternal1() throws JobExecutionException {
        executeInternal(null);
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        if (StringUtils.isBlank(mailsFolder)) {
            mailsFolder = propertiesLoader.getProperty(PropertiesConstants.MAILS_FOLDER);
        }

        File mailsFolderFile = new File(mailsFolder);
        mailsFolderFile.mkdirs();
        Collection<File> filesCollections = FileUtils.listFiles(mailsFolderFile, new String[]{"json"}, false);
        for (final File file : filesCollections) {
            synchronized (file) {

                if (!file.exists()) {
                    continue;
                }

                try {
                    final ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, Object> map = objectMapper.readValue(file, Map.class);
                    emailSenderService.send(map);
                    if (file.exists()) {
                        file.delete();
                    }
                } catch (Exception ex) {
                    LOG.error(file.getName(), ex);
                    if (file.exists()) {
                        moveFile(file);
                    }
                }
            }
        }
    }

    public String moveFile(File file) {

        try {

            String notSentDirPath = String.format("%s/error/%s", mailsFolder, new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
            File notSentDir = new File(notSentDirPath);
            notSentDir.mkdirs();
            File notSentFile = new File(notSentDir, file.getName());
            try ( InputStream fis = new FileInputStream(file);  OutputStream fos = new FileOutputStream(notSentFile)) {
                IOUtils.copy(fis, fos);
            }
            file.delete();

            return notSentFile.getPath();
        } catch (IOException ex) {
            LOG.warn(file.getName(), ex);
            return null;
        }
    }

    public void setMailsFolder(String mailsFolder) {
        this.mailsFolder = mailsFolder;
    }

    public void setEmailSenderService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    public void setPropertiesLoader(PropertiesLoader propertiesLoader) {
        this.propertiesLoader = propertiesLoader;
    }

}
