package com.yyd.bytedance.open.demo.controller.v1;

import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.v1.response.operation.OperationLiveApplicationStatusResponse;
import com.github.yydzxz.open.api.v1.response.operation.OperationPhoneNumberApplicationStatusResponse;
import com.github.yydzxz.open.api.v1.response.operation.OperationVideoApplicationResponse;
import com.github.yydzxz.open.api.v1.response.operation.OperationVideoApplicationStatusResponse;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 运营管理
 * @author yangyidian
 * @date 2020/12/22
 **/
@Slf4j
@RestController
@RequestMapping("/bytedance/v1/miniprogram/operation")
public class ByteDanceV1OperationController {

    @Autowired
    private IByteDanceOpenService byteDanceOpenService;

    /**
     * 查询「短视频挂载」能力申请状态
     * @param appid
     * @return
     */
    @GetMapping("/video_application_status")
    public OperationVideoApplicationStatusResponse videoApplicationStatus(String appid) {
        return byteDanceOpenService.getByteDanceOpenV1ComponentService()
            .getByteDanceOpenV1MiniProgramServiceByAppid(appid)
            .getByteDanceOpenV1MiniProgramOperationService()
            .videoApplicationStatus();
    }

    /**
     * 查询「抖音直播组件」能力申请状态
     * @param appid
     * @return
     */
    @GetMapping("/live_application_status")
    public OperationLiveApplicationStatusResponse liveApplicationStatus(String appid){
        return byteDanceOpenService.getByteDanceOpenV1ComponentService()
            .getByteDanceOpenV1MiniProgramServiceByAppid(appid)
            .getByteDanceOpenV1MiniProgramOperationService()
            .liveApplicationStatus();
    }

    /**
     * 查询「获取用户手机号」能力申请状态
     * @param appid
     * @return
     */
    @GetMapping("/phone_number_application_status")
    public OperationPhoneNumberApplicationStatusResponse phoneNumberApplicationStatus(String appid){
        return byteDanceOpenService.getByteDanceOpenV1ComponentService()
            .getByteDanceOpenV1MiniProgramServiceByAppid(appid)
            .getByteDanceOpenV1MiniProgramOperationService()
            .phoneNumberApplicationStatus();
    }
}
