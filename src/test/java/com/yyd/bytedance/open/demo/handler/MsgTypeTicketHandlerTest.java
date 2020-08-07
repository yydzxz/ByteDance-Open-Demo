package com.yyd.bytedance.open.demo.handler;

import com.github.yydzxz.open.bean.message.ByteDanceOpenMessage;
import com.github.yydzxz.open.bean.message.ByteDanceOpenMessageHandleResult;
import java.util.Date;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest( webEnvironment = WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=unittest")
class MsgTypeTicketHandlerTest {


    @Autowired
    private MsgTypeTicketHandler msgTypeTicketHandler;

    @Value("${local.server.port}")
    private int port;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void handle() {
        ByteDanceOpenMessage message = new ByteDanceOpenMessage();
        message.setMsgType("Ticket");
        message.setTicket("123456789011232312323122312");
        message.setFromTpAppId("testappid");
        message.setFromUserName("TestByteDanceMicroApp");
        message.setCreateTime(new Date(1596609368000L));
        message.setEvent("PUSH");
        Map<String, Object> context = null;
        ByteDanceOpenMessageHandleResult result = msgTypeTicketHandler.handle(message, context);
        assertEquals("success", result.getDefaultResult());
    }
}