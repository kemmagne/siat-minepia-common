package org.guce.epayment.core.dao.config;

import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author tadzotsa
 */
@Configuration
@PropertySources(value = @PropertySource("classpath:global-config.properties"))
@EnableTransactionManagement
@EnableJpaRepositories({"org.guce.epayment.core.repositories", "org.guce.epayment.campost.repositories"})
@ComponentScan(basePackages = {"org.guce.epayment.core.dao", "org.guce.epayment.transfer.dao"})
public class DataBaseConfig {

    @Value("${database.databasePlatform}")
    private String databasePlatform;

    @Value("${database.generateDdl}")
    private boolean generateDdl;

    @Bean
    public DataSource dataSource() throws Exception {

        final Properties props = new Properties();

        props.load(new ClassPathResource("datasource.properties").getInputStream());

        return new BoneCPDataSource(new BoneCPConfig(props));
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {

        final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        jpaVendorAdapter.setDatabasePlatform(databasePlatform);
        jpaVendorAdapter.setGenerateDdl(generateDdl);

        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {

        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(jpaProperties());
        entityManagerFactoryBean.setPackagesToScan("org.guce.epayment.core.entities", "org.guce.epayment.transfer.entities", "org.guce.epayment.campost.entities");

        return entityManagerFactoryBean;
    }

    private Properties jpaProperties() throws Exception {

        final Properties jpaProperties = new Properties();

        jpaProperties.load(new ClassPathResource("jpa_properties.properties").getInputStream());

        return jpaProperties;
    }

    @Bean
    public JpaTransactionManager transactionManager() throws Exception {

        final JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

}
