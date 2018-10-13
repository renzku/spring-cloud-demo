package com.renzku.eurekaClientTwo.rest;

import brave.Tracer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.renzku.eurekaClientTwo.feign.EurekaClientOneFeign;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignTest {

	@Value("${configTest.properties.str1}")
	String str1;

	@Value("${configTest.properties.str2}")
	String str2;

	@Autowired
	EurekaClientOneFeign eurekaClientOneFeign;  // 如果提示“No Beans Named***" 其实没问题，恶...

	@Autowired
	Tracer tracer;

//	@HystrixCommand(fallbackMethod = "someBoom")
	@ApiOperation(value = "feign 调用", notes = "通过feign调用client-one的接口")
	@GetMapping("/test/home")
	@ResponseStatus(HttpStatus.OK)
	public String testHome(){
		return eurekaClientOneFeign.home();
	}


	@HystrixCommand(fallbackMethod = "someBoom")
	@GetMapping("/")
	public String hello(){
		int a = 0, c = 1;
		int d = c/a;  // 抛出异常触发
		return "hello world -- client two";
	}

	/**
	 * 断路回调方法
	 */
	public String someBoom(){
		return "wokao, 魂淡---client two";
	}



}
