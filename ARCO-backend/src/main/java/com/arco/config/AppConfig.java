package com.arco.config;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration

public class AppConfig{
		@Value("${spring.datasource.url}")
		private String datasourceUrl;

		@Value("${spring.datasource.driver-class-name}")
		private String dbDriverClassName;

		@Value("${spring.datasource.username}")
		private String dbUsername;

		@Value("${spring.datasource.password}")
		private String dbPassword;

		@Value("${spring.jpa.properties.hibernate.entitymanager.packagesToScan}")
		private String packagesToScan;

		@Value("${spring.jpa.properties.hibernate.dialect}")
		private String hibernateDialect;

		@Value("${spring.jpa.properties.hibernate.show-sql}")
		private String showSql;

		@Value("${spring.jpa.hibernate.ddl-auto}")
		private String ddlAuto;
		
		 @Bean
		  public WebMvcConfigurer corsConfigurer() {
		        return new WebMvcConfigurerAdapter() {
		        	@Override
		        	public void addCorsMappings(CorsRegistry registry) {
		        		registry.addMapping("/**")
		        		.allowedOrigins("*")
		        		.allowedMethods("POST", "GET",  "PUT", "OPTIONS", "DELETE")
		        		.allowedHeaders("X-Auth-Token", "Content-Type")
		        		.exposedHeaders("custom-header1", "custom-header2")
		        		.allowCredentials(true)
		        		.maxAge(4800);
			}
		  };
		 }


		@Bean
		public DataSource dataSource() {
			final DriverManagerDataSource dataSource = new DriverManagerDataSource();

			dataSource.setDriverClassName(dbDriverClassName);
			dataSource.setUrl(datasourceUrl);
			dataSource.setUsername(dbUsername);
			dataSource.setPassword(dbPassword);

			return dataSource;
		}
		
		

		@Bean
		public LocalSessionFactoryBean sessionFactory() {
			LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
			sessionFactoryBean.setDataSource(dataSource());
			sessionFactoryBean.setPackagesToScan(packagesToScan);
			Properties hibernateProperties = new Properties();
			hibernateProperties.put("hibernate.dialect", hibernateDialect);
			hibernateProperties.put("hibernate.show_sql", showSql);
			hibernateProperties.put("hibernate.id.new_generator_mappings","false");
			sessionFactoryBean.setHibernateProperties(hibernateProperties);

			return sessionFactoryBean;
		}

		@Bean
		public HibernateTransactionManager transactionManager() {
			HibernateTransactionManager transactionManager = 
					new HibernateTransactionManager();
			transactionManager.setSessionFactory(sessionFactory().getObject());
			return transactionManager;
		}




	}

