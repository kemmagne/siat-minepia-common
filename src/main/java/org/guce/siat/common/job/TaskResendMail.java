package org.guce.siat.common.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Collection;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.guce.siat.common.mail.bo.EmailSenderService;
import org.guce.siat.common.utils.PropertiesConstants;
import org.guce.siat.common.utils.PropertiesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *
 * @author tadzotsa
 */
@PropertySource("classpath:global-config.properties")
@Component("taskMailSender")
public class TaskResendMail {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(TaskResendMail.class);

    @Autowired
    private PropertiesLoader propertiesLoader;

    /**
     * the mails folder
     */
    @Value("${mails.folder}")
    private String mailsFolder;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostConstruct
    public void init() {
        mailsFolder = propertiesLoader.getProperty(PropertiesConstants.MAILS_FOLDER);
    }

    public void resendMail() {
        File mailsFolderFile = new File(mailsFolder);
        mailsFolderFile.mkdirs();
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

}
