package com.yyd.bytedance.open.demo.handler;

import com.github.yydzxz.open.message.ByteDanceOpenMessage;
import com.github.yydzxz.open.message.ByteDanceOpenMessageHandleResult;
import com.github.yydzxz.open.message.IByteDanceOpenMessageHandler;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 代码审核事件处理
 * @author yangyidian
 * @date 2020/07/17
 **/
@Slf4j
@Component
public class CodeAuditEventHandler implements IByteDanceOpenMessageHandler {

    @Override
    public ByteDanceOpenMessageHandleResult handle(ByteDanceOpenMessage message, Map<String, Object> context) {
        return null;
    }
}
