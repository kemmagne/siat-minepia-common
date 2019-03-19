package org.guce.siat.common.utils;

import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.codec.Base64;

/**
 *
 * @author tadzotsa
 */
public class HttpUtils {

    public static HttpHeaders createHeaders(final String username, final String password) {

        final HttpHeaders headers = new HttpHeaders();

        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encode(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);

        return headers;
    }

    private HttpUtils() {
    }

}
