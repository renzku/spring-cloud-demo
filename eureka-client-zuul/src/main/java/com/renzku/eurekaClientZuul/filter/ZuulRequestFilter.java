package com.renzku.eurekaClientZuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;


public class ZuulRequestFilter extends ZuulFilter {

	public int filterOrder() {
		// run before PreDecorationFilter
		return 5 - 1;
	}

	public String filterType() {
		return "pre";
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	public Object run() {
		RequestContext ctx = getCurrentContext();
		ctx.addZuulRequestHeader("requestFilterParam", "here is RequestFilter!");
		return null;
	}
}
