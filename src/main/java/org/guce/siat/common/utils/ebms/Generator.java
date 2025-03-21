package org.guce.siat.common.utils.ebms;

import java.net.InetAddress;
import java.util.Date;
import java.util.UUID;

/**
 * Generator is a convenient class for generating various standard objects. For
 * instance, a message ID which conforms to RFC2822.
 */
public final class Generator {

    private static int messageIdCounter = 0;

    private static final Object MESSAGE_ID_COUNTER_LOCK = new Object();

    /**
     * Creates a new instance of Generator.
     */
    private Generator() {
    }

    /**
     * Generates a message ID which conforms to
     * <a href="http://www.ietf.org/rfc/rfc2822.txt">RFC2822</a>
     *
     * @return the message ID.
     */
    public static String generateMessageID() {
        // this message id conforms to MessageId [RFC2822]
        final DataFormatter messageIdFormatter = DataFormatter.getInstance();
        final Date timestamp = new Date();
        int localCounter;
        synchronized (MESSAGE_ID_COUNTER_LOCK) {
            messageIdCounter %= 100;
            localCounter = messageIdCounter;
            messageIdCounter++;
        }

        final StringBuilder messageId = new StringBuilder();
        messageId.append(messageIdFormatter.formatDate(timestamp, "yyyyMMdd-HHmmss-SSS"));
        messageId.append(StringUtilities.addLeadingZero(String.valueOf(localCounter), 2));

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
     * Generates a UUID which conforms to
     * <a href="http://www.iso.ch/cate/d2229.html">ISO/IEC 11578:1996</a>
     *
     * @return the UUID.
     */
    private static String generateUUID() {
        return UUID.randomUUID().toString();
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
}

