package com.renzku.eurekaClientTwo;

import com.google.common.base.Predicates;
import io.swagger.annotations.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableEurekaClient  // 开启eureka客户端
@EnableFeignClients(basePackages = {"com.renzku.eurekaClientTwo.feign"})  // 开启feign
@EnableCircuitBreaker  // 开启断路器-和 @HystrixCommand 组合使用
@EnableSwagger2
public class EurekaClientTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientTwoApplication.class, args);
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

}
