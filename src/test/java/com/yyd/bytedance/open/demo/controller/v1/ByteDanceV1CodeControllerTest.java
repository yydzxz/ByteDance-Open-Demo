package com.yyd.bytedance.open.demo.controller.v1;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.yydzxz.open.api.v1.response.code.CodeVersionsResponse;
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

@SpringBootTest( webEnvironment = WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=unittest")
class ByteDanceV1CodeControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private TestAppInfoProperties testAppInfoProperties;

    @DisplayName("获取小程序版本列表信息")
    @Test
    void versions() {
        ParameterizedTypeReference<ByteDanceOpenDemoResponse<CodeVersionsResponse>> typeRef = new ParameterizedTypeReference<ByteDanceOpenDemoResponse<CodeVersionsResponse>>() {};
        ResponseEntity<ByteDanceOpenDemoResponse<CodeVersionsResponse>> responseEntity = restTemplate.exchange("http://127.0.0.1:" + port + "/bytedance/v1/miniprogram/code/versions?appid=" + testAppInfoProperties.getAppid(), HttpMethod.GET, null, typeRef);

        CodeVersionsResponse response = responseEntity.getBody().getData();
        assertThat(response.getErrno()).isEqualTo(0);
        if(response.getData().getAudit() != null){
            CodeVersionsResponse.Audit audit = response.getData().getAudit();
            assertThat(audit.getDeveloperId()).isNotBlank();
            assertThat(audit.getDeveloperAvatar()).isNotBlank();
            assertThat(audit.getDeveloperName()).isNotBlank();
        }

        if(response.getData().getCurrent() != null){
            CodeVersionsResponse.Current current = response.getData().getCurrent();
            assertThat(current.getDeveloperId()).isNotBlank();
            assertThat(current.getDeveloperAvatar()).isNotBlank();
            assertThat(current.getDeveloperName()).isNotBlank();
        }

        if(response.getData().getLatest()!= null){
            CodeVersionsResponse.Latest latest = response.getData().getLatest();
            assertThat(latest.getDeveloperId()).isNotBlank();
            assertThat(latest.getDeveloperAvatar()).isNotBlank();
            assertThat(latest.getDeveloperName()).isNotBlank();
        }
    }
}