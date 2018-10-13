package com.renzku.eurekaClientStream.rest;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 自定义消息通道
 */
public interface CustomProcessor {
	String INPUT = "customInput";
	String OUTPUT = "customOutput";

	@Input(CustomProcessor.INPUT)
	MessageChannel customInput();

	@Output(CustomProcessor.OUTPUT)
	MessageChannel customOutput();
}
