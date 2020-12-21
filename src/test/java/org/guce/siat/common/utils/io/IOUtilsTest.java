package org.guce.siat.common.utils.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author ht
 */
public class IOUtilsTest extends TestCase {

    private static final int BUFFER_SIZE = 4096;

    @Test
    public void test_attachmentsToZip() throws IOException {

        Map<String, byte[]> attachmentsIn = new HashMap<>();

        attachmentsIn.put(RandomStringUtils.randomAlphabetic(5).concat(".txt"), RandomStringUtils.randomAlphabetic(500).getBytes());
        attachmentsIn.put(RandomStringUtils.randomAlphabetic(10).concat(".txt"), RandomStringUtils.randomAlphabetic(1000).getBytes());
        attachmentsIn.put(RandomStringUtils.randomAlphabetic(15).concat(".txt"), RandomStringUtils.randomAlphabetic(1500).getBytes());

        byte[] zipBytes = IOUtils.attachmentsToZip(attachmentsIn);
        Assert.assertNotNull(zipBytes);
        File file = new File(System.getProperty("user.home"), RandomStringUtils.randomAlphabetic(10).concat(".zip"));
        FileUtils.writeByteArrayToFile(file, zipBytes);
        Assert.assertTrue(file.exists());

        Map<String, byte[]> attachmentsOut = new HashMap<>();
        try (InputStream fis = new FileInputStream(file);
                ZipInputStream zis = new ZipInputStream(fis)) {
            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null) {
                byte[] zeBytes = zipEntryToByteArray(zis);
                attachmentsOut.put(ze.getName(), zeBytes);
            }
            Assert.assertEquals(attachmentsIn.keySet(), attachmentsOut.keySet());
            for (byte[] outBytes : attachmentsOut.values()) {
                boolean ok = false;
                for (byte[] inBytes : attachmentsIn.values()) {
                    if (outBytes.length == inBytes.length) {
                        ok = true;
                    }
                }
                Assert.assertTrue(ok);
            }
        }

        file.delete();
        Assert.assertFalse(file.exists());
    }

    private byte[] zipEntryToByteArray(ZipInputStream zis) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read;
        while ((read = zis.read(bytesIn)) != -1) {
            out.write(bytesIn, 0, read);
        }
        out.close();
        return out.toByteArray();
    }

}
