package com.seaway.springcloud.demo.zuul;

import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

public class MyPreZuulHeader extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyPreZuulHeader.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //before PreDecorationFilter
        return 100;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        LOGGER.info("自定义zuul pre filter");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getHeader("token");
        LOGGER.info("请求header token："+ Strings.nullToEmpty(token));
        LOGGER.info("测试只打印header token，实际可以校验权限、会话等。");
        return null;
    }
}
