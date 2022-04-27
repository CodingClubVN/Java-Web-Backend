package com.se.codingclub.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.se.codingclub.dto.Auth;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.se.codingclub")
@PropertySource({ "classpath:applications.properties" })
public class AppConfig implements WebMvcConfigurer {
	@Autowired
	private Environment evn;

	private Logger logger = Logger.getLogger(getClass().getName());

	@Bean
	public DataSource myDataSource() {

		ComboPooledDataSource myDataSource = new ComboPooledDataSource();

		try {
			myDataSource.setDriverClass(evn.getProperty("jdbc.driver"));

		} catch (PropertyVetoException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

		logger.info("jdbc.url=" + evn.getProperty("jdbc.url"));
		logger.info("jdbc.user=" + evn.getProperty("jdbc.user"));

//		set database connection props

		myDataSource.setJdbcUrl(evn.getProperty("jdbc.url"));
		myDataSource.setUser(evn.getProperty("jdbc.user"));
		myDataSource.setPassword(evn.getProperty("jdbc.password"));
//		set connection pool props

		myDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		myDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		myDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		myDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return myDataSource;

	}

	private Properties getHibernateProperties() {

		Properties properties = new Properties();

		properties.setProperty("hibernate.dialect", evn.getProperty("hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", evn.getProperty("hibernate.show_sql"));

		return properties;

	}

	private int getIntProperty(String propName) {
		String propVal = evn.getProperty(propName);
		int intPropVal = Integer.parseInt(propVal);
		return intPropVal;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {

		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

		sessionFactoryBean.setDataSource(myDataSource());
		sessionFactoryBean.setPackagesToScan(evn.getProperty("hibernate.packagesToScan"));
		sessionFactoryBean.setHibernateProperties(getHibernateProperties());
		return sessionFactoryBean;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txtManager = new HibernateTransactionManager();
		txtManager.setSessionFactory(sessionFactory);
		return txtManager;
	}

	@Override
	  public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(bearerTokenInterceptor());
	  }

	  @Bean
	  public BearerTokenInterceptor bearerTokenInterceptor() {
	      return new BearerTokenInterceptor(bearerTokenWrapper());
	  }

	  @Bean
	  @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	  public Auth bearerTokenWrapper() {
	    return new Auth();
	  }


	@Bean
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
}
