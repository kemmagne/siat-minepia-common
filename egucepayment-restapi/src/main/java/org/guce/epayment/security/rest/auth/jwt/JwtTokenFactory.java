package org.guce.epayment.security.rest.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.core.utils.DateUtils;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.security.rest.auth.models.AccessJwtToken;
import org.guce.epayment.security.rest.auth.models.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author tadzotsa
 */
@Component
public class JwtTokenFactory {

    private final JwtSettings settings;

    @Autowired
    public JwtTokenFactory(final JwtSettings settings) {
        this.settings = settings;
    }

    public AccessJwtToken createAccessJwtToken(final UserContext userContext) {

        if (StringUtils.isBlank(userContext.getLogin())) {
            throw new IllegalArgumentException("Cannot create JWT Token without username");
        }

        if (CollectionUtils.isEmpty(userContext.getAuthorities())) {
            throw new IllegalArgumentException("User doesn't have any privileges");
        }

        Claims claims = Jwts.claims().setSubject(userContext.getLogin());
        claims.put(RestConstants.ROLES, userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));
        claims.put(RestConstants.BROWSER_FINGER_PRINT, userContext.getInfos().get(RestConstants.BROWSER_FINGER_PRINT));
        claims.put(RestConstants.REMOTE_ADR, userContext.getInfos().get(RestConstants.REMOTE_ADR));

        String token = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setClaims(claims)
                .setIssuer(settings.getApplicationIssuer())
                .setIssuedAt(DateUtils.asDate(LocalDateTime.now()))
                .setNotBefore(DateUtils.asDate(LocalDateTime.now()))
                //          .setExpiration(Date.from(currentTime.plusMinutes(settings.getTokenExpirationTime()).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getJwtSecretKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }

}
