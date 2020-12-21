package org.guce.siat.common.utils.io;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FileUtils;
import org.guce.siat.common.utils.ebms.Generator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class IOUtils.
 */
public final class IOUtils {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(IOUtils.class);

    /**
     * Write bytes to file.
     *
     * @param theFile the the file
     * @param bytes the bytes
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void writeBytesToFile(File theFile, byte[] bytes) throws IOException {
        FileUtils.writeByteArrayToFile(theFile, bytes);
    }

    public static byte[] attachmentsToZip(Map<String, byte[]> attachments) {
        try {
            ByteArrayOutputStream zipOut = new ByteArrayOutputStream();
            BufferedOutputStream buff = new BufferedOutputStream(zipOut);
            try (ZipOutputStream zipStream = new ZipOutputStream(buff)) {
                for (Map.Entry<String, byte[]> entry : attachments.entrySet()) {
                    String attachmentName = entry.getKey();
                    byte[] bytes = entry.getValue();
                    ZipEntry zipEntry = new ZipEntry(attachmentName);
                    zipStream.putNextEntry(zipEntry);
                    zipStream.write(bytes);
                    zipStream.closeEntry();
                }
            }
            return zipOut.toByteArray();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Generate message id.
     *
     * @return the string
     */
    public static String generateMessageID() {
        return Generator.generateMessageID();
    }

    private IOUtils() {
    }

}
