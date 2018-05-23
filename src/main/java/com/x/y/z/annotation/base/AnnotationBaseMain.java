package com.x.y.z.annotation.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@ComponentScan
@EnableWebMvc
public class AnnotationBaseMain extends WebMvcConfigurationSupport {
	private static final Logger logger = LoggerFactory.getLogger(AnnotationBaseMain.class);


	public AnnotationBaseMain(){
		System.out.println("=======[Initialize application]===========");
	}

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
