package com.yyd.bytedance.open.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.yydzxz.open.api.response.appinfo.AppInfoResponse;
import com.yyd.bytedance.open.demo.config.TestAppInfoProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest( webEnvironment = WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=unittest")
class ByteDanceInfoControllerTest {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestAppInfoProperties testAppInfoProperties;

    @Test
    void appInfo() {
        ParameterizedTypeReference<AppInfoResponse> typeRef = new ParameterizedTypeReference<AppInfoResponse>() {};
        ResponseEntity<AppInfoResponse> responseEntity = restTemplate.exchange("http://localhost:" + port + "/bytedance/app/info?appid=" + testAppInfoProperties.getAppid(), HttpMethod.GET, new HttpEntity<>(null), typeRef);
        assertEquals(0, responseEntity.getBody().getErrno());
    }

    @Test
    void qrCode() {
    }

    @Test
    void checkAppName() {
    }

    @Test
    void modifyAppName() {
    }

    @Test
    void modifyAppIntro() {
    }

    @Test
    void modifyAppIcon() {
    }

    @Test
    void modifyServerDomain() {
    }

    @Test
    void modifyWebviewDomain() {
    }
}