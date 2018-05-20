package org.guce.epayment.security.rest.auth;

import org.guce.epayment.security.rest.auth.models.UserContext;
import org.guce.epayment.security.rest.auth.models.JwtToken;
import org.guce.epayment.security.rest.auth.jwt.JwtTokenFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.repositories.UserRepository;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.security.services.EncryptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author tadzotsa
 */
@Component
@PropertySources(value = @PropertySource("classpath:global-config.properties"))
public class AjaxAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${jwt.token.encryption}")
    private boolean jwtTokenEncryption;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private JwtTokenFactory tokenFactory;
    @Autowired
    private EncryptorService encryptorService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        final UserContext userContext = (UserContext) authentication.getPrincipal();

        final JwtToken accessToken = tokenFactory.createAccessJwtToken(userContext);

//        final Map<String, String> tokenMap = new HashMap<>();
//        tokenMap.put("token", jwtTokenEncryption ? encryptorService.encrypt(accessToken.getToken()) : accessToken.getToken());
//        tokenMap.put("login", userContext.getLogin());
//
        final User user = userRepository.findByLogin(userContext.getLogin()).get();
        final String token = jwtTokenEncryption ? encryptorService.encrypt(accessToken.getToken()) : accessToken.getToken();

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), RestUtils.getUserDto(user, Optional.of(token)));

        clearAuthenticationAttributes(request);
    }

    protected final void clearAuthenticationAttributes(HttpServletRequest request) {

        final HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}
