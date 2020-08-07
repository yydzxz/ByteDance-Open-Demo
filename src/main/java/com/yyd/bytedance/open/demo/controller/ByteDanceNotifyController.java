package com.yyd.bytedance.open.demo.controller;

import com.github.yydzxz.open.api.ByteDanceOpenMessageRouter;
import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.bean.message.ByteDanceOpenMessage;
import com.yyd.bytedance.open.demo.config.ByteDanceOpenProperties;
import com.yyd.bytedance.open.demo.controller.query.auth.ReceiveTicketQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理字节跳动服务器各种推送消息
 * @author yangyidian
 * @date 2020/06/19
 **/
@Slf4j
@RestController
@RequestMapping("/bytedance/notify")
public class ByteDanceNotifyController {
    @Autowired
    private IByteDanceOpenService byteDanceOpenService;

    @Autowired
    private ByteDanceOpenProperties byteDanceOpenProperties;

    @Autowired
    private ByteDanceOpenMessageRouter byteDanceOpenMessageRouter;

    /**
     * 授权接收URL
     * 用于接收授权成功、更新、解除通知，也用于接收ticket
     * @param receiveTicketQuery
     * @return
     */
    @PostMapping("/receive_ticket")
    public String receiveTicket(@RequestBody ReceiveTicketQuery receiveTicketQuery) {
        log.info("接收字节跳动请求：{}",receiveTicketQuery);
        handleMessage(receiveTicketQuery);
        return "success";
    }

    /**
     * 消息与事件接收URL
     * 该URL用于接收已授权小程序的消息和事件
     * @return
     */
    @RequestMapping("/{appid}/callback")
    public String callback(@PathVariable("appid") String appid, @RequestBody ReceiveTicketQuery receiveTicketQuery){
        log.info("小程序[{}]接收到消息与事件推送: {}", appid, receiveTicketQuery);
        handleMessage(receiveTicketQuery);
        return "success";
    }


    private String handleMessage(ReceiveTicketQuery receiveTicketQuery){
        boolean checkSignatureSuccess = byteDanceOpenService.getByteDanceOpenComponentService()
            .checkSignature(receiveTicketQuery.getMsgSignature(),
                byteDanceOpenProperties.getComponentToken(),
                receiveTicketQuery.getTimeStamp(),
                receiveTicketQuery.getNonce(),
                receiveTicketQuery.getEncrypt());

        if (!checkSignatureSuccess) {
            log.error("非法请求，可能属于伪造的请求！");
            return "failed";
        }
        ByteDanceOpenMessage message = null;
        try {
            message = ByteDanceOpenMessage.fromEncrypted(receiveTicketQuery.getEncrypt(), byteDanceOpenProperties.getComponentAesKey());
            log.info("message: {}", message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "failed";
        }
        byteDanceOpenMessageRouter.route(message);
        return "success";
    }
}
