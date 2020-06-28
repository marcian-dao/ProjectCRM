package com.mmm.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.mmm")
@PropertySources({
		@PropertySource("classpath:security-persistence-mysql.properties"),
		@PropertySource("classpath:thValidationErrors.properties"),
		@PropertySource("classpath:application.properties")
})
public class AppContextConfig {

	@Autowired
	private Environment env; 

	private int getIntProperty(String props) {

		String stringProps = env.getProperty(props);

		int intProps = Integer.parseInt(stringProps);

		return intProps;
	}

	@Bean
	public DataSource securityDataSource() {

		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

		try {

			securityDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");

		} catch (PropertyVetoException exc) {

			throw new RuntimeException(exc);
		}

		securityDataSource.setJdbcUrl(env.getProperty("security.jdbc.url"));
		securityDataSource.setUser(env.getProperty("security.jdbc.username"));
		securityDataSource.setPassword(env.getProperty("security.jdbc.password"));
		
		securityDataSource.setInitialPoolSize(getIntProperty("security.connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("security.connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("security.connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("security.connection.pool.maxIdleTime"));

		return securityDataSource;
	}

	private Properties getHibernateProperties() {
		
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		return props;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(securityDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
}
