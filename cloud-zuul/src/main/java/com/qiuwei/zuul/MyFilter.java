package com.qiuwei.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 * 安全校验 过滤器 模拟认证
 *
 * @Author: qiuweib@yonyou.com
 * @Date: 2020-08-30.
 */

@Component
public class MyFilter extends ZuulFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);

    /**
     * 主要是配置过滤器的生命周期
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤的顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 这里可以写逻辑判断，是否要过滤，本文true,永远过滤
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        LOGGER.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));

        /**
         * 此处模拟校验token  从url中看是否包含 token 字符串
         */
        String url = request.getRequestURL().toString();

        /**
         * 表示路劲里有token 字符串
         */
        if (url.indexOf("token") != -1) {

            LOGGER.info("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);

            try {
                ctx.getResponse().getWriter().write("token is empty");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("KO");
        return null;
    }

}
