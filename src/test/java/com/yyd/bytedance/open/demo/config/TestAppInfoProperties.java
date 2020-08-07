package com.yyd.bytedance.open.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yangyidian
 * @date 2020/08/06
 **/
@Component
@ConfigurationProperties(prefix = "test-app-info")
@Data
public class TestAppInfoProperties {
    private String appid;
}
