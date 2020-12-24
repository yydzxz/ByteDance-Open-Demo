package com.yyd.bytedance.open.demo.controller.v1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.github.yydzxz.open.api.v1.response.auth.AuthAppListResponse;
import com.github.yydzxz.open.api.v1.response.operation.OperationLiveApplicationStatusResponse;
import com.github.yydzxz.open.api.v1.response.operation.OperationPhoneNumberApplicationStatusResponse;
import com.github.yydzxz.open.api.v1.response.operation.OperationVideoApplicationStatusResponse;
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
class ByteDanceV1OperationControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private TestAppInfoProperties testAppInfoProperties;

    @DisplayName("查询「短视频挂载」能力申请状态")
    @Test
    void videoApplicationStatus() {
        ParameterizedTypeReference<ByteDanceOpenDemoResponse<OperationVideoApplicationStatusResponse>> typeRef = new ParameterizedTypeReference<ByteDanceOpenDemoResponse<OperationVideoApplicationStatusResponse>>() {};
        ResponseEntity<ByteDanceOpenDemoResponse<OperationVideoApplicationStatusResponse>> responseEntity = restTemplate.exchange("http://127.0.0.1:" + port + "/bytedance/v1/miniprogram/operation/video_application_status?appid=" + testAppInfoProperties.getAppid(), HttpMethod.GET, null, typeRef);

        OperationVideoApplicationStatusResponse response = responseEntity.getBody().getData();
        assertThat(response.getErrno()).isEqualTo(0);
        assertThat(response.getData().getStatus()).isNotNull();
        assertThat(response.getData().getStatus()).isBetween(1, 6);
        //「短视频挂载」能力申请状态对应的原因
        //如果状态是1（申请中）、2（申请通过）、5（可申请），则 reason 为空字符串
        if(response.getData().getStatus() == 1 || response.getData().getStatus() == 2 || response.getData().getStatus() == 5){
            assertThat(response.getData().getReason()).isBlank();
        }else {
            assertThat(response.getData().getReason()).isNotBlank();
        }
    }

    @DisplayName("查询「抖音直播组件」能力申请状态")
    @Test
    void liveApplicationStatus() {
        ParameterizedTypeReference<ByteDanceOpenDemoResponse<OperationLiveApplicationStatusResponse>> typeRef = new ParameterizedTypeReference<ByteDanceOpenDemoResponse<OperationLiveApplicationStatusResponse>>() {};
        ResponseEntity<ByteDanceOpenDemoResponse<OperationLiveApplicationStatusResponse>> responseEntity = restTemplate.exchange("http://127.0.0.1:" + port + "/bytedance/v1/miniprogram/operation/live_application_status?appid=" + testAppInfoProperties.getAppid(), HttpMethod.GET, null, typeRef);

        OperationLiveApplicationStatusResponse response = responseEntity.getBody().getData();
        assertThat(response.getErrno()).isEqualTo(0);
        assertThat(response.getData().getStatus()).isNotNull();
        assertThat(response.getData().getStatus()).isBetween(0, 6);
        //「抖音直播组件」申请状态对应的原因
        //如果状态是0（默认值）、1（可申请）、3（申请中）、4（申请通过），则 reason 为空字符串
        if(response.getData().getStatus() == 0 || response.getData().getStatus() == 1 || response.getData().getStatus() == 3 || response.getData().getStatus() == 4){
            assertThat(response.getData().getReason()).isBlank();
        }else {
            assertThat(response.getData().getReason()).isNotBlank();
        }
    }

    @DisplayName("查询「获取用户手机号」能力申请状态")
    @Test
    void phoneNumberApplicationStatus() {
        ParameterizedTypeReference<ByteDanceOpenDemoResponse<OperationPhoneNumberApplicationStatusResponse>> typeRef = new ParameterizedTypeReference<ByteDanceOpenDemoResponse<OperationPhoneNumberApplicationStatusResponse>>() {};
        ResponseEntity<ByteDanceOpenDemoResponse<OperationPhoneNumberApplicationStatusResponse>> responseEntity = restTemplate.exchange("http://127.0.0.1:" + port + "/bytedance/v1/miniprogram/operation/phone_number_application_status?appid=" + testAppInfoProperties.getAppid(), HttpMethod.GET, null, typeRef);

        OperationPhoneNumberApplicationStatusResponse response = responseEntity.getBody().getData();
        assertThat(response.getErrno()).isEqualTo(0);
        assertThat(response.getData().getStatus()).isNotNull();
        assertThat(response.getData().getStatus()).isBetween(0, 6);
        //「获取用户手机号」能力申请状态对应的原因
        //如果状态是0（默认值）、1（可申请）、3（申请中）、4（申请通过），则 reason 为空字符串
        if(response.getData().getStatus() == 0 || response.getData().getStatus() == 1 || response.getData().getStatus() == 3 || response.getData().getStatus() == 4){
            assertThat(response.getData().getReason()).isBlank();
        }else {
            assertThat(response.getData().getReason()).isNotBlank();
        }
    }
}