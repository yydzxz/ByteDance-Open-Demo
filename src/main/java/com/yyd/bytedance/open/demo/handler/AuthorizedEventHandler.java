package com.yyd.bytedance.open.demo.handler;


import com.github.yydzxz.open.api.IByteDanceOpenMessageHandler;
import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.bean.message.ByteDanceOpenMessage;
import com.github.yydzxz.open.bean.message.ByteDanceOpenMessageHandleResult;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO
 * 授权的时候，字节跳动最后一步的重定向并不稳定，经常出错，所以还是需要这个handler来处理授权事件
 * @author yangyidian
 * @date 2020/07/10
 **/
@Slf4j
@Component
public class AuthorizedEventHandler implements IByteDanceOpenMessageHandler {

    @Autowired
    IByteDanceOpenService byteDanceOpenService;

    @Override
    public ByteDanceOpenMessageHandleResult handle(ByteDanceOpenMessage message, Map<String, Object> context) {
        return new ByteDanceOpenMessageHandleResult();
    }
}
