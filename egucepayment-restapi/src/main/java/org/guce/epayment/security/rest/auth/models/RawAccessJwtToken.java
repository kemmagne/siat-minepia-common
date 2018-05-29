package org.guce.epayment.security.rest.auth.models;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.util.Map;
import org.guce.epayment.rest.controllers.utils.RestConstants;
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
                    .requireSubject(infos.get(RestConstants.LOGIN).toString())
                    .requireIssuer(infos.get(RestConstants.APPLICATION_ISSUER).toString())
                    .require(RestConstants.BROWSER_FINGER_PRINT, infos.get(RestConstants.BROWSER_FINGER_PRINT))
                    .require(RestConstants.REMOTE_ADR, infos.get(RestConstants.REMOTE_ADR))
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
