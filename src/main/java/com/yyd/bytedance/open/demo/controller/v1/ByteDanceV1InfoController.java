package com.yyd.bytedance.open.demo.controller.v1;

import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.v1.request.appinfo.AppModifyAppIconRequest;
import com.github.yydzxz.open.api.v1.request.appinfo.AppModifyAppIntroRequest;
import com.github.yydzxz.open.api.v1.request.appinfo.AppModifyAppNameRequest;
import com.github.yydzxz.open.api.v1.request.appinfo.AppModifyServerDomainRequest;
import com.github.yydzxz.open.api.v1.request.appinfo.AppModifyWebviewDomainRequest;
import com.github.yydzxz.open.api.v1.request.appinfo.AppQrCodeRequest;
import com.github.yydzxz.open.api.v1.response.appinfo.AppCheckAppNameResponse;
import com.github.yydzxz.open.api.v1.response.appinfo.AppInfoResponse;
import com.github.yydzxz.open.api.v1.response.appinfo.AppModifyAppIconResponse;
import com.github.yydzxz.open.api.v1.response.appinfo.AppModifyAppIntroResponse;
import com.github.yydzxz.open.api.v1.response.appinfo.AppModifyAppNameResponse;
import com.github.yydzxz.open.api.v1.response.appinfo.AppModifyServerDomainResponse;
import com.github.yydzxz.open.api.v1.response.appinfo.AppModifyWebviewDomainResponse;
import com.yyd.bytedance.open.demo.controller.query.info.AppModifyAppIconQuery;
import com.yyd.bytedance.open.demo.controller.query.info.AppModifyAppIntroQuery;
import com.yyd.bytedance.open.demo.controller.query.info.AppModifyAppNameQuery;
import com.yyd.bytedance.open.demo.controller.query.info.AppModifyServerDomainQuery;
import com.yyd.bytedance.open.demo.controller.query.info.AppModifyWebviewDomainQuery;
import com.yyd.bytedance.open.demo.controller.query.info.AppInfoQrCodeQuery;
import javax.validation.Valid;
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
@RequestMapping("/bytedance/v1/app")
public class ByteDanceV1InfoController {

    @Autowired
    private IByteDanceOpenService byteDanceOpenService;

    /**
     * 获取授权小程序基本信息
     * @param appid
     * @return
     */
    @GetMapping("/info")
    public AppInfoResponse appInfo(String appid){
        return byteDanceOpenService.getByteDanceOpenV1ComponentService()
            .getByteDanceOpenMiniProgramServiceByAppid(appid)
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
    public byte[] qrCode(String appid,@RequestBody AppInfoQrCodeQuery param){
        AppQrCodeRequest request = new AppQrCodeRequest();
        BeanUtils.copyProperties(param, request);
        return byteDanceOpenService.getByteDanceOpenV1ComponentService()
            .getByteDanceOpenMiniProgramServiceByAppid(appid)
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
    public AppCheckAppNameResponse checkAppName(String appid,@RequestParam("app_name") String appName){
        return byteDanceOpenService.getByteDanceOpenV1ComponentService()
            .getByteDanceOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramInfoService()
            .checkAppName(appName);
    }

    /**
     * 修改小程序名称
     * @param appid
     * @param query
     * @return
     */
    @PostMapping("/modify_app_name")
    public AppModifyAppNameResponse modifyAppName(String appid, @Valid @RequestBody AppModifyAppNameQuery query){
        AppModifyAppNameRequest request = new AppModifyAppNameRequest();
        BeanUtils.copyProperties(query, request);
        return byteDanceOpenService.getByteDanceOpenV1ComponentService()
            .getByteDanceOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramInfoService()
            .modifyAppName(request);
    }

    /**
     * 修改小程序简介
     * @param appid
     * @param query
     * @return
     */
    @PostMapping("modify_app_intro")
    public AppModifyAppIntroResponse modifyAppIntro(String appid, @Valid @RequestBody AppModifyAppIntroQuery query){
        AppModifyAppIntroRequest request = new AppModifyAppIntroRequest();
        BeanUtils.copyProperties(query, request);
        return byteDanceOpenService.getByteDanceOpenV1ComponentService()
            .getByteDanceOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramInfoService()
            .modifyAppIntro(request);
    }

    /**
     * 修改小程序图标
     * @param appid
     * @param query
     * @return
     */
    @PostMapping("/modify_app_icon")
    public AppModifyAppIconResponse modifyAppIcon(String appid, @Valid @RequestBody AppModifyAppIconQuery query){
        AppModifyAppIconRequest request = new AppModifyAppIconRequest();
        BeanUtils.copyProperties(query, request);
        return byteDanceOpenService.getByteDanceOpenV1ComponentService()
            .getByteDanceOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramInfoService()
            .modifyAppIcon(request);
    }

    /**
     * 修改服务器域名
     * 注意：事先需要在第三方平台（https://open.microapp.bytedance.com）设置好第三方平台的服务器域名，接口服务器域名的选项必须在第三方平台的服务器域名列表内
     * 提示：设置的服务器域名可以是第三方平台服务器域名的子域名
     * @param appid
     * @param query
     * @return
     */
    @PostMapping("/modify_server_domain")
    public AppModifyServerDomainResponse modifyServerDomain(String appid, @Valid @RequestBody AppModifyServerDomainQuery query){
        AppModifyServerDomainRequest request = new AppModifyServerDomainRequest();
        BeanUtils.copyProperties(query, request);
        return byteDanceOpenService.getByteDanceOpenV1ComponentService()
            .getByteDanceOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramInfoService()
            .modifyServerDomain(request);
    }

    /**
     * 修改 webview 域名
     * @param appid
     * @return
     */
    @PostMapping("/modify_webview_domain")
    public AppModifyWebviewDomainResponse modifyWebviewDomain(String appid, @Valid @RequestBody AppModifyWebviewDomainQuery query){
        AppModifyWebviewDomainRequest request = new AppModifyWebviewDomainRequest();
        BeanUtils.copyProperties(query, request);
        return byteDanceOpenService.getByteDanceOpenV1ComponentService()
            .getByteDanceOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramInfoService()
            .modifyWebviewDomain(request);
    }
}
