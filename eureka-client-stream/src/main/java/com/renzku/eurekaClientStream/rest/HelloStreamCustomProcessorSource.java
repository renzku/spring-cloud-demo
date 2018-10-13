package com.renzku.eurekaClientStream.rest;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

@EnableBinding(CustomProcessor.class)
public class HelloStreamCustomProcessorSource {

	@Resource
	private MessageChannel customOutput;

	public void sendTestData() {
		String s = "custom source msg";
		this.customOutput.send(MessageBuilder.withPayload(s).build());
	}
}
