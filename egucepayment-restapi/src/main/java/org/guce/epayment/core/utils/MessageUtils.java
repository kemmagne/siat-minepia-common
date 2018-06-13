package org.guce.epayment.core.utils;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 *
 * @author tadzotsa
 */
public final class MessageUtils {

    private static int messageIdCounter = 0;

    private static Object messageIdCounterLock = new Object();

    /**
     * Generates a message ID which conforms to
     * <a href="http://www.ietf.org/rfc/rfc2822.txt">RFC2822</a>
     *
     * @return the message ID.
     */
    public static String generateMessageID() {
        // this message id conforms to MessageId [RFC2822]
        int localCounter;
        synchronized (messageIdCounterLock) {
            messageIdCounter %= 100;
            localCounter = messageIdCounter;
            messageIdCounter++;
        }

        final StringBuilder messageId = new StringBuilder();
        messageId.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS")));
        messageId.append(addLeadingZero(String.valueOf(localCounter), 2));

        String domain;
        try {
            final InetAddress localAddr = InetAddress.getLocalHost();
            domain = localAddr.getHostAddress();
        } catch (final Exception e) {
            domain = "unknown-domain";
        }

        messageId.append("@").append(domain);

        return messageId.toString();
    }

    /**
     * Adds leading zeros to the given string to the specified length. Nothing
     * will be done if the length of the given string is equal to or greater
     * than the specified length.
     *
     * @param s the source string.
     * @param len the length of the target string.
     * @return the string after adding leading zeros.
     */
    private static String addLeadingZero(final String s, final int len) {
        return addLeadingCharacter(s, '0', len);
    }

    /**
     * Adds specified leading characters to the specified length. Nothing will
     * be done if the length of the given string is equal to or greater than the
     * specified length.
     *
     * @param s the source string.
     * @param c the leading character(s) to be added.
     * @param len the length of the target string.
     * @return the string after adding the specified leading character(s).
     */
    private static String addLeadingCharacter(String s, final char c, final int len) {
        while (s != null && s.length() < len) {
            s = c + s;
        }
        return s;
    }

    /**
     * Generates a UUID which conforms to
     * <a href="http://www.iso.ch/cate/d2229.html">ISO/IEC 11578:1996</a>
     *
     * @return the UUID.
     */
    private static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    private MessageUtils() {
    }

}
