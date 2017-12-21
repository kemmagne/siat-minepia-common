package org.guce.siat.common.utils;

import com.google.zxing.WriterException;
import java.io.IOException;
import org.guce.siat.common.model.File;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author tadzotsa
 */
public class QRCodeUtilsTest {

//    @Ignore
    @Test
    public void test() throws WriterException, IOException, NoSuchMethodException {
        File siatFile = new File();
        siatFile.setId(50l);
        String filePath = "D:";
        String fileName = "image";
        QRCodeUtils.generateQRCode(siatFile, filePath, fileName);
//        Assert.assertTrue();
    }

}
