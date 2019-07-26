package com.usman.sample.scheduler.demo.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.usman.sample.scheduler.demo.repository.destination"},
        entityManagerFactoryRef = "destinationEntityManagerFactory",
        transactionManagerRef = "tempTransactionManager"

)
public class DestinationDataSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "datasource.destination")
    public DataSourceProperties destinationDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "destinationDataSource")
    public DataSource destinationDataSource() {
        DataSourceProperties destinationDataSourceProperties = destinationDataSourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(destinationDataSourceProperties.getDriverClassName())
                .url(destinationDataSourceProperties.getUrl())
                .username(destinationDataSourceProperties.getUsername())
                .password(destinationDataSourceProperties.getPassword())
                .build();
    }

    @Bean(name = "destinationTransactionManager")
    public PlatformTransactionManager destinationTransactionManager() {
        return new JpaTransactionManager(destinationEntityManagerFactory().getObject());
    }

    @Bean(name = "destinationEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean destinationEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(destinationDataSource());
        factory.setPackagesToScan("com.usman.sample.scheduler.demo.entity.destination");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setPersistenceUnitName("DestinationDb");
        return factory;

    }
}
