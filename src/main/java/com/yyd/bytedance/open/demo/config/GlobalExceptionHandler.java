package com.yyd.bytedance.open.demo.config;

import com.github.yydzxz.common.error.ByteDanceErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yangyidian
 * @date 2020/08/07
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ByteDanceErrorException.class)
    public ByteDanceOpenDemoResponse handleByteDanceException(ByteDanceErrorException exception){
        ByteDanceOpenDemoResponse response = new ByteDanceOpenDemoResponse();
        response.setCode(10086);
        response.setMsg("字节跳动接口错误(＞﹏＜)");
        response.setData(exception.getError());
        return response;
    }

}
