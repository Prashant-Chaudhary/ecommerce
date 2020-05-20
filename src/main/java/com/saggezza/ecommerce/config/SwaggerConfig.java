package com.saggezza.ecommerce.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This is swagger config class that manages the swagger configuration
 * 
 * @author tusharkansal
 *
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Value("${swagger.config.application-version}")
	private String applicationVersion;

	@Value("${swagger.config.base-package}")
	private String basePackage;

	@Value("${swagger.config.application-name}")
	private String applicationName;

	@Value("${swagger.config.application-description}")
	private String applicationDescription;

	@Value("${swagger.config.url-endpoints}")
	private List<String> urlEndPoints;

	@Bean
	public Docket apiDocument() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage(basePackage)).paths(getPostPaths()).build();
	}

	/**
	 * Returns the paths supported for documentation
	 * 
	 * @return
	 */
	private Predicate<String> getPostPaths() {
		Predicate<String> pathRegex = Predicates
				.or(urlEndPoints.stream().map(endPoint -> PathSelectors.regex(endPoint)).collect(Collectors.toList()));
		return pathRegex;
	}

	/**
	 * This method returns the api info
	 * 
	 * @return
	 */
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title(applicationName).description(applicationDescription)
				.version(applicationVersion).build();
	}

}
