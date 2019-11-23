package com.edu.usbcali.wst_database_trace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.edu.usbcali.wst_database_trace"))
				.paths(PathSelectors.regex("/.*")).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Trace Database API").description("Permite realizar el seguimiento y control a las bases de datos, a través de la toma de huellas de sus estructuras y la comparación de las mismas, para su validación.")
				.termsOfServiceUrl("http://www.javainuse.com").license("MIT License")
				.contact(
						new Contact("Smart Campus", "http://smartcampus.uniajc.edu.co", "faccsistemas@gmail.com"))
				.version("1.0").build();

	}

}
