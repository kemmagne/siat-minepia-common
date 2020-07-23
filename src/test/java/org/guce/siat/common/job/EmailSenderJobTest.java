package org.guce.siat.common.job;

import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author ht
 */
public class EmailSenderJobTest {

    @Test
    public void testMoveFileFileExists() throws IOException {
        String mailsFolder = System.getProperty("user.home");
        EmailSenderJob job = new EmailSenderJob();
        job.setMailsFolder(mailsFolder);
        File file = new File(mailsFolder, "file.txt");
        org.guce.siat.common.utils.io.IOUtils.writeBytesToFile(file, "Just a test file".getBytes());
        String s = job.moveFile(file);
        Assert.assertNotNull(s);
        Assert.assertFalse(file.exists());
        Assert.assertTrue(new File(s).exists());
    }

}
