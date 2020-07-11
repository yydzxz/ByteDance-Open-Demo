package com.yyd.bytedance.open.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yangyidian
 * @date 2020/06/19
 **/
@Component
@ConfigurationProperties(prefix = "bytedance.open")
@Data
public class ByteDanceOpenProperties {
    /**
     * 字节跳动三方平台的appid
     */
    private String componentAppId;

    /**
     * 字节跳动三方平台的app secret
     */
    private String componentSecret;

    /**
     * 字节跳动三方平台的token
     */
    private String componentToken;

    /**
     * 字节跳动三方平台的EncodingAESKey
     */
    private String componentAesKey;
}
