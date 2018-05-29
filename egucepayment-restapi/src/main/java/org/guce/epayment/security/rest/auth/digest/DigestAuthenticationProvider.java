package org.guce.epayment.security.rest.auth.digest;

import org.guce.epayment.security.rest.auth.jwt.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.guce.epayment.security.rest.auth.models.UserContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 *
 * @author tadzotsa
 */
@Component
public class DigestAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        final String login = (String) authentication.getPrincipal();
//        final String password = (String) authentication.getCredentials();
//
//        final User user = coreService.findByUniqueKey(Constants.UK_USER_LOGIN, login, User.class)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + login));
//        user.getCredentialses().stream().findFirst()
//                .filter(credentials -> passwordEncoder.matches(password, credentials.getPassword()))
//                .orElseThrow(() -> new BadCredentialsException("Authentication Failed. Login or Password not valid."));

        final UserContext userContext = UserContext.create(login, new ArrayList<>(), new HashMap<>());

        return new UsernamePasswordAuthenticationToken(userContext, null, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
