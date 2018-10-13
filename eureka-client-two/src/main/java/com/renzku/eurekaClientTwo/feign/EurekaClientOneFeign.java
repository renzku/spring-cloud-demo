package com.renzku.eurekaClientTwo.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  eureka-client-one注册时的 spring.application.name
 */
@FeignClient(value = "eureka-client-one", fallback = HystrixFallback.class)
public interface EurekaClientOneFeign {

	@RequestMapping("/")
	String home();
}

/**
 * feign 的短路回调： 用 fallback
 */
@Component
class HystrixFallback implements EurekaClientOneFeign{
	@Override
	public String home() {
		return "feign fallback,魂淡";
	}
}

/**
 * feign 的短路回调--带上获取失败抛出的异常： 用 fallbackFactory
 */
@Component
class HystrixFallbackFactory implements FallbackFactory<EurekaClientOneFeign>{
	@Override
	public EurekaClientOneFeign create(Throwable throwable) {
		return ()-> ("feign fallback,魂淡: " + throwable.toString());
	}
}