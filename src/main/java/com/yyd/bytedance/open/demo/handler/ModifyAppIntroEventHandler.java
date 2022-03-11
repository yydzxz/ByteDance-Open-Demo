package com.yyd.bytedance.open.demo.handler;

import com.github.yydzxz.open.message.ByteDanceOpenMessage;
import com.github.yydzxz.open.message.ByteDanceOpenMessageHandleResult;
import com.github.yydzxz.open.message.IByteDanceOpenMessageHandler;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 处理修改授权小程序简介的推送事件
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
