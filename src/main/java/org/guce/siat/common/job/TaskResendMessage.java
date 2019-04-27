package org.guce.siat.common.job;

import java.io.File;
import java.util.Collection;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.guce.orchestra.core.OrchestraEbxmlMessage;
import org.guce.orchestra.core.OrchestraEbxmlMessageFactory;
import org.guce.siat.common.service.FileProducer;
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
@Component("taskMessageResender")
public class TaskResendMessage {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(TaskResendMessage.class);

    private static final OrchestraEbxmlMessageFactory FACTORY = OrchestraEbxmlMessageFactory.getInstance();

    @Autowired
    private FileProducer fileProducer;

    /**
     * the messages folder
     */
    @Value("${messages.folder}")
    private String messagesFolder;

    public void resendMessage() {
        File messagesFolderFile = new File(messagesFolder);
        Collection<File> filesCollections = FileUtils.listFiles(messagesFolderFile, new String[]{"ebxml"}, false);
        if (CollectionUtils.isNotEmpty(filesCollections)) {
            for (final File file : filesCollections) {
                try {
                    final OrchestraEbxmlMessage ebxml = FACTORY.createFromFile(file.getAbsolutePath());
                    fileProducer.sendViaRest(ebxml, null);
                } catch (Exception ex) {
                    LOG.error(null, ex);
                }
            }
        }
    }

}

