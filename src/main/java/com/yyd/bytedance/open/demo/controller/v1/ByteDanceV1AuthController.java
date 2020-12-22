package com.yyd.bytedance.open.demo.controller.v1;

import com.github.yydzxz.common.error.ByteDanceErrorException;
import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.v1.response.auth.AuthAppListResponse;
import com.github.yydzxz.open.api.v1.response.auth.GetAuthorizerAccessTokenResponse;
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
 * 授权认证v1
 * 不支持预设置分账比例
 * @author yangyidian
 * @date 2020/06/28
 **/
@Slf4j
@RestController
@RequestMapping("/bytedance/v1/auth")
public class ByteDanceV1AuthController {

    @Autowired
    private IByteDanceOpenService byteDanceOpenService;

    @GetMapping("/goto_auth_url_show")
    public String gotoPreAuthUrlShow(){
        return "<a href='goto_auth_url'>go</a>";
    }

    @GetMapping("/goto_auth_url")
    public void gotoPreAuthUrl(HttpServletRequest request, HttpServletResponse response){
        String host = request.getHeader("host");
        String scheme = request.getScheme();
        String url = scheme + "://"+host+"/bytedance/v1/auth/jump";
        try {
            url = byteDanceOpenService.getByteDanceOpenV1ComponentService().getPreAuthUrl(url);
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

    @GetMapping("/{appid}/authorizer_access_token")
    public String getAuthorizerAccessToken(@PathVariable("appid") String appid){
        return byteDanceOpenService.getByteDanceOpenConfigStorage().getAuthorizerAccessToken(appid);
    }

    /**
     * 查询所有授权给第三方平台的小程序列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/auth_app_list")
    public AuthAppListResponse authAppList(Integer page, Integer size){
        return byteDanceOpenService.getByteDanceOpenV1ComponentService().authAppList(page, size);
    }
}
