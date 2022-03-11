package com.yyd.bytedance.open.demo.controller.v1;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.yydzxz.open.api.v1.response.appinfo.AppCheckAppNameResponse;
import com.github.yydzxz.open.api.v1.response.appinfo.AppCreditScoreResponse;
import com.github.yydzxz.open.api.v1.response.appinfo.AppInfoResponse;
import com.github.yydzxz.open.api.v1.response.appinfo.AppQualityRatingResponse;
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
class ByteDanceV1InfoControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private TestAppInfoProperties testAppInfoProperties;

    @DisplayName("获取授权小程序基本信息")
    @Test
    void appInfo() {
        ParameterizedTypeReference<ByteDanceOpenDemoResponse<AppInfoResponse>> typeRef = new ParameterizedTypeReference<ByteDanceOpenDemoResponse<AppInfoResponse>>() {};
        ResponseEntity<ByteDanceOpenDemoResponse<AppInfoResponse>> responseEntity = restTemplate.exchange("http://127.0.0.1:" + port + "/bytedance/v1/miniprogram/info?appid=" + testAppInfoProperties.getAppid(), HttpMethod.GET, null, typeRef);

        AppInfoResponse response = responseEntity.getBody().getData();
        assertThat(response.getErrno()).isEqualTo(0);
        assertThat(response.getData().getAppId()).isEqualTo(testAppInfoProperties.getAppid());
    }

    @DisplayName("获取小程序对应版本的二维码")
    @Test
    void qrCode() {

    }

    @DisplayName("小程序名称检测-名字正常")
    @Test
    void checkAppName() {
        String appName = "我的杂货铺";
        ParameterizedTypeReference<ByteDanceOpenDemoResponse<AppCheckAppNameResponse>> typeRef = new ParameterizedTypeReference<ByteDanceOpenDemoResponse<AppCheckAppNameResponse>>() {};
        ResponseEntity<ByteDanceOpenDemoResponse<AppCheckAppNameResponse>> responseEntity = restTemplate.exchange("http://127.0.0.1:" + port + "/bytedance/v1/miniprogram/check_app_name?appid=" + testAppInfoProperties.getAppid() + "&app_name=" + appName, HttpMethod.GET, null, typeRef);
        AppCheckAppNameResponse response = responseEntity.getBody().getData();
        assertThat(response.getErrno()).isEqualTo(0);
    }

    @DisplayName("小程序名称检测-名字有特殊字符")
    @Test
    void checkAppName2() {
        String appName = "%^&*(";
        ParameterizedTypeReference<ByteDanceOpenDemoResponse<AppCheckAppNameResponse>> typeRef = new ParameterizedTypeReference<ByteDanceOpenDemoResponse<AppCheckAppNameResponse>>() {};
        ResponseEntity<ByteDanceOpenDemoResponse<AppCheckAppNameResponse>> responseEntity = restTemplate.exchange("http://127.0.0.1:" + port + "/bytedance/v1/miniprogram/check_app_name?appid=" + testAppInfoProperties.getAppid() + "&app_name=" + appName, HttpMethod.GET, null, typeRef);
        AppCheckAppNameResponse response = responseEntity.getBody().getData();
        assertThat(response.getErrno()).isEqualTo(21001);
    }

    @DisplayName("小程序名称检测-名字太短")
    @Test
    void checkAppName3() {
        String appName = "aaa";
        ParameterizedTypeReference<ByteDanceOpenDemoResponse<AppCheckAppNameResponse>> typeRef = new ParameterizedTypeReference<ByteDanceOpenDemoResponse<AppCheckAppNameResponse>>() {};
        ResponseEntity<ByteDanceOpenDemoResponse<AppCheckAppNameResponse>> responseEntity = restTemplate.exchange("http://127.0.0.1:" + port + "/bytedance/v1/miniprogram/check_app_name?appid=" + testAppInfoProperties.getAppid() + "&app_name=" + appName, HttpMethod.GET, null, typeRef);
        AppCheckAppNameResponse response = responseEntity.getBody().getData();
        assertThat(response.getErrno()).isEqualTo(21001);
    }

    @DisplayName("小程序名称检测-名字太长")
    @Test
    void checkAppName4() {
        String appName = "aaaaaaaaaaaaaaaaaaaaa";
        ParameterizedTypeReference<ByteDanceOpenDemoResponse<AppCheckAppNameResponse>> typeRef = new ParameterizedTypeReference<ByteDanceOpenDemoResponse<AppCheckAppNameResponse>>() {};
        ResponseEntity<ByteDanceOpenDemoResponse<AppCheckAppNameResponse>> responseEntity = restTemplate.exchange("http://127.0.0.1:" + port + "/bytedance/v1/miniprogram/check_app_name?appid=" + testAppInfoProperties.getAppid() + "&app_name=" + appName, HttpMethod.GET, null, typeRef);
        AppCheckAppNameResponse response = responseEntity.getBody().getData();
        assertThat(response.getErrno()).isEqualTo(21001);
    }

    @DisplayName("查询质量评级信息")
    @Test
    void qualityRating() {
        ParameterizedTypeReference<ByteDanceOpenDemoResponse<AppQualityRatingResponse>> typeRef = new ParameterizedTypeReference<ByteDanceOpenDemoResponse<AppQualityRatingResponse>>() {};
        ResponseEntity<ByteDanceOpenDemoResponse<AppQualityRatingResponse>> responseEntity = restTemplate.exchange("http://127.0.0.1:" + port + "/bytedance/v1/miniprogram/quality_rating?appid=" + testAppInfoProperties.getAppid(), HttpMethod.GET, null, typeRef);

        AppQualityRatingResponse response = responseEntity.getBody().getData();
        assertThat(response.getErrno()).isEqualTo(0);
        assertThat(response.getData().getStatus()).isNotNull();
        //质量评级状态，1：未发布、未评级，2：已发布、评级中，3：已评级
        //如果状态是未评级或评级中，则 qualityRating 为空字符串
        if(response.getData().getStatus() == 1 || response.getData().getStatus() == 2){
            assertThat(response.getData().getQualityRating()).isBlank();
        }else {
            assertThat(response.getData().getQualityRating()).isNotBlank();
        }
    }

    @DisplayName("查询信用分分值")
    @Test
    void creditScore() {
        ParameterizedTypeReference<ByteDanceOpenDemoResponse<AppCreditScoreResponse>> typeRef = new ParameterizedTypeReference<ByteDanceOpenDemoResponse<AppCreditScoreResponse>>() {};
        ResponseEntity<ByteDanceOpenDemoResponse<AppCreditScoreResponse>> responseEntity = restTemplate.exchange("http://127.0.0.1:" + port + "/bytedance/v1/miniprogram/credit_score?appid=" + testAppInfoProperties.getAppid(), HttpMethod.GET, null, typeRef);

        AppCreditScoreResponse response = responseEntity.getBody().getData();
        assertThat(response.getErrno()).isEqualTo(0);

        assertThat(response.getData().getCreditScore()).isNotNull();
    }
}