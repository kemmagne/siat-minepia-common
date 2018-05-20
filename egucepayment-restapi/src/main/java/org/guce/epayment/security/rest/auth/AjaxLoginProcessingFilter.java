package org.guce.epayment.security.rest.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.exceptions.AuthMethodNotSupportedException;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author tadzotsa
 */
public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxLoginProcessingFilter.class);

    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;

    public AjaxLoginProcessingFilter(String defaultProcessUrl, AuthenticationSuccessHandler successHandler,
            AuthenticationFailureHandler failureHandler) {

        super(defaultProcessUrl);
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (!HttpMethod.POST.name().equals(request.getMethod())) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Authentication method not supported. Request method: " + request.getMethod());
            }
            throw new AuthMethodNotSupportedException("Authentication method not supported");
        }

        final String login = request.getHeader(RestConstants.LOGIN);
        final String password = request.getHeader(RestConstants.PASSWORD);
        final String browserFingerPrint = request.getHeader(RestConstants.BROWSER_FINGER_PRINT);

        if (StringUtils.isBlank(login) || StringUtils.isBlank(password) || StringUtils.isBlank(browserFingerPrint)) {
            throw new AuthenticationServiceException("Some credentials are not provided");
        }

        final Map<String, String> credentials = new HashMap<>();

        credentials.put(RestConstants.PASSWORD, password);
        credentials.put(RestConstants.BROWSER_FINGER_PRINT, browserFingerPrint);
        credentials.put(RestConstants.REMOTE_ADR, RestUtils.getClientIp(request));

        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, credentials);

        return this.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {

        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

}
