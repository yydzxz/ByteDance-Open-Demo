package com.yyd.bytedance.open.demo.config;

import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/08/10
 **/
@Data
public class ByteDanceOpenDemoResponse {
    private String msg;
    private Integer code;
    private Object data;
}
