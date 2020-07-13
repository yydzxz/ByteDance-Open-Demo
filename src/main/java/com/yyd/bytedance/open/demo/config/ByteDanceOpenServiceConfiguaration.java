package com.yyd.bytedance.open.demo.config;

import com.yyd.common.redis.RedissonByteDanceRedisOps;
import com.yyd.common.service.impl.RestTemplateByteDanceHttpRequestServiceImpl;
import com.yyd.open.api.IByteDanceOpenService;
import com.yyd.open.api.impl.ByteDanceOpenComponentServiceImpl;
import com.yyd.open.api.impl.ByteDanceOpenInRedisConfigStorage;
import com.yyd.open.api.impl.ByteDanceOpenServiceImpl;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * ByteDanceOpenServiceConfiguaration
 *
 * @author Clevo
 * @date 2020/7/12
 */
@Configuration
public class ByteDanceOpenServiceConfiguaration {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public IByteDanceOpenService getIByteDanceOpenService(){
        ByteDanceOpenServiceImpl byteDanceOpenService = new ByteDanceOpenServiceImpl();
        byteDanceOpenService.setByteDanceHttpRequestService(new RestTemplateByteDanceHttpRequestServiceImpl(restTemplate));
        RedissonByteDanceRedisOps  redissonByteDanceRedisOps = new RedissonByteDanceRedisOps(redissonClient);
        byteDanceOpenService.setRedissonByteDanceRedisOps(redissonByteDanceRedisOps);
        byteDanceOpenService.setByteDanceOpenComponentService(new ByteDanceOpenComponentServiceImpl(byteDanceOpenService));
        ByteDanceOpenInRedisConfigStorage inRedisConfigStorage = new ByteDanceOpenInRedisConfigStorage(redissonByteDanceRedisOps, "yourprefix");
        inRedisConfigStorage.setComponentAppId("Your ComponentAppId");
        inRedisConfigStorage.setComponentAppSecret("Your ComponentAppSecret");
        inRedisConfigStorage.setComponentToken("Your ComponentToken");
        inRedisConfigStorage.setComponentAesKey("Your ComponentAesKey");
        byteDanceOpenService.setByteDanceOpenConfigStorage(inRedisConfigStorage);
        return byteDanceOpenService;
    }

}
