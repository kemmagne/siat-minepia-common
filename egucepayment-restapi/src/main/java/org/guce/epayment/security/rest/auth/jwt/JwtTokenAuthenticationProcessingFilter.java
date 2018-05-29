package org.guce.epayment.security.rest.auth.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.security.rest.auth.models.RawAccessJwtToken;
import org.guce.epayment.security.rest.config.RestSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 *
 * @author tadzotsa
 */
public class JwtTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationFailureHandler failureHandler;
    private final TokenExtractor tokenExtractor;

    @Autowired
    public JwtTokenAuthenticationProcessingFilter(AuthenticationFailureHandler failureHandler, TokenExtractor tokenExtractor, RequestMatcher matcher) {

        super(matcher);
        this.failureHandler = failureHandler;
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        final String tokenPayload = request.getHeader(RestSecurityConfig.JWT_TOKEN_HEADER_PARAM);
        final RawAccessJwtToken token = new RawAccessJwtToken(tokenExtractor.extract(tokenPayload));

        final Map<String, Object> infos = new HashMap<>();

        infos.put(RestConstants.LOGIN, request.getHeader(RestConstants.LOGIN));
        infos.put(RestConstants.BROWSER_FINGER_PRINT, request.getHeader(RestConstants.BROWSER_FINGER_PRINT));
        infos.put(RestConstants.REMOTE_ADR, RestUtils.getClientIp(request));

        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token, infos));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        final SecurityContext context = SecurityContextHolder.createEmptyContext();

        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

}
