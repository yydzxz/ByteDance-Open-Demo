package com.yyd.bytedance.open.demo.config;

import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/08/10
 **/
@Data
public class ByteDanceOpenDemoResponse<T> {
    private String msg;
    private Integer code;
    private T data;
}
