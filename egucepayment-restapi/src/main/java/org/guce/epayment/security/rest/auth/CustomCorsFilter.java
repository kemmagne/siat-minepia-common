package org.guce.epayment.security.rest.auth;

import java.util.Arrays;
import java.util.Map;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.security.rest.config.RestSecurityConfig;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 *
 * @author tadzotsa
 */
public class CustomCorsFilter extends CorsFilter {

    private static Map<String, String> PARAMS;

    public CustomCorsFilter(final Map<String, String> params) {

        super(configurationSource());
        PARAMS = params;
    }

    private static UrlBasedCorsConfigurationSource configurationSource() {

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
        config.addAllowedOrigin(PARAMS.get(RestConstants.ORIGIN));
        config.setExposedHeaders(Arrays.asList(exposedHeaders));
        config.setMaxAge(36000L);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/restapi/**", config);

        return source;
    }

}
