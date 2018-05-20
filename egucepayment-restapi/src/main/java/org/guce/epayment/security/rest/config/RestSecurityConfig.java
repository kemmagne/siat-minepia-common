package org.guce.epayment.security.rest.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.core.entities.Role;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.security.rest.auth.AjaxAuthenticationProvider;
import org.guce.epayment.security.rest.auth.AjaxLoginProcessingFilter;
import org.guce.epayment.security.rest.auth.CustomCorsFilter;
import org.guce.epayment.security.rest.auth.jwt.JwtAuthenticationProvider;
import org.guce.epayment.security.rest.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import org.guce.epayment.security.rest.auth.RestAuthenticationEntryPoint;
import org.guce.epayment.security.rest.auth.SkipPathRequestMatcher;
import org.guce.epayment.security.rest.auth.TokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
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
@PropertySources(value = @PropertySource("classpath:global-config.properties"))
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${request.origins}")
    private String requestOrigins;

    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/restapi/auth/login";
    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/restapi/**";
    public static final String PUBLIC_URL_PATTERN = "/restapi/public/**";
    public static final String ADMIN_URL_PATTERN = "/restapi/admin/**";

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
    private TokenExtractor tokenExtractor;

    @Autowired
    private AuthenticationManager authenticationManager;

    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter() throws Exception {

        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(FORM_BASED_LOGIN_ENTRY_POINT, successHandler, failureHandler);
        filter.setAuthenticationManager(this.authenticationManager);

        return filter;
    }

    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {

        final List<String> pathsToSkip = Arrays.asList(FORM_BASED_LOGIN_ENTRY_POINT, PUBLIC_URL_PATTERN);
        final SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT);
        final JwtTokenAuthenticationProcessingFilter filter = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
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
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        final Map<String, String> params = new HashMap<>();

        params.put(RestConstants.ORIGIN, requestOrigins);

        http.csrf().disable() // We don't need CSRF for JWT based authentication
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(FORM_BASED_LOGIN_ENTRY_POINT, PUBLIC_URL_PATTERN).permitAll()
                .antMatchers(ADMIN_URL_PATTERN).hasAuthority(Role.ROLE_ADMIN)
                .and()
                .authorizeRequests()
                .antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated() // Protected API End-points
                .and()
                .addFilterBefore(new CustomCorsFilter(params), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public RoleVoter roleVoter() {

        final RoleVoter roleVoter = new RoleVoter();

        roleVoter.setRolePrefix(StringUtils.EMPTY);

        return roleVoter;
    }

}
