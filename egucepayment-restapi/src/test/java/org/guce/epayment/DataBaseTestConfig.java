package org.guce.epayment;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
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
@ComponentScan(basePackages = {"org.guce.epayment.core.dao", "org.guce.epayment.core.services", "org.guce.epayment.transfer.dao", "org.guce.epayment.transfer.services", "org.guce.epayment.campost.services"})
public class DataBaseTestConfig {

    @Value("classpath:mode.sql")
    private Resource mode;

    @Value("classpath:sample_data_test.sql")
    private Resource dataScript;

    private DatabasePopulator createDatabasePopulator() {

        final ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();

        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(mode);
        databasePopulator.addScript(dataScript);

        return databasePopulator;
    }

    @Bean
    public DataSource dataSource() throws Exception {

        final DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:hsqldb:mem:test;", "sa", "sa");

        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");

        DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);

        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {

        final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(false);

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

        jpaProperties.load(new ClassPathResource("jpa_properties_test.properties").getInputStream());

        return jpaProperties;
    }

    @Bean
    public JpaTransactionManager transactionManager() throws Exception {

        final JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

}
