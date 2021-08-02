package com.reg.service.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.reg.service.controller")).paths(PathSelectors.any())
				.build().apiInfo(apiInfo());
	}

	// For swagger 3 use this url to access ui http://localhost:8081/swagger-ui/
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("reg-service")
				.description("This APi is for Registering and fetching the existing users").version("1.0")
				.contact(new Contact("Team Name", "", "Team Email Id")).license("Its for licence users only").build();
	}

}
