package org.guce.epayment.core.mail.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 *
 * @author tadzotsa
 */
@Configuration
@PropertySources(value = @PropertySource("classpath:global-config.properties"))
public class MailConfig {

    @Value("${mailSender.host}")
    private String host;
    @Value("${mailSender.port}")
    private int port;
    @Value("${mailSender.username}")
    private String username;
    @Value("${mailSender.password}")
    private String password;
    @Value("${smtpSecurityProcol}")
    private String smtpSecurityProcol;

    @Bean
    public FreeMarkerConfigurationFactoryBean freeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("/templates/");
        return bean;
    }

    @Bean
    public JavaMailSender mailSender() {

        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        final Properties javaMailProperties = new Properties();

        javaMailProperties.put("mail.smtp.auth", true);
        if (SMTPSecurityProcol.TLS.name().equalsIgnoreCase(smtpSecurityProcol)) {
            javaMailProperties.put("mail.smtp.starttls.enable", true);
        } else if (SMTPSecurityProcol.SSL.name().equalsIgnoreCase(smtpSecurityProcol)) {
            javaMailProperties.put("mail.smtp.socketFactory.port", port);
            javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }

        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }

    private enum SMTPSecurityProcol {

        NONE, SSL, TLS

    }

}
