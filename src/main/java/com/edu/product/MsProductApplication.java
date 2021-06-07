package com.edu.product;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MsProductApplication {

	public static void main(String[] args) { 
		new SpringApplicationBuilder(MsProductApplication.class)
			.web(WebApplicationType.SERVLET)
			.run(args);
	}

}
