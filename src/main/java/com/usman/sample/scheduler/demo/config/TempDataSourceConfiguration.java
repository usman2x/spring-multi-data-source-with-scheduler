package com.usman.sample.scheduler.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "tempEntityManagerFactory",
        transactionManagerRef = "tempTransactionManager",
        basePackages = {"com.usman.sample.scheduler.demo.repository.temp"}
)
public class TempDataSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "datasource.temp")
    public DataSourceProperties tempDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "tempDataSource")
    public DataSource tempDataSource() {
        DataSourceProperties destinationDataSourceProperties = tempDataSourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(destinationDataSourceProperties.getDriverClassName())
                .url(destinationDataSourceProperties.getUrl())
                .username(destinationDataSourceProperties.getUsername())
                .password(destinationDataSourceProperties.getPassword())
                .build();
    }

    @Bean(name = "tempTransactionManager")
    public PlatformTransactionManager tempTransactionManager() {
        return new JpaTransactionManager(tempEntityManagerFactory().getObject());
    }

    @Bean(name = "tempEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean tempEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(tempDataSource());
        factory.setPackagesToScan("com.usman.sample.scheduler.demo.entity.temp");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setPersistenceUnitName("TempDb");
        return factory;

    }
}
