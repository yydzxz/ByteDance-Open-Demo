package com.yyd.bytedance.open.demo.handler;

import cn.hutool.json.JSONUtil;
import com.yyd.open.api.IByteDanceOpenMessageHandler;
import com.yyd.open.api.IByteDanceOpenService;
import com.yyd.open.bean.message.ByteDanceOpenMessage;
import com.yyd.open.bean.message.ByteDanceOpenMessageHandleResult;
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
    public ByteDanceOpenMessageHandleResult handle(ByteDanceOpenMessage message) {
        log.info("MsgTypeTicketHandler 开始处理消息: {}", JSONUtil.toJsonStr(message));
        byteDanceOpenService.getByteDanceOpenComponentService()
            .getOpenConfigStorage()
            .setComponentVerifyTicket(message.getTicket());
        return new ByteDanceOpenMessageHandleResult();
    }
}
