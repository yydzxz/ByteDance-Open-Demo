package com.yyd.bytedance.open.demo.controller.v1;

import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.v1.request.pay.OrderPayAddRequest;
import com.github.yydzxz.open.api.v1.response.pay.OrderPayAddResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付服务
 * @author yangyidian
 * @date 2021/06/24
 **/
@Slf4j
@RestController
@RequestMapping("/bytedance/v1/miniprogram/pay")
public class ByteDanceV1PayController {

    @Autowired
    private IByteDanceOpenService byteDanceOpenService;

    @PostMapping("/order/add")
    public OrderPayAddResponse orderPayAdd(String appid, @RequestBody OrderPayAddRequest request){
        return byteDanceOpenService.getByteDanceOpenV1ComponentService()
            .getByteDanceOpenV1MiniProgramServiceByAppid(appid)
            .getByteDanceOpenV1MiniProgramPayService()
            .orderAdd(request);
    }
}
