package org.guce.epayment.security.rest.auth.digest;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.security.rest.auth.models.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
public class DigestAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationFailureHandler failureHandler;

    @Autowired
    public DigestAuthenticationProcessingFilter(AuthenticationFailureHandler failureHandler, RequestMatcher matcher) {

        super(matcher);
        this.failureHandler = failureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        final String login = request.getHeader(RestConstants.LOGIN);
//        final String password = request.getHeader(RestConstants.PASSWORD);

        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, null);

        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        final SecurityContext context = SecurityContextHolder.createEmptyContext();

        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);

        try ( /**
                 * les requêtes qui utilisent cet url pattern (/.../digest/...)
                 * fournissent uniquement un login qui sert de préfix pour
                 * retrouver tous les credentials : clé secrète partagée, clé
                 * publique pour vérifier l'intégrité des données
                 */
                // on récupère le body de la requête
                final BufferedReader requestReader = request.getReader()) {
            final StringBuilder bodyBuilder = new StringBuilder();
            String line;
            while ((line = requestReader.readLine()) != null) {
                bodyBuilder.append(line);
            }
            final String body = bodyBuilder.toString().trim();
            final UserContext userContext = (UserContext) authResult.getPrincipal();
            final String login = userContext.getLogin();
        }

        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

}
