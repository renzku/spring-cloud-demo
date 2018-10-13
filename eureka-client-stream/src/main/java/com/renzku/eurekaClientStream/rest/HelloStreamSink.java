package com.renzku.eurekaClientStream.rest;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class HelloStreamSink {

	@StreamListener(Sink.INPUT)
	public void input(String s){

		System.out.println("input:" + s);
	}
}
