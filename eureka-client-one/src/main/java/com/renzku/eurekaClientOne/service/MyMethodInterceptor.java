package com.renzku.eurekaClientOne.service;

import com.google.common.collect.HashBasedTable;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class MyMethodInterceptor implements MethodInterceptor{
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("said:" + Arrays.toString(objects));
		return methodProxy.invokeSuper(o, objects);
	}


	public static void main(String[] args){
		ArrayList arr =new ArrayList<>();
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(HelloCon.class);
		enhancer.setCallback(new MyMethodInterceptor());
		HelloCon helloCon = (HelloCon) enhancer.create();
		System.out.println(helloCon.sayHello("I fuck you"));

	}
}
