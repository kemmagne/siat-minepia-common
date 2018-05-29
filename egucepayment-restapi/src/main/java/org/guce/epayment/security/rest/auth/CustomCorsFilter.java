package org.guce.epayment.security.rest.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.security.rest.config.RestSecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 *
 * @author tadzotsa
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomCorsFilter extends CorsFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomCorsFilter.class);

    public CustomCorsFilter() {
        super(configurationSource());
    }

    private static UrlBasedCorsConfigurationSource configurationSource() {

        try {

            final CorsConfiguration config = new CorsConfiguration();

            final String[] headers = {
                RestConstants.LOGIN, RestConstants.PASSWORD, RestConstants.BROWSER_FINGER_PRINT, RestConstants.LOCALE,
                RestConstants.TOKEN, RestConstants.CONTENT_TYPE, RestConstants.ROLES, RestConstants.X_REQUEST_WITH,
                RestConstants.ORIGIN, RestSecurityConfig.JWT_TOKEN_HEADER_PARAM
            };
            final String[] exposedHeaders = {
                RestConstants.CONTENT_DISPOSITION, RestConstants.LOCATION
            };

            config.setAllowCredentials(true);
            config.setAllowedHeaders(Arrays.asList(headers));
            config.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name(),
                    HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name()));

            final Properties props = new Properties();

            props.load(new ClassPathResource("global-config.properties").getInputStream());

            config.addAllowedOrigin(props.getProperty("request.origins", "*"));
            config.setExposedHeaders(Arrays.asList(exposedHeaders));
            config.setMaxAge(36000L);

            final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

            source.registerCorsConfiguration("/restapi/*", config);

            return source;
        } catch (IOException ex) {
            LOGGER.error(null, ex);
            return null;
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {

            final String[] headers = {
                RestConstants.LOGIN, RestConstants.PASSWORD, RestConstants.BROWSER_FINGER_PRINT, RestConstants.LOCALE,
                RestConstants.TOKEN, RestConstants.CONTENT_TYPE, RestConstants.ROLES, RestConstants.X_REQUEST_WITH,
                RestConstants.ORIGIN, RestSecurityConfig.JWT_TOKEN_HEADER_PARAM
            };
            final String[] exposedHeaders = {
                RestConstants.CONTENT_DISPOSITION, RestConstants.LOCATION
            };
            final Properties props = new Properties();

            props.load(new ClassPathResource("global-config.properties").getInputStream());

            final String requestOrigins = props.getProperty("request.origins", "*");

            response.setHeader("Access-Control-Allow-Origin", requestOrigins);
            if (!"*".equals(requestOrigins.trim())) {
                response.setHeader("Vary", "Origin");
            }
            response.setHeader("Access-Control-Allow-Methods",
                    StringUtils.arrayToCommaDelimitedString(new String[]{HttpMethod.GET.name(),
                HttpMethod.HEAD.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
                HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name()}));
            response.setHeader("Access-Control-Allow-Headers",
                    StringUtils.arrayToCommaDelimitedString(headers));
            response.setHeader("Access-Control-Expose-Headers",
                    StringUtils.arrayToCommaDelimitedString(exposedHeaders));
        } catch (IOException ex) {
            LOGGER.error(null, ex);
        }

        if (!HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            super.doFilterInternal(request, response, filterChain);
        }
    }

}
