package org.guce.epayment.security.rest.auth.jwt;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 *
 * @author tadzotsa
 */
@Configuration
@PropertySources(value = @PropertySource("classpath:global-config.properties"))
@Data
@Setter(AccessLevel.NONE)
public class JwtSettings {

    @Value("${application.issuer}")
    private String applicationIssuer;
    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

}
