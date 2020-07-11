package com.yyd.bytedance.open.demo.handler;

import cn.hutool.json.JSONUtil;
import com.yyd.open.api.IByteDanceOpenMessageHandler;
import com.yyd.open.bean.message.ByteDanceOpenMessage;
import com.yyd.open.bean.message.ByteDanceOpenMessageHandleResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yangyidian
 * @date 2020/07/08
 **/
@Slf4j
@Component
public class LogHandler implements IByteDanceOpenMessageHandler {

    @Override
    public ByteDanceOpenMessageHandleResult handle(ByteDanceOpenMessage message) {
        log.info("LogHandler开始处理消息: {}", JSONUtil.toJsonStr(message));
        return new ByteDanceOpenMessageHandleResult();
    }
}
