package com.renzku.eurekaClientStream.rest;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

@EnableBinding(Source.class)
public class HelloStreamSource {

	@Resource
	private MessageChannel output;

	public void sendTestData() {
		String s = "source msg";
		this.output.send(MessageBuilder.withPayload(s).build());
	}
}
