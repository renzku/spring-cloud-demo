package com.renzku.eurekaClientStream.rest;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(CustomProcessor.class)
public class HelloStreamCustomProcessorSink {

	@StreamListener(CustomProcessor.INPUT)
	public void input(String s){

		System.out.println("custom input:" + s);
	}
}
