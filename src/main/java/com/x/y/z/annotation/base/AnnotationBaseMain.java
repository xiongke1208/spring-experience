package com.x.y.z.annotation.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;

@Configuration
@ComponentScan
public class AnnotationBaseMain {
	private static final Logger logger = LoggerFactory.getLogger(AnnotationBaseMain.class);


	@Bean
	public PropertyPlaceholderConfigurer PropertyPlaceholderConfigurer(ApplicationContext context){
		PropertyPlaceholderConfigurer c = new PropertyPlaceholderConfigurer();
		c.setLocations(context.getResource("classpath:config.properties"));
		return c;
	}

	public static void main(String[] args) {

		ApplicationContext context =
				new AnnotationConfigApplicationContext(AnnotationBaseMain.class);
		User u = (User)context.getBean("user");
		System.out.println(u.getAge());
	}
}
