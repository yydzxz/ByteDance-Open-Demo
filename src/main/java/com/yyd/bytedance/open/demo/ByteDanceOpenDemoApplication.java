package com.yyd.bytedance.open.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
public class ByteDanceOpenDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ByteDanceOpenDemoApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate(){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(60*1000);
        requestFactory.setReadTimeout(2*60*1000);
        return new RestTemplate(requestFactory);
    }
}
