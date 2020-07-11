package com.yyd.bytedance.open.demo;

import com.yyd.common.redis.IByteDanceRedisOps;
import com.yyd.common.redis.RedissonByteDanceRedisOps;
import org.redisson.api.RedissonClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ByteDanceOpenDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ByteDanceOpenDemoApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    @Bean
    public IByteDanceRedisOps getByteDanceRedisOps(RedissonClient redissonClient){
        return new RedissonByteDanceRedisOps(redissonClient);
    }
}
