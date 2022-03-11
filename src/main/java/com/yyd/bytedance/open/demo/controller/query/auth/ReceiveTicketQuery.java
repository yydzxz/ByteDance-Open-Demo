package com.yyd.bytedance.open.demo.controller.query.auth;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/06/22
 **/
@Data
public class ReceiveTicketQuery {
    @JsonAlias("Nonce")
    private String nonce;
    @JsonAlias("TimeStamp")
    private String timeStamp;
    @JsonAlias("Encrypt")
    private String encrypt;
    @JsonAlias("MsgSignature")
    private String msgSignature;
}
