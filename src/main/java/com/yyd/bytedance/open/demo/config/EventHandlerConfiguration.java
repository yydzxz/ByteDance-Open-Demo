package com.yyd.bytedance.open.demo.config;


import com.github.yydzxz.common.ByteDanceMessageInRedisDuplicateChecker;
import com.github.yydzxz.common.IByteDanceMessageDuplicateChecker;
import com.github.yydzxz.common.redis.IByteDanceRedisOps;
import com.github.yydzxz.open.api.ByteDanceOpenMessageRouter;
import com.github.yydzxz.open.bean.message.ByteDanceOpenMessage;
import com.yyd.bytedance.open.demo.handler.LogHandler;
import com.yyd.bytedance.open.demo.handler.MsgTypeTicketHandler;
import com.yyd.bytedance.open.demo.handler.UnauthorizedEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 字节跳动推送事件处理配置文件
 * @author yangyidian
 * @date 2020/07/08
 **/
@Configuration
public class EventHandlerConfiguration {

    @Autowired
    private LogHandler logHandler;

    @Autowired
    private UnauthorizedEventHandler unauthorizedEventHandler;

    @Autowired
    private MsgTypeTicketHandler msgTypeTicketHandler;

    @Autowired
    private IByteDanceRedisOps byteDanceRedisOps;

    @Bean
    public ByteDanceOpenMessageRouter getByteDanceOpenMessageRouter(){
        final IByteDanceMessageDuplicateChecker messageDuplicateChecker = new ByteDanceMessageInRedisDuplicateChecker(byteDanceRedisOps);
        final ByteDanceOpenMessageRouter router = new ByteDanceOpenMessageRouter(messageDuplicateChecker);
        //注意，到底是要用event还是msgType，或者同时使用来匹配handler
        router.rule()
            .event(ByteDanceOpenMessage.EVENT_UNAUTHORIZED)
            .addHandler(unauthorizedEventHandler)
            .end();

        router.rule()
            .event(ByteDanceOpenMessage.EVENT_AUTHORIZED)
            .addHandler(logHandler)
            .end();

        router.rule()
            .event(ByteDanceOpenMessage.EVENT_PUSH)
            .msgType(ByteDanceOpenMessage.MSG_TYPE_TICKET)
            .addHandler(logHandler)
            .addHandler(msgTypeTicketHandler)
            .end();

        return router;
    }

}
