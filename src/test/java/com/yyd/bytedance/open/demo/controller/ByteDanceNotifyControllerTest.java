package com.yyd.bytedance.open.demo.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest( webEnvironment = WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=unittest")
class ByteDanceNotifyControllerTest {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${local.server.port}")
    private int port;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void receiveTicket() {
        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {};
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json =
            "{\"nonce\": 54618697180, "
            + "\"timeStamp\":1596527768, "
            + "\"encrypt\":\"7e1bu4YdtcYxUUl919uedfHVRcPZgzQvZBhQJE2saVD9u6GZge8y6aJSW54Gj4qdVlliI+vivT/ChbRnRJebhGlj32E25uVosxNb07OM3icAalHmqekZp+Gt56h3ff5/L5IKvLoCNp+yVuWhEaf6bOygYmBkh9+2tce65l71UMHluIKrwJ5KYT4XhJDCwgzCS5TszznPLWVJ5RlQE8tnOkRSA29XDN6Rkx3mBTbRYq2SWuL1W1O1PPEW43ggGqhXa6OLBIc5vvJxX/Ov9yLb2A==\", "
            + "\"msgSignature\":\"e850fa3d03134cd0024e28d3608ca8bd8afa1716\""
            + "}";
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/bytedance/notify/receive_ticket", HttpMethod.POST, requestEntity, typeRef);
        String result = responseEntity.getBody();
        assertEquals("success", result);
    }

    @Test
    void callback() {
    }
}