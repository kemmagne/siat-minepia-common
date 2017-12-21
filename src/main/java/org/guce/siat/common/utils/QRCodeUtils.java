package org.guce.siat.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 *
 * @author tadzotsa
 */
public class QRCodeUtils {

    private static final String QR_CODE_IMAGE_FILE_TYPE = "png";
    private static final int QR_CODE_IMAGE_SIZE = 125;

    private QRCodeUtils() {
    }

    public static void generateQRCode(org.guce.siat.common.model.File siatFile, String filePath, String fileName) throws WriterException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Writer stringWriter = new StringWriter();
        mapper.writeValue(stringWriter, siatFile);
        File qrFile = new File(filePath + File.separator + fileName + "." + QR_CODE_IMAGE_FILE_TYPE);
        createQRImage(qrFile, stringWriter.toString(), QR_CODE_IMAGE_SIZE, QR_CODE_IMAGE_FILE_TYPE);
    }

    private static void createQRImage(File qrFile, String qrCodeContent, int size, String fileType) throws WriterException, IOException {
        // creating the ByteMatrix for QR-Code that encodes the given string
        Map<EncodeHintType, Object> hinMap = new HashMap<>();
        hinMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix byteMatrix = writer.encode(qrCodeContent, BarcodeFormat.QR_CODE, size, size, hinMap);
        // make the BuffredImage that are to hold the QRCode
        int matrixWith = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWith, matrixWith, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics graphics = (Graphics) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWith, matrixWith);
        // paint and save the image useing the byteMatrix
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWith; i++) {
            for (int j = 0; j < matrixWith; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

        ImageIO.write(image, fileType, qrFile);
    }

}
