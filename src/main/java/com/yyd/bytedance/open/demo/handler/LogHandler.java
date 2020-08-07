package com.yyd.bytedance.open.demo.handler;

import com.github.yydzxz.common.util.json.ByteDanceJsonBuilder;
import com.github.yydzxz.open.api.IByteDanceOpenMessageHandler;
import com.github.yydzxz.open.bean.message.ByteDanceOpenMessage;
import com.github.yydzxz.open.bean.message.ByteDanceOpenMessageHandleResult;
import java.util.Map;
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
    public ByteDanceOpenMessageHandleResult handle(ByteDanceOpenMessage message, Map<String, Object> context) {
        log.info("LogHandler开始处理消息: {}", ByteDanceJsonBuilder.instance().toJson(message));
        return new ByteDanceOpenMessageHandleResult();
    }
}
