package org.guce.epayment.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author tadzotsa
 */
@Configuration
@ComponentScan(basePackages = {"org.guce.epayment.core.services", "org.guce.epayment.transfer.services", "org.guce.epayment.campost.services"})
public class CoreConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
