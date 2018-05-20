package org.guce.epayment.security.rest.auth.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;

/**
 *
 * @author tadzotsa
 */
public class AccessJwtToken implements JwtToken {

    private final String rawToken;
    @JsonIgnore
    private final Claims claims;

    public AccessJwtToken(String rawToken, Claims claims) {
        this.rawToken = rawToken;
        this.claims = claims;
    }

    @Override
    public String getToken() {
        return rawToken;
    }

    public Claims getClaims() {
        return claims;
    }

}
