package com.xmairtravel.core.config.spring.handler;

import com.alibaba.fastjson.JSONObject;
import com.xmairtravel.core.server.wechat.entity.ResultBean;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Liao
 * @version 1.0
 * @date 2021/8/23 8:31
 */
@ControllerAdvice("com.xmairtravel.*.api.controller.system.controller")
public class GlobalResponseHandler implements ResponseBodyAdvice {
    private static final String DATA = "data";

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    /**
     * 只有加了@ResponseBody的方法才会执行到这里
     * 在返回结果前执行
     * */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {


        if(o instanceof ResultBean || o instanceof JSONObject){
            return o;
        }

        return ResultBean.success(o);
    }
}