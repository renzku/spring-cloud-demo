package com.renzku.eurekaClientSwagger;

import com.google.common.base.Predicates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class EurekaClientSwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientSwaggerApplication.class, args);
	}

	@Bean
	public Docket createRestApi(){
		String basePackage = "com.renzku.eurekaClientTwo.rest";
		String pathNotSelectReg = "(/error.*)|(/actuator.*)";  // 不包含 /error  和 /actuator
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage(basePackage))  // 这里目测没设置会扫描所有包
				.paths(Predicates.not(PathSelectors.regex(pathNotSelectReg)))
				.build();
	}

	private ApiInfo apiInfo(){
		Contact contact = new Contact("renzku", "https://github.com/renzku", "");
		List vendorExtensions  = new ArrayList();
		return new ApiInfo(
				"Swagger 接口文档",
				"eureka-client-two doc.",
				"1.0",
				"urn:tos",
				contact,
				"Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0",
				vendorExtensions);
	}

	@Component
	@Primary
	class DoucmentationConfig implements SwaggerResourcesProvider {
		@Override
		public List<SwaggerResource> get() {
			List resources = new ArrayList();
			resources.add(swaggerResource("eureka-client-one", "http://localhost:8601/client-one/v2/api-docs", "1.0"));
			resources.add(swaggerResource("eureka-client-two", "http://localhost:8601/client-two/v2/api-docs", "1.0"));
			return resources;
		}

		private SwaggerResource swaggerResource(String name, String location, String version){
			SwaggerResource swaggerResource = new SwaggerResource();
			swaggerResource.setName(name);
			swaggerResource.setLocation(location);
			swaggerResource.setSwaggerVersion(version);
			return swaggerResource;
		}
	}
}
