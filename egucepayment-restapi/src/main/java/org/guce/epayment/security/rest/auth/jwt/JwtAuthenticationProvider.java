package org.guce.epayment.security.rest.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.security.rest.auth.models.RawAccessJwtToken;
import org.guce.epayment.security.rest.auth.models.UserContext;
import org.guce.epayment.security.services.EncryptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 *
 * @author tadzotsa
 */
@Component
@PropertySources(value = @PropertySource("classpath:global-config.properties"))
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Value("${jwt.token.encryption}")
    private boolean jwtTokenEncryption;

    private final JwtSettings jwtSettings;
    private final EncryptorService encryptorService;

    @Autowired
    public JwtAuthenticationProvider(final JwtSettings jwtSettings, final EncryptorService encryptorService) {
        this.jwtSettings = jwtSettings;
        this.encryptorService = encryptorService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

        if (jwtTokenEncryption) {
            final String encryptedToken = rawAccessToken.getToken();
            final String token = encryptorService.decrypt(encryptedToken);
            rawAccessToken = new RawAccessJwtToken(token);
        }

        final Map<String, Object> infos = (Map<String, Object>) authentication.getDetails();

        infos.put(RestConstants.APPLICATION_ISSUER, jwtSettings.getApplicationIssuer());

        final Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getJwtSecretKey(), infos);
        final String subject = jwsClaims.getBody().getSubject();
        final List<String> roles = jwsClaims.getBody().get(RestConstants.ROLES, List.class);
        final List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        final UserContext context = UserContext.create(subject, authorities, infos);

        return new JwtAuthenticationToken(context, context.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
