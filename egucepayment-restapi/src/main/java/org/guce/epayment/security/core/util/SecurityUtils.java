package org.guce.epayment.security.core.util;

import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author tadzotsa
 */
public class SecurityUtils {

    private static final String ENC_TRANSFORMATION = "AES/CBC/NoPadding";
    private static final String ENC_ALGO = "AES";

    public static String symetricEncrypt(String input, String key, String iv) {

        try {

            Cipher cipher = Cipher.getInstance(ENC_TRANSFORMATION);
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            SecretKeySpec spec = new SecretKeySpec(key.getBytes(), ENC_ALGO);
            cipher.init(Cipher.ENCRYPT_MODE, spec, ips);
            String padString = padString(input);

            return getHexString(cipher.doFinal(padString.getBytes()));
        } catch (Exception ex) {
            Logger.getLogger(org.guce.util.CipherUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static String symetricDecrypt(String input, String key, String iv) {
        try {
            Cipher cipher = Cipher.getInstance(ENC_TRANSFORMATION);
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            SecretKeySpec spec = new SecretKeySpec(key.getBytes(), ENC_ALGO);
            cipher.init(Cipher.DECRYPT_MODE, spec, ips);
            return new String(cipher.doFinal(toByteArray(input)));
        } catch (Exception ex) {
            Logger.getLogger(org.guce.util.CipherUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private static String padString(String source) {
        char paddingChar = ' ';
        int size = 16;
        int padLength = size - source.getBytes().length % size;
        if (padLength != 16) {
            for (int i = 0; i < padLength; i++) {
                source = source + paddingChar;
            }
        }
        return source;
    }

    private static String getHexString(byte[] bytes) throws Exception {
        String result = "";
        for (int i = 0; i < bytes.length; i++) {
            result = result + Integer.toString((bytes[i] & 0xFF) + 256, 16).substring(1);
        }
        return result;
    }

    private static byte[] toByteArray(String hexString) {
        int arrLength = hexString.length() >> 1;
        byte[] buf = new byte[arrLength];
        for (int ii = 0; ii < arrLength; ii++) {
            int index = ii << 1;
            String l_digit = hexString.substring(index, index + 2);
            buf[ii] = ((byte) Integer.parseInt(l_digit, 16));
        }
        return buf;
    }

    public static String generatePassword() {

        final SecureRandom RANDOM = new SecureRandom();

        final String MINUSCULES = "abcdefghijklmnopqrstuvwxyz";
        final String MAJUSCULES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String CHIFFRES = "0123456789";
        final String PONCTUATIONS = "&#(-_),?;.:/!";

        final StringBuilder passBuilder = new StringBuilder();
        int index;
        while (8 > passBuilder.length()) {
            index = RANDOM.nextInt(4);
            switch (index) {
                case 0:
                    passBuilder.append(MINUSCULES.charAt(RANDOM.nextInt(MINUSCULES.length())));
                    break;
                case 1:
                    passBuilder.append(MAJUSCULES.charAt(RANDOM.nextInt(MAJUSCULES.length())));
                    break;
                case 2:
                    passBuilder.append(CHIFFRES.charAt(RANDOM.nextInt(CHIFFRES.length())));
                    break;
                case 3:
                    passBuilder.append(PONCTUATIONS.charAt(RANDOM.nextInt(PONCTUATIONS.length())));
                    break;
            }
        }
        return passBuilder.toString();
    }

    private SecurityUtils() {
    }

}
