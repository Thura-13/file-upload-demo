package com.jdc.book.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@ComponentScan("com.jdc.book.root")
@Configuration
public class RootConfig {
	
	@Bean
	public DataSource dataSource() {
		var ds = new BasicDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/books_db");
		ds.setUsername("root");
		ds.setPassword("admin");
		return ds;
	}
	
	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate(DataSource ds) {
		return new NamedParameterJdbcTemplate(ds);
	}
	
	@Bean
	@Scope("prototype")
	public SimpleJdbcInsert jdbcInsert(DataSource ds) {
		return new SimpleJdbcInsert(ds);
	}
	
	@Bean
	ReloadableResourceBundleMessageSource messageSource() {
		var resourceBundle = new ReloadableResourceBundleMessageSource();
		resourceBundle.setBasename("classpath:messages");
		resourceBundle.setDefaultEncoding("UTF-8");
		return resourceBundle;
	}

	
	@Bean
	public Validator validator() {
		var validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}

}
