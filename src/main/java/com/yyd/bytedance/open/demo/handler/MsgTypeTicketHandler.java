package com.yyd.bytedance.open.demo.handler;

import com.github.yydzxz.common.util.json.ByteDanceJsonBuilder;
import com.github.yydzxz.open.api.IByteDanceOpenMessageHandler;
import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.bean.message.ByteDanceOpenMessage;
import com.github.yydzxz.open.bean.message.ByteDanceOpenMessageHandleResult;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 字节跳动服务器推送 component_ticket 事件处理器
 * @author yangyidian
 * @date 2020/07/08
 **/
@Slf4j
@Component
public class MsgTypeTicketHandler implements IByteDanceOpenMessageHandler {

    @Autowired
    IByteDanceOpenService byteDanceOpenService;

    @Override
    public ByteDanceOpenMessageHandleResult handle(ByteDanceOpenMessage message, Map<String, Object> context) {
        log.info("MsgTypeTicketHandler 开始处理消息: {}", ByteDanceJsonBuilder.instance().toJson(message));
        byteDanceOpenService.getByteDanceOpenComponentService()
            .getOpenConfigStorage()
            .setComponentVerifyTicket(message.getTicket());
        return new ByteDanceOpenMessageHandleResult();
    }
}
