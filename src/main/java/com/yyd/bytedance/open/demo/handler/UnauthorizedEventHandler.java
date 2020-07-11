package com.yyd.bytedance.open.demo.handler;

import com.yyd.open.api.IByteDanceOpenMessageHandler;
import com.yyd.open.bean.message.ByteDanceOpenMessage;
import com.yyd.open.bean.message.ByteDanceOpenMessageHandleResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 取消授权事件处理器
 * @author yangyidian
 * @date 2020/07/08
 **/
@Slf4j
@Component
public class UnauthorizedEventHandler implements IByteDanceOpenMessageHandler {

    @Override
    public ByteDanceOpenMessageHandleResult handle(ByteDanceOpenMessage message) {
        log.info("字节小程序[{}]取消授权", message.getAppId());
        return new ByteDanceOpenMessageHandleResult();
    }
}
