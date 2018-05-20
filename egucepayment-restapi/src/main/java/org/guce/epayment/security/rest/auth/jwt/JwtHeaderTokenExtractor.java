package org.guce.epayment.security.rest.auth.jwt;

import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.security.rest.auth.TokenExtractor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

/**
 *
 * @author tadzotsa
 */
@Component
public class JwtHeaderTokenExtractor implements TokenExtractor {

    public static String AUTHORIZATION_PREFIX = "Bearer ";

    @Override
    public String extract(String header) {

        if (StringUtils.isBlank(header)) {
            throw new AuthenticationServiceException("Authorization header cannot be blank!");
        }

        return header.replace(AUTHORIZATION_PREFIX, "");
    }

}
