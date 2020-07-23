package com.yyd.bytedance.open.demo.controller;

import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.request.appinfo.AppModifyAppIconRequest;
import com.github.yydzxz.open.api.request.appinfo.AppModifyAppIntroRequest;
import com.github.yydzxz.open.api.request.appinfo.AppModifyAppNameRequest;
import com.github.yydzxz.open.api.request.appinfo.AppModifyServerDomainRequest;
import com.github.yydzxz.open.api.request.appinfo.AppModifyWebviewDomainRequest;
import com.github.yydzxz.open.api.request.appinfo.AppQrCodeRequest;
import com.github.yydzxz.open.api.response.appinfo.AppCheckAppNameResponse;
import com.github.yydzxz.open.api.response.appinfo.AppInfoResponse;
import com.github.yydzxz.open.api.response.appinfo.AppModifyAppIconResponse;
import com.github.yydzxz.open.api.response.appinfo.AppModifyAppIntroResponse;
import com.github.yydzxz.open.api.response.appinfo.AppModifyAppNameResponse;
import com.github.yydzxz.open.api.response.appinfo.AppModifyServerDomainResponse;
import com.github.yydzxz.open.api.response.appinfo.AppModifyWebviewDomainResponse;
import com.yyd.bytedance.open.demo.controller.query.info.InfoQrCodeQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基本信息配置
 * @author yangyidian
 * @date 2020/07/06
 **/
@RestController
@RequestMapping("/bytedance/app")
public class  ByteDanceInfoController {

    @Autowired
    private IByteDanceOpenService byteDanceOpenService;

    /**
     * 获取授权小程序基本信息
     * @param appid
     * @return
     */
    @GetMapping("/info")
    public AppInfoResponse appInfo(String appid){
        return byteDanceOpenService.getByteDanceOpenComponentService()
            .getOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramInfoService()
            .getAppInfo();
    }

    /**
     * 获取小程序对应版本的二维码
     * @param appid
     * @param param
     * @return
     */
    @PostMapping(value = "/qrcode", produces = {MediaType.IMAGE_PNG_VALUE})
    public byte[] qrCode(String appid,@RequestBody InfoQrCodeQuery param){
        AppQrCodeRequest request = new AppQrCodeRequest();
        BeanUtils.copyProperties(param, request);
        return byteDanceOpenService.getByteDanceOpenComponentService()
            .getOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramInfoService()
            .getAppQrCode(request);
    }

    /**
     * 小程序名称检测
     * @param appid
     * @param appName
     * @return
     */
    @GetMapping("/check_app_name")
    public AppCheckAppNameResponse checkAppName(String appid, @RequestParam("app_name") String appName){
        return byteDanceOpenService.getByteDanceOpenComponentService()
            .getOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramInfoService()
            .checkAppName(appName);
    }

    /**
     * 修改小程序名称
     * @param appid
     * @param request
     * @return
     */
    @PostMapping("/modify_app_name")
    public AppModifyAppNameResponse modifyAppName(String appid, @RequestBody AppModifyAppNameRequest request){
        return byteDanceOpenService.getByteDanceOpenComponentService()
            .getOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramInfoService()
            .modifyAppName(request);
    }

    /**
     * 修改小程序简介
     * @param appid
     * @param request
     * @return
     */
    @PostMapping("modify_app_intro")
    public AppModifyAppIntroResponse modifyAppIntro(String appid, @RequestBody AppModifyAppIntroRequest request){
        return byteDanceOpenService.getByteDanceOpenComponentService()
            .getOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramInfoService()
            .modifyAppIntro(request);
    }

    /**
     * 修改小程序图标
     * @param appid
     * @param request
     * @return
     */
    @PostMapping("/modify_app_icon")
    public AppModifyAppIconResponse modifyAppIcon(String appid, @RequestBody AppModifyAppIconRequest request){
        return byteDanceOpenService.getByteDanceOpenComponentService()
            .getOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramInfoService()
            .modifyAppIcon(request);
    }

    /**
     * 修改服务器域名
     * 注意：事先需要在第三方平台（https://open.microapp.bytedance.com）设置好第三方平台的服务器域名，接口服务器域名的选项必须在第三方平台的服务器域名列表内
     * 提示：设置的服务器域名可以是第三方平台服务器域名的子域名
     * @param appid
     * @param request
     * @return
     */
    @PostMapping("/modify_server_domain")
    public AppModifyServerDomainResponse modifyServerDomain(String appid, @RequestBody AppModifyServerDomainRequest request){
        return byteDanceOpenService.getByteDanceOpenComponentService()
            .getOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramInfoService()
            .modifyServerDomain(request);
    }

    /**
     * 修改 webview 域名
     * @param appid
     * @return
     */
    @PostMapping("/modify_webview_domain")
    public AppModifyWebviewDomainResponse modifyWebviewDomain(String appid, @RequestBody AppModifyWebviewDomainRequest request){
        return byteDanceOpenService.getByteDanceOpenComponentService()
            .getOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramInfoService()
            .modifyWebviewDomain(request);
    }
}
