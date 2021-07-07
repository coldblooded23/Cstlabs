package com.cst.CstLabs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@SpringBootApplication
@EnableAutoConfiguration


@PropertySources({
@PropertySource(ignoreResourceNotFound=true, value="classpath:CstLabs.properties")
})
public class CstLabsApplication {
	private static final Logger LOGGER = LogManager.getLogger(CstLabsApplication.class);	
	
	public static void main(String[] args) {
		LOGGER.info("CstLabsApplication application is Started");
		SpringApplication.run(CstLabsApplication.class, args);
	}

}
