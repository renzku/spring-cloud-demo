package com.renzku.eurekaClientZuul;

import com.google.common.base.Predicates;
import com.renzku.eurekaClientZuul.fallback.MyZuulFallbackProvider;
import com.renzku.eurekaClientZuul.filter.ZuulRequestFilter;
import com.renzku.eurekaClientZuul.filter.ZuulResponseFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableSwagger2
public class EurekaClientZuulApplication {

	@Autowired
	ZuulProperties zuulProperties;

	@Bean
	public ZuulRequestFilter requestFilter(){
		return new ZuulRequestFilter();
	}

	@Bean
	public ZuulResponseFilter responseFilter(){
		return new ZuulResponseFilter();
	}

	@Bean
	public FallbackProvider fallbackProvider(){
		return new MyZuulFallbackProvider();
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new org.springframework.web.filter.CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientZuulApplication.class, args);
	}

	@Component
	@Primary
	class DocumentationConfig implements SwaggerResourcesProvider {
		@Override
		public List<SwaggerResource> get() {
			List resources = new ArrayList();
			resources.add(swaggerResource("eureka-client-one", "/client-one/v2/api-docs", "1.0"));
			resources.add(swaggerResource("eureka-client-two", "/client-two/v2/api-docs", "1.0"));
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
