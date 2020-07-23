package com.yyd.bytedance.open.demo.handler;

import com.github.yydzxz.open.api.IByteDanceOpenMessageHandler;
import com.github.yydzxz.open.bean.message.ByteDanceOpenMessage;
import com.github.yydzxz.open.bean.message.ByteDanceOpenMessageHandleResult;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yangyidian
 * @date 2020/07/23
 **/
@Slf4j
@Component
public class ModifyAppIntroEventHandler implements IByteDanceOpenMessageHandler {

    @Override
    public ByteDanceOpenMessageHandleResult handle(ByteDanceOpenMessage byteDanceOpenMessage, Map<String, Object> map) {
        return null;
    }
}
