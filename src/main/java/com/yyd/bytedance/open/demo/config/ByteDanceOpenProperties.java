package com.yyd.bytedance.open.demo.config;

import java.util.List;
import javax.annotation.PostConstruct;
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

    private ServerDomain serverDomain;

    /**
     * 支付配置 salt
     */
    private String paySalt;

    /**
     * 支付配置 商户号
     */
    private String mchNo;

    @Data
    public static class ServerDomain{
        private List<String> request;
    }

    @PostConstruct
    public void check(){
        if("your componentAppId".equals(componentAppId)){
            throw new RuntimeException("请先在application-dev.yml或者docker-compose.yml中设置自己的第三方平台信息!");
        }
    }
}
