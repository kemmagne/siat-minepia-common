package org.guce.siat.common.utils;

import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * The Class SecurityUtils.
 */
public final class SecurityUtils {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(SecurityUtils.class);

    /**
     * Instantiates a new security utils.
     */
    private SecurityUtils() {
    }

    /**
     * Gets the current user.
     *
     * @return the current user
     */
    public static String getCurrentUser() {

        /**
         * The try catch bloc has been added for tests purpose
         */
        try {
            return SecurityContextHolder.getContext().getAuthentication() != null
                    ? SecurityContextHolder.getContext().getAuthentication().getName()
                    : "Scheduled Job";
        } catch (Exception ex) {
            return "ROOT";
        }
    }

    /**
     * Gets the current address ip.
     *
     * @return the current address ip
     */
    public static String getCurrentAddressIp() {

        /**
         * The try catch bloc has been added for tests purpose
         */
        try {
            final HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            return curRequest.getRemoteAddr();
        } catch (Exception ex) {
            return StringUtils.EMPTY;
        }
    }

    /**
     * Gets the current mac address.
     *
     * @return the current mac address
     */
    public static String getCurrentMacAddress() {
        String macAdress = StringUtils.EMPTY;
//        try {
//            final Pattern p = Pattern.compile("([0-9A-Fa-f]{2}[:-]){5}[0-9A-Fa-f]{2}");
//
//            final Runtime runtime = Runtime.getRuntime();
//            final Process process = runtime.exec(getARPCommand());
//            try (final InputStream inputstream = process.getInputStream();
//                    final InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
//                    final BufferedReader bufferedreader = new BufferedReader(inputstreamreader);) {
//                String line;
//                readLine:
//                while ((line = bufferedreader.readLine()) != null) {
//                    final Matcher matcher = p.matcher(line);
//                    while (matcher.find()) {
//                        macAdress = matcher.group();
//                        break readLine;
//                    }
//                }
//            }
//
//        } catch (final Exception e) {
//            LOG.error(e.getMessage(), e);
//        }
        return macAdress;
    }

    /**
     * Gets the ARP command.
     *
     * @return the ARP command
     */
    private static String getARPCommand() {
        final HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        return "arp -a " + curRequest.getRemoteAddr();
    }

    public static String getBasicAuth(final String login, final String password) {

        String auth = login + ":" + password;
        byte[] encodedAuth = Base64.encode(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);

        return authHeader;
    }

}
