package io.github.educastilho.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "io.github.educastilho.rest.controller")
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
					.useDefaultResponseMessages(false)
					.select()
					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any())
					.build()
					.securityContexts(Arrays.asList(securityContext()))
					.securitySchemes(Arrays.asList(apiKey()))
					.apiInfo(apiInfo());
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Vendas API")
				.description("Projeto de um API genérica para um Sistema de Vendas")
				.version("1.0")
				.contact(contact())
				.build();
	}
	
	private Contact contact() {
		return new Contact(
				"Eduardo Silva", 
				"https://github.com/Educastilho/Spring-Boot-Curso-Udemy-Implementation", 
				"castilhosouzas@gmail.com");
	}
	
	public ApiKey apiKey () {
		return new ApiKey("JWT", "Authorization", "Header");
		
	}
	
	private SecurityContext securityContext() {
		return SecurityContext
				.builder()
				.securityReferences(deafultAuth())
				.forPaths(PathSelectors.any())
				.build();
	}
	
	private List<SecurityReference> deafultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "access everything");
		AuthorizationScope [] scopes = new AuthorizationScope[1];
		scopes[0] = authorizationScope;
		SecurityReference reference = new SecurityReference("JWT", scopes);
		List<SecurityReference> auths = new ArrayList<>();
		auths.add(reference);
		return auths; 
	}
}
