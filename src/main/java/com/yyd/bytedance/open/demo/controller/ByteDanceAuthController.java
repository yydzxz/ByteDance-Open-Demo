package com.yyd.bytedance.open.demo.controller;

import com.github.yydzxz.common.error.ByteDanceErrorException;
import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.response.auth.GetAuthorizerAccessTokenReponse;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
@Slf4j
@RestController
@RequestMapping("/bytedance/auth")
public class ByteDanceAuthController {

    @Autowired
    private IByteDanceOpenService byteDanceOpenService;

    @GetMapping("/goto_auth_url_show")
    @ResponseBody
    public String gotoPreAuthUrlShow(){
        return "<a href='goto_auth_url'>go</a>";
    }
    @GetMapping("/goto_auth_url")
    public void gotoPreAuthUrl(HttpServletRequest request, HttpServletResponse response){
        String host = request.getHeader("host");
        String scheme = request.getScheme();
        String url = scheme + "://"+host+"/bytedance/auth/jump";
        try {
            url = byteDanceOpenService.getByteDanceOpenComponentService().getPreAuthUrl(url);
            // 添加来源，解决302跳转来源丢失的问题
            response.addHeader("Referer", scheme + "://"+host);
            response.sendRedirect(url);
        } catch (ByteDanceErrorException | IOException e) {
            log.error("gotoPreAuthUrl", e);
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/jump")
    @ResponseBody
    public GetAuthorizerAccessTokenReponse jump(@RequestParam("authorization_code") String authorizationCode){
        log.info("authorizationCode: {}", authorizationCode);
        return byteDanceOpenService.getByteDanceOpenComponentService().getAuthorizerAccessTokenByAuthorizationCode(authorizationCode);
    }

    @GetMapping("/{appid}/authorizer_access_token")
    public String getAuthorizerAccessToken(@PathVariable("appid") String appid){
        return byteDanceOpenService.getByteDanceOpenConfigStorage().getAuthorizerAccessToken(appid);
    }
}
