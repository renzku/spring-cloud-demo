package com.renzku.eurekaClientOne.rest;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

	@RequestMapping("/")
	@HystrixCommand(fallbackMethod =  "someBoom")
	public String home(){
		int a = 0, c = 1;
		int d = c/a;  // 抛出异常触发
		return "hello world";
	}

	public String someBoom(){
		return "wokao, 魂淡";
	}

	@RequestMapping("/zuul/test")
	public String zuulTest(@RequestHeader String requestFilterParam){
		return "requestFilterParam: " + requestFilterParam + " .--client one";
	}
}
