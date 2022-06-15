package org.guce.siat.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author tadzotsa
 */
public class QRCodeGenerator {

    private static final String QR_CODE_IMAGE_FILE_TYPE = "png";
    private static final int QR_CODE_IMAGE_SIZE = 512;

    public byte[] generateQR(String data) throws Exception {
        return createQRImage(data, QR_CODE_IMAGE_SIZE, QR_CODE_IMAGE_FILE_TYPE);
    }

    public byte[] generateQR(String data, int size) throws Exception {
        return createQRImage(data, size, QR_CODE_IMAGE_FILE_TYPE);
    }

    public byte[] generateQR(String data, String fileType) throws Exception {
        return createQRImage(data, QR_CODE_IMAGE_SIZE, fileType);
    }

    public byte[] generateQR(String data, int size, String fileType) throws Exception {
        return createQRImage(data, size, fileType);
    }

    private byte[] createQRImage(String qrCodeText, int size, String fileType) throws Exception {
        // Create the ByteMatrix for the QR-Code that encodes the given String
        HashMap hintMap = new HashMap();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText,
                BarcodeFormat.QR_CODE, size, size, hintMap);
        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth,
                BufferedImage.TYPE_INT_RGB);
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
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, fileType, out);
        return out.toByteArray();
    }

}
