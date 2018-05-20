package org.guce.epayment.security.rest.auth.models;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;

/**
 *
 * @author tadzotsa
 */
public class RawAccessJwtToken implements JwtToken {

    private static final Logger LOGGER = LoggerFactory.getLogger(RawAccessJwtToken.class);

    private final String token;

    public RawAccessJwtToken(String token) {
        this.token = token;
    }

    public Jws<Claims> parseClaims(final String signingKey, final Map<String, Object> infos) {
        try {
            return Jwts.parser()
                    .requireSubject(infos.get("login").toString())
                    .requireIssuer(infos.get("application_issuer").toString())
                    .require("browser_finger_print", infos.get("browser_finger_print"))
                    .require("remote_address", infos.get("remote_address"))
                    .setSigningKey(signingKey).parseClaimsJws(this.token);
        } catch (Exception ex) {
            LOGGER.error("Invalid JWT Token", ex);
            throw new BadCredentialsException("Invalid JWT token: ", ex);
        }
    }

    @Override
    public String getToken() {
        return token;
    }

}
