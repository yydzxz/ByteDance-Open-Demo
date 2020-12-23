package com.yyd.bytedance.open.demo.controller.v1;

import com.github.yydzxz.open.api.v1.response.auth.AuthAppListResponse;
import com.yyd.bytedance.open.demo.config.ByteDanceOpenDemoResponse;
import com.yyd.bytedance.open.demo.config.TestAppInfoProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest( webEnvironment = WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=dev")
class ByteDanceV1AuthControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private TestAppInfoProperties testAppInfoProperties;

    @Test
    void getAuthorizerAccessToken() {
    }

    @DisplayName("查询所有授权给第三方平台的小程序列表")
    @Test
    void authAppList() {
        ParameterizedTypeReference<ByteDanceOpenDemoResponse<AuthAppListResponse>> typeRef = new ParameterizedTypeReference<ByteDanceOpenDemoResponse<AuthAppListResponse>>() {};
        ResponseEntity<ByteDanceOpenDemoResponse<AuthAppListResponse>> responseEntity = restTemplate.exchange("http://127.0.0.1:" + port + "/bytedance/v1/auth/auth_app_list", HttpMethod.GET, null, typeRef);

        AuthAppListResponse authAppListResponse = responseEntity.getBody().getData();
        assertThat(authAppListResponse.getErrno()).isEqualTo(0);
        if(authAppListResponse.getData().getAuthAppList() == null || authAppListResponse.getData().getAuthAppList().isEmpty()){
            throw new RuntimeException("没有授权小程序");
        }
        assertThat(authAppListResponse.getData().getAuthAppList().get(0).getAuthAppId()).isNotBlank();
        assertThat(authAppListResponse.getData().getAuthAppList().get(0).getAuthTime()).isNotNull();
    }
}