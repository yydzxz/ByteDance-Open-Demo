package com.yyd.bytedance.open.demo.controller.v2;

import com.github.yydzxz.common.error.ByteDanceErrorException;
import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.v1.response.auth.GetAuthorizerAccessTokenResponse;
import com.github.yydzxz.open.api.v2.request.auth.GetPreAuthCodeRequest;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 授权认证
 * @author yangyidian
 * @date 2020/06/28
 **/
@Slf4j
@RestController
@RequestMapping("/bytedance/v2/auth")
public class ByteDanceV2AuthController {

    @Autowired
    private IByteDanceOpenService byteDanceOpenService;

    @GetMapping("/goto_auth_url_show")
    public String gotoPreAuthUrlShow(){
        return "<a href='goto_auth_url'>go</a>";
    }

    @GetMapping("/goto_auth_url")
    public void v2GotoPreAuthUrl(HttpServletRequest request, HttpServletResponse response){
        String host = request.getHeader("host");
        String scheme = request.getScheme();
        String url = scheme + "://"+host+"/bytedance/v2/auth/jump";
        try {
            GetPreAuthCodeRequest getPreAuthCodeRequest = new GetPreAuthCodeRequest();
            getPreAuthCodeRequest.setShareAmount(10);
            getPreAuthCodeRequest.setShareRatio(10);
            url = byteDanceOpenService.getByteDanceOpenV2ComponentService().getPreAuthUrl(url, getPreAuthCodeRequest);
            // 添加来源，解决302跳转来源丢失的问题
            response.addHeader("Referer", scheme + "://"+host);
            response.sendRedirect(url);
        } catch (ByteDanceErrorException | IOException e) {
            log.error("gotoPreAuthUrl", e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/jump")
    public GetAuthorizerAccessTokenResponse jump(@RequestParam("authorization_code") String authorizationCode){
        log.info("authorizationCode: {}", authorizationCode);
        return byteDanceOpenService.getByteDanceOpenV1ComponentService().getAuthorizerAccessTokenByAuthorizationCode(authorizationCode);
    }
}
