package org.guce.epayment.security.rest.config;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import static org.guce.epayment.core.entities.Role.*;
import org.guce.epayment.security.rest.auth.ajax.AjaxAuthenticationProvider;
import org.guce.epayment.security.rest.auth.ajax.AjaxLoginProcessingFilter;
import org.guce.epayment.security.rest.auth.CustomCorsFilter;
import org.guce.epayment.security.rest.auth.jwt.JwtAuthenticationProvider;
import org.guce.epayment.security.rest.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import org.guce.epayment.security.rest.auth.RestAuthenticationEntryPoint;
import org.guce.epayment.security.rest.auth.SkipPathRequestMatcher;
import org.guce.epayment.security.rest.auth.jwt.TokenExtractor;
import org.guce.epayment.security.rest.auth.digest.DigestAuthenticationProcessingFilter;
import org.guce.epayment.security.rest.auth.digest.DigestAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author tadzotsa
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"org.guce.epayment.security.rest.auth"})
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";

    private static final String FORM_BASED_LOGIN_ENTRY_POINT = "/restapi/jwt/auth/login";
    private static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/restapi/jwt/**";
    private static final String PUBLIC_URL_PATTERN = "/restapi/jwt/public/**";
    private static final String DIGEST_URL_PATTERN = "/restapi/digest/**";

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;
    @Autowired
    private DigestAuthenticationProvider digestAuthenticationProvider;

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private AuthenticationManager authenticationManager;

    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter() throws Exception {

        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(FORM_BASED_LOGIN_ENTRY_POINT,
                successHandler, failureHandler);
        filter.setAuthenticationManager(this.authenticationManager);

        return filter;
    }

    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {

        final List<String> pathsToSkip = Arrays.asList(FORM_BASED_LOGIN_ENTRY_POINT, DIGEST_URL_PATTERN,
                PUBLIC_URL_PATTERN);
        final SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT);
        final JwtTokenAuthenticationProcessingFilter filter = new JwtTokenAuthenticationProcessingFilter(failureHandler,
                tokenExtractor, matcher);

        filter.setAuthenticationManager(this.authenticationManager);

        return filter;
    }

    protected DigestAuthenticationProcessingFilter buildDigestAuthenticationProcessingFilter() throws Exception {

        final List<String> pathsToSkip = Arrays.asList(FORM_BASED_LOGIN_ENTRY_POINT, TOKEN_BASED_AUTH_ENTRY_POINT,
                PUBLIC_URL_PATTERN);
        final SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, DIGEST_URL_PATTERN);
        final DigestAuthenticationProcessingFilter filter = new DigestAuthenticationProcessingFilter(failureHandler,
                matcher);
        filter.setAuthenticationManager(this.authenticationManager);

        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(ajaxAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
        auth.authenticationProvider(digestAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable() // We don't need CSRF for JWT based authentication
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(FORM_BASED_LOGIN_ENTRY_POINT, PUBLIC_URL_PATTERN).permitAll()
                .antMatchers("/restapi/jwt/admin/**").hasAuthority(ROLE_ADMIN)
                .antMatchers("/restapi/jwt/cashier/**").hasAuthority(ROLE_CAISSIER)
                //
                .antMatchers("/restapi/jwt/banks/accounts/*", "/restapi/banks/accounts/by-owner/*")
                .hasAnyAuthority(ROLE_ADMIN, ROLE_DONNEUR_ORDRE)
                //
                .antMatchers("/restapi/jwt/statistics/transfer/orders/by-bank/last/*")
                .hasAnyAuthority(ROLE_CAISSIER, ROLE_CONTROLEUR_AGENCE, ROLE_CONTROLEUR_BANQUE,
                        ROLE_AUDITEUR_BANQUE)
                //
                .antMatchers("/restapi/jwt/report/transfer/orders/pdf/**", "/restapi/jwt/report/transfer/orders/excel/**",
                        "/restapi/jwt/transfers/orders/filter")
                .hasAnyAuthority(ROLE_CAISSIER, ROLE_CONTROLEUR_AGENCE, ROLE_CONTROLEUR_BANQUE,
                        ROLE_AUDITEUR_BANQUE, ROLE_BENEFICIAIRE, ROLE_DECIDEUR)
                //
                .antMatchers("/restapi/jwt/transfers/orders/initiation/*")
                .hasAnyAuthority(ROLE_CAISSIER, ROLE_DONNEUR_ORDRE)
                //
                .antMatchers("/restapi/jwt/transfers/orders/validations")
                .hasAnyAuthority(ROLE_CONTROLEUR_DONNEUR_ORDRE, ROLE_CONTROLEUR_AGENCE, ROLE_CONTROLEUR_BANQUE)
                //
                .antMatchers("/restapi/jwt/transfers/orders/**")
                .hasAnyAuthority(ROLE_DONNEUR_ORDRE, ROLE_CONTROLEUR_DONNEUR_ORDRE, ROLE_AUDITEUR_DONNEUR_ORDRE,
                        ROLE_CAISSIER, ROLE_CONTROLEUR_AGENCE, ROLE_CONTROLEUR_BANQUE, ROLE_AUDITEUR_BANQUE)
                //
                .antMatchers("/restapi/jwt/transfers/orders/by-validator/**")
                .hasAnyAuthority(ROLE_DONNEUR_ORDRE, ROLE_CONTROLEUR_DONNEUR_ORDRE,
                        ROLE_CAISSIER, ROLE_CONTROLEUR_AGENCE, ROLE_CONTROLEUR_BANQUE)
                //
                .antMatchers("/restapi/jwt/statistics/transfer/orders/by-beneficiary/last/*",
                        "/restapi/jwt/transfers/orders/acknowled/**", "/restapi/jwt/transfers/orders/stats")
                .hasAuthority(ROLE_BENEFICIAIRE)
                //
                .antMatchers("/restapi/jwt/statistics/transfer/orders/by-decision-maker/last/*")
                .hasAuthority(ROLE_DECIDEUR)
                .and()
                .authorizeRequests()
                .antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT, DIGEST_URL_PATTERN).authenticated()
                .and()
                .addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildDigestAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public RoleVoter roleVoter() {

        final RoleVoter roleVoter = new RoleVoter();

        roleVoter.setRolePrefix(StringUtils.EMPTY);

        return roleVoter;
    }

}
