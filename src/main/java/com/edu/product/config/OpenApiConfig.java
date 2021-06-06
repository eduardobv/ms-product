package com.edu.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI productOpenApi() {
		
		return new OpenAPI().openapi("3.0.3")
				  .info(new Info().title("Product API").description("Rest Product Managment").termsOfService("http://www.example.com/terms/")
						  .contact(new Contact().name("API Soft").url("http://www.example.com/support/").email("support@example.com"))
						  .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html"))
						  .version("1.0.0"));
	}
}
