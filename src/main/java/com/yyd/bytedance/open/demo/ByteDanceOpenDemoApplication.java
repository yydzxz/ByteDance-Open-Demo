package com.yyd.bytedance.open.demo;

import com.github.yydzxz.common.redis.IByteDanceRedisOps;
import com.github.yydzxz.common.redis.RedissonByteDanceRedisOps;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Slf4j
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
