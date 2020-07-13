package com.yyd.bytedance.open.demo.controller;

import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.request.appinfo.AppQrCodeRequest;
import com.github.yydzxz.open.api.response.appinfo.AppInfoResponse;
import com.yyd.bytedance.open.demo.controller.query.info.InfoQrCodeQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通过字节跳动接口，获取小程序基本信息配置
 * @author yangyidian
 * @date 2020/07/06
 **/
@RestController
@RequestMapping("/bytedance/miniprogram/info")
public class  ByteDanceInfoController {

    @Autowired
    private IByteDanceOpenService byteDanceOpenService;

    /**
     * 获取授权小程序基本信息
     * @param appid
     * @return
     */
    @GetMapping
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
}
