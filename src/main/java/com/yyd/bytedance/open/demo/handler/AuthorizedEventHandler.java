package com.yyd.bytedance.open.demo.handler;


import com.github.yydzxz.open.api.v1.IByteDanceOpenService;
import com.github.yydzxz.open.api.v1.request.appinfo.AppModifyServerDomainRequest;
import com.github.yydzxz.open.message.ByteDanceOpenMessage;
import com.github.yydzxz.open.message.ByteDanceOpenMessageHandleResult;
import com.github.yydzxz.open.message.IByteDanceOpenMessageHandler;
import com.yyd.bytedance.open.demo.config.ByteDanceOpenProperties;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 授权事件处理器
 * @author yangyidian
 * @date 2020/07/10
 **/
@Slf4j
@Component
public class AuthorizedEventHandler implements IByteDanceOpenMessageHandler {

    @Autowired
    private ByteDanceOpenProperties byteDanceOpenProperties;

    @Autowired
    private IByteDanceOpenService byteDanceOpenService;

    @Override
    public ByteDanceOpenMessageHandleResult handle(ByteDanceOpenMessage message, Map<String, Object> context) {
        byteDanceOpenService.getByteDanceOpenComponentService().getAuthorizerAccessTokenByAuthorizationCode(message.getAuthorizationCode());
        AppModifyServerDomainRequest request = new AppModifyServerDomainRequest();
        request.setAction("delete");
        request.setRequest(byteDanceOpenProperties.getServerDomain().getRequest());
        try {
            byteDanceOpenService.getByteDanceOpenComponentService()
                .getOpenMiniProgramServiceByAppid(message.getAppId())
                .getByteDanceOpenMiniProgramInfoService()
                .modifyServerDomain(request);
        }catch (Exception e){
            log.error("字节跳动小程序[{}]删除request域名: {} 失败", message.getAppId(), byteDanceOpenProperties.getServerDomain().getRequest());
            log.error(e.getMessage(), e);
        }

        request.setAction("add");
        byteDanceOpenService.getByteDanceOpenComponentService()
            .getOpenMiniProgramServiceByAppid(message.getAppId())
            .getByteDanceOpenMiniProgramInfoService()
            .modifyServerDomain(request);
        return new ByteDanceOpenMessageHandleResult();
    }
}
