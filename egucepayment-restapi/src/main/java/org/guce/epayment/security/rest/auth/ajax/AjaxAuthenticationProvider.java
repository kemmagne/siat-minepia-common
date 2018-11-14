package org.guce.epayment.security.rest.auth.ajax;

import org.guce.epayment.security.rest.auth.models.UserContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author tadzotsa
 */
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CoreService coreService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        final String login = (String) authentication.getPrincipal();
        final Map<String, String> creds = (Map<String, String>) authentication.getCredentials();

        final User user = coreService.findByUniqueKey(Constants.UK_USER_LOGIN, login, User.class)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + login));
        user.getCredentialses().stream().findFirst()
                .filter(credentials -> passwordEncoder.matches(creds.get("password"), credentials.getPassword()))
                .orElseThrow(() -> new BadCredentialsException("Authentication Failed. Login or Password not valid."));

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        final Map<String, Object> infos = new HashMap<>();
        infos.put(RestConstants.BROWSER_FINGER_PRINT, creds.get(RestConstants.BROWSER_FINGER_PRINT));
        infos.put(RestConstants.REMOTE_ADR, creds.get(RestConstants.REMOTE_ADR));

        final UserContext userContext = UserContext.create(login, authorities, infos);

        return new UsernamePasswordAuthenticationToken(userContext, null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}

