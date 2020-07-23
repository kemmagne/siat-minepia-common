package org.guce.siat.common.utils.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.guce.siat.common.utils.ebms.Generator;

/**
 * The Class IOUtils.
 */
public final class IOUtils {

    private IOUtils() {
    }

    /**
     * Write bytes to file.
     *
     * @param theFile the the file
     * @param bytes the bytes
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void writeBytesToFile(final File theFile, final byte[] bytes) throws IOException {

        try ( InputStream is = new ByteArrayInputStream(bytes);  OutputStream fos = new FileOutputStream(theFile)) {
            org.apache.commons.io.IOUtils.copy(is, fos);
        }

//        BufferedOutputStream bos = null;
//
//        try {
//            FileOutputStream fos = new FileOutputStream(theFile);
//            bos = new BufferedOutputStream(fos);
//            bos.write(bytes);
//        } finally {
//            if (bos != null) {
//                try {
//                    //flush and close the BufferedOutputStream
//                    bos.flush();
//                    bos.close();
//                } catch (final Exception e) {
//                }
//            }
//        }
    }

    /**
     * Generate message id.
     *
     * @return the string
     */
    public static String generateMessageID() {
        return Generator.generateMessageID();
    }
}
