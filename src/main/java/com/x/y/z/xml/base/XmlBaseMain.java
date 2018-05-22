package com.x.y.z.xml.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.convert.ConversionService;

public class XmlBaseMain {
	private static final Logger logger = LoggerFactory.getLogger(XmlBaseMain.class);
	
	public static void main(String[] args) {

		logger.info("start...");
		String[] locations = {"classpath:RootContext.xml"};
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(locations);

		context.refresh();

		context.registerShutdownHook();

		MyValueCalculator c = (MyValueCalculator)context.getBean("myValueCalculator");
		System.out.println(c.computeValue("aaaa"));



	}
}
