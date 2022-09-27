package org.guce.siat.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Hashtable;
import java.util.Map;
import javax.imageio.ImageIO;
//import org.guce.siat.common.model.File;
//import org.guce.siat.common.model.FileField;
//import org.guce.siat.common.model.FileFieldValue;
//import org.guce.siat.common.model.FileType;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Ignore;

/**
 *
 * @author tadzotsa
 */
public class FileUtilsTest {

    @Ignore
    @Test
    public void testApplyModifications() {
//        File source = new File();
//        File dest = new File();
//        FileType fileType = new FileType();
//        fileType.setId(Long.MIN_VALUE);
//        source.setFileType(fileType);
//        dest.setFileType(fileType);
//
//        String code = "CODE";
//        FileField ff = new FileField();
//        ff.setCode(code);
//        ff.setUpdatable(Boolean.TRUE);
//
//        String value = "FINALVALUE";
//
//        FileFieldValue ffv;
//
//        ffv = new FileFieldValue();
//        ffv.setFileField(ff);
//        ffv.setValue(value);
//        source.setFileFieldValueList(Arrays.asList(ffv));
//
//        ffv = new FileFieldValue();
//        ffv.setFileField(ff);
//        ffv.setValue("FIRSTVALUE");
//        dest.setFileFieldValueList(Arrays.asList(ffv));
//
//        FileUtils.applyModifications(source, dest);
//
//        Assert.assertEquals(value, dest.getFileFieldValueList().get(0).getValue());
    }

    @Ignore
    @Test
    public void testGetRootFile() {

//        long id = 1l;
//
//        File root = new File();
//        root.setId(id++);
//
//        File current = new File();
//        current.setId(id++);
//        current.setParent(root);
//
//        Assert.assertEquals(root, FileUtils.getRootFile(current));
    }

    @Ignore
    @Test
    public void testQRCodeCreation() throws Exception {
//        String content = "https://localhost:40081/siat-ct-web/pages/unsecure/document.xhtml?file=CTE001533";
//        QRCodeGenerator generator = new QRCodeGenerator();
//        byte[] bytes = generator.generateQR(content);
//        org.apache.commons.io.FileUtils.writeByteArrayToFile(new java.io.File("CTE001533-qr-code.png"), bytes);
    }

    public static void main1(String[] args) {
        String content = "https://localhost:40081/siat-ct-web/pages/unsecure/document.xhtml?file=CTE001533";
        String filePath = "CTE001533-qr-code.png";
        int size = 512;
        String crunchifyFileType = "png";
        java.io.File crunchifyFile = new java.io.File(filePath);
        try {

            Map<EncodeHintType, Object> crunchifyHintType = new EnumMap<>(EncodeHintType.class);
            crunchifyHintType.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Now with version 3.4.1 you could change margin (white border size)
            crunchifyHintType.put(EncodeHintType.MARGIN, 1);
            /* default = 4 */
            Object put = crunchifyHintType.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

            QRCodeWriter mYQRCodeWriter = new QRCodeWriter(); // throws com.google.zxing.WriterException
            BitMatrix crunchifyBitMatrix = mYQRCodeWriter.encode(content, BarcodeFormat.QR_CODE, size,
                    size, crunchifyHintType);
            int CrunchifyWidth = crunchifyBitMatrix.getWidth();

            // The BufferedImage subclass describes an Image with an accessible buffer of crunchifyImage data.
            BufferedImage crunchifyImage = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);

            // Creates a Graphics2D, which can be used to draw into this BufferedImage.
            crunchifyImage.createGraphics();

            // This Graphics2D class extends the Graphics class to provide more sophisticated control over geometry, coordinate transformations, color management, and text layout.
            // This is the fundamental class for rendering 2-dimensional shapes, text and images on the Java(tm) platform.
            Graphics2D crunchifyGraphics = (Graphics2D) crunchifyImage.getGraphics();

            // setColor() sets this graphics context's current color to the specified color.
            // All subsequent graphics operations using this graphics context use this specified color.
            crunchifyGraphics.setColor(Color.white);

            // fillRect() fills the specified rectangle. The left and right edges of the rectangle are at x and x + width - 1.
            crunchifyGraphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);

            // TODO: Please change this color as per your need
            crunchifyGraphics.setColor(Color.BLUE);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (crunchifyBitMatrix.get(i, j)) {
                        crunchifyGraphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            // A class containing static convenience methods for locating
            // ImageReaders and ImageWriters, and performing simple encoding and decoding.
            ImageIO.write(crunchifyImage, crunchifyFileType, crunchifyFile);

            System.out.println("\nCongratulation.. You have successfully created QR Code.. \nCheck your code here: " + filePath);
        } catch (WriterException e) {
            System.out.println("\nSorry.. Something went wrong...\n");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws WriterException, IOException {
//        String content = "https://www.journaldev.com";
//        String content = "https://localhost:40081/siat-ct-web/pages/unsecure/document.xhtml?file=CTE001533";
        String content = "https://testsiat.guichetunique.cm/siat-ct-web/pages/unsecure/document.xhtml?file=CTE001533";
        String filePath = "JD.png";
        int size = 125;
        String fileType = "png";
        java.io.File qrFile = new java.io.File(filePath);
        createQRImage(qrFile, content, size, fileType);
        System.out.println("DONE");
    }

    private static void createQRImage(java.io.File qrFile, String qrCodeText, int size, String fileType) throws WriterException, IOException {
        // Create the ByteMatrix for the QR-Code that encodes the given String
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ImageIO.write(image, fileType, qrFile);
    }

}
