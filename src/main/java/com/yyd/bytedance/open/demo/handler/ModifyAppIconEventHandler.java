package com.yyd.bytedance.open.demo.handler;

import com.github.yydzxz.open.message.ByteDanceOpenMessage;
import com.github.yydzxz.open.message.ByteDanceOpenMessageHandleResult;
import com.github.yydzxz.open.message.IByteDanceOpenMessageHandler;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 处理修改授权小程序图标的推送事件
 * @author yangyidian
 * @date 2020/07/20
 **/
@Slf4j
@Component
public class ModifyAppIconEventHandler implements IByteDanceOpenMessageHandler {

    @Override
    public ByteDanceOpenMessageHandleResult handle(ByteDanceOpenMessage message, Map<String, Object> context) {
        return null;
    }
}
