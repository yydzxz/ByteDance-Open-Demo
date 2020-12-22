package com.yyd.bytedance.open.demo.controller.v1;

import com.yyd.bytedance.open.demo.config.ByteDanceOpenDemoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest( webEnvironment = WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=unittest")
class ByteDanceV1AuthControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void getAuthorizerAccessToken() {
    }

    @Test
    void authAppList() {
        ByteDanceOpenDemoResponse response = restTemplate.getForEntity("http://localhost:" + port + "/bytedance/v1/auth/bytedance/v1/auth", ByteDanceOpenDemoResponse.class).getBody();
    }
}