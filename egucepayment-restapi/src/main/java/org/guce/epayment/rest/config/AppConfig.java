package org.guce.epayment.rest.config;

import org.guce.epayment.core.config.CoreConfig;
import org.guce.epayment.core.dao.config.DataBaseConfig;
import org.guce.epayment.core.mail.config.MailConfig;
import org.guce.epayment.security.core.config.CoreSecurityConfig;
import org.guce.epayment.security.rest.config.RestSecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author tadzotsa
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.guce.epayment.rest.controllers"})
@Import({CoreConfig.class, DataBaseConfig.class, MailConfig.class, CoreSecurityConfig.class, RestSecurityConfig.class})
public class AppConfig {
}
