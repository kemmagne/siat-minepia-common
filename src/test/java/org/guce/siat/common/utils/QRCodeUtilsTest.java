package org.guce.siat.common.utils;

import com.google.zxing.WriterException;
import java.io.IOException;
import java.text.DecimalFormat;
import org.guce.siat.common.model.File;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author tadzotsa
 */
public class QRCodeUtilsTest {

    @Ignore
    @Test
    public void test() throws WriterException, IOException, NoSuchMethodException {
        File siatFile = new File();
        siatFile.setId(50l);
        String filePath = "D:";
        String fileName = "image";
        QRCodeUtils.generateQRCode(siatFile, filePath, fileName);
//        Assert.assertTrue();
    }

    @Ignore
    @Test
    public void test1() {
        System.out.println(new DecimalFormat("CT" + "000000").format(985));
    }

}

