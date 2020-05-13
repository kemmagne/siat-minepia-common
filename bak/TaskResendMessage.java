package org.guce.siat.common.job;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.guce.siat.common.service.FileProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class TaskNotificationMail.
 */
public class TaskResendMessage {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(TaskResendMessage.class);

    private FileProducer fileProducer;
    private String messagesFolder;

    public void resendMessage() {
        File messagesFolderFile = new File(messagesFolder);
        Collection<File> filesCollections = FileUtils.listFiles(messagesFolderFile, new String[]{"ebxml"}, false);
        if (CollectionUtils.isNotEmpty(filesCollections)) {
            for (final File file : filesCollections) {
                try {
                    final byte[] ebxml = FileUtils.readFileToByteArray(file);
                    fileProducer.sendViaRest(ebxml);
                } catch (IOException | SOAPException | TransformerException ex) {
                    LOG.error(null, ex);
                }
            }
        }
    }

    public FileProducer getFileProducer() {
        return fileProducer;
    }

    public void setFileProducer(FileProducer fileProducer) {
        this.fileProducer = fileProducer;
    }

    public String getMessagesFolder() {
        return messagesFolder;
    }

    public void setMessagesFolder(String messagesFolder) {
        this.messagesFolder = messagesFolder;
    }

}

