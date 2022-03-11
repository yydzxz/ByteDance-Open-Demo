package com.yyd.bytedance.open.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author yangyidian
 * @date 2020/08/10
 **/
@Slf4j
@RestControllerAdvice
public class ByteDanceOpenDemoResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest serverHttpRequest,
        ServerHttpResponse serverHttpResponse) {
        if(body instanceof ByteDanceOpenDemoResponse){
            return body;
        }else if(body instanceof String){
            return body;
        }else if(body instanceof byte[]){
            return body;
        }
        return ByteDanceOpenDemoResponse.ok(body);
    }
}
