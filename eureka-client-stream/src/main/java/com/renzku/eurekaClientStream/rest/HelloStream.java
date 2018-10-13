package com.renzku.eurekaClientStream.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloStream {

	@Autowired
	private HelloStreamSource helloStreamSource;

	@Autowired
	private HelloStreamCustomProcessorSource helloStreamCustomProcessorSource;

	@RequestMapping("/stream")
	public String HelloStream(){
		helloStreamSource.sendTestData();
		return "hello stream";
	}

	@RequestMapping("/stream/cust")
	public String HelloStreamCust(){
		helloStreamCustomProcessorSource.sendTestData();
		return "hello stream cust";
	}
}
