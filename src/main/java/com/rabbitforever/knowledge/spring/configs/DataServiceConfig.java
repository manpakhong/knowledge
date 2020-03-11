package com.rabbitforever.knowledge.spring.configs;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



/**
 * Created by iuliana.cosmina on 7/14/17.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.rabbitforever.knowledge.repos"})

@ComponentScan(basePackages = {"com.rabbitforever.knowledge"})
@PropertySource(value = {
	    "classpath:application.properties"
	})
public class DataServiceConfig {

	private static Logger logger = LoggerFactory.getLogger(DataServiceConfig.class);

    @Autowired
    private Environment environment;
    
    

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String driverClassName = environment.getRequiredProperty("jdbc.driver");
        String url = environment.getRequiredProperty("jdbc.url");
        String username = environment.getRequiredProperty("jdbc.username");
        String password = environment.getRequiredProperty("jdbc.password");
        
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
    
    /************* Start Spring JPA config details **************/
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setPackagesToScan("com.rabbitforever.knowledge.models.eos");
        lcemfb.setJpaVendorAdapter(getJpaVendorAdapter());
        lcemfb.setDataSource(dataSource());
        lcemfb.setPersistenceUnitName("myJpaPersistenceUnit");
        lcemfb.setJpaProperties(hibernateProperties());
        return lcemfb;
    }
//	@Bean
//	public EntityManagerFactory entityManagerFactory() {
//		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//		factoryBean.setPackagesToScan("com.apress.prospring5.entities");
//		factoryBean.setDataSource(dataSource());
//		factoryBean.setJpaProperties(hibernateProperties());
//		factoryBean.setJpaVendorAdapter(getJpaVendorAdapter());
//		factoryBean.afterPropertiesSet();
//		return factoryBean.getNativeEntityManagerFactory();
//	}


    @Bean(name = "transactionManager")
    public PlatformTransactionManager txManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(
            getEntityManagerFactoryBean().getObject());
        return jpaTransactionManager;
    }

    /************* End Spring JPA config details **************/


    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }
    @Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return adapter;
    }

}
