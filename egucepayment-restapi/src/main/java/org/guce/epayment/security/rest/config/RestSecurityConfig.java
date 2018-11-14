package org.guce.epayment.security.rest.config;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.core.entities.enums.RoleName;
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
                .antMatchers("/restapi/jwt/admin/**").hasAuthority(RoleName.ADMIN.name())
                .antMatchers("/restapi/jwt/cashier/**").hasAuthority(RoleName.CASHIER.name())
                //
                .antMatchers("/restapi/jwt/banks/accounts/*", "/restapi/banks/accounts/by-owner/*")
                .hasAnyAuthority(RoleName.ADMIN.name(), RoleName.PRINCIPAL.name())
                //
                .antMatchers("/restapi/jwt/statistics/transfer/orders/by-bank/last/*")
                .hasAnyAuthority(RoleName.CASHIER.name(), RoleName.AGENCY_CONTROLLER.name(),
                        RoleName.BANK_CONTROLLER.name(), RoleName.BANK_AUDITOR.name())
                //
                .antMatchers("/restapi/jwt/report/transfer/orders/pdf/**", "/restapi/jwt/report/transfer/orders/excel/**",
                        "/restapi/jwt/transfers/orders/filter")
                .hasAnyAuthority(RoleName.CASHIER.name(), RoleName.AGENCY_CONTROLLER.name(),
                        RoleName.BANK_CONTROLLER.name(), RoleName.BANK_AUDITOR.name(),
                        RoleName.BENEFICIARY.name(), RoleName.DECISION_MAKER.name())
                //
                .antMatchers("/restapi/jwt/transfers/orders/initiation/*")
                .hasAnyAuthority(RoleName.CASHIER.name(), RoleName.PRINCIPAL.name())
                //
                .antMatchers("/restapi/jwt/transfers/orders/validations")
                .hasAnyAuthority(RoleName.PRINCIPAL_CONTROLLER.name(),
                        RoleName.AGENCY_CONTROLLER.name(), RoleName.BANK_CONTROLLER.name())
                //
                .antMatchers("/restapi/jwt/transfers/orders/**")
                .hasAnyAuthority(RoleName.PRINCIPAL.name(), RoleName.PRINCIPAL_CONTROLLER.name(),
                        RoleName.PRINCIPAL_AUDITOR.name(), RoleName.CASHIER.name(),
                        RoleName.AGENCY_CONTROLLER.name(), RoleName.BANK_CONTROLLER.name(),
                        RoleName.BANK_AUDITOR.name())
                //
                .antMatchers("/restapi/jwt/transfers/orders/by-validator/**")
                .hasAnyAuthority(RoleName.PRINCIPAL.name(), RoleName.PRINCIPAL_CONTROLLER.name(),
                        RoleName.CASHIER.name(), RoleName.AGENCY_CONTROLLER.name(),
                        RoleName.BANK_CONTROLLER.name())
                //
                .antMatchers("/restapi/jwt/statistics/transfer/orders/by-beneficiary/last/*",
                        "/restapi/jwt/transfers/orders/acknowled/**", "/restapi/jwt/transfers/orders/stats")
                .hasAuthority(RoleName.BENEFICIARY.name())
                //
                .antMatchers("/restapi/jwt/statistics/transfer/orders/by-decision-maker/last/*")
                .hasAuthority(RoleName.DECISION_MAKER.name())
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

