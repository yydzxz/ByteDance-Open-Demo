package com.yyd.bytedance.open.demo.controller;

import com.github.yydzxz.common.util.json.ByteDanceJsonBuilder;
import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.request.code.CodeUploadRequest;
import com.github.yydzxz.open.api.response.code.CodeAuditResponse;
import com.github.yydzxz.open.api.response.code.CodeReleaseResponse;
import com.github.yydzxz.open.api.response.code.CodeRollbackResponse;
import com.github.yydzxz.open.api.response.code.CodeUploadResponse;
import com.yyd.bytedance.open.demo.controller.query.code.CodeUploadQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代码包管理相关controller
 * @author yangyidian
 * @date 2020/07/01
 **/
@RestController
@RequestMapping("/bytedance/miniprogram/code")
public class ByteDanceCodeController {

    @Autowired
    private IByteDanceOpenService byteDanceOpenService;

    /**
     * 提交代码
     * @param appid
     * @param param
     * @return
     */
    @PostMapping("/upload")
    public CodeUploadResponse upload(String appid, @RequestBody CodeUploadQuery param){
        CodeUploadRequest request = new CodeUploadRequest();
        BeanUtils.copyProperties(param, request);
        request.setExtJson(ByteDanceJsonBuilder.instance().toJson(param.getExtJson()));
        return byteDanceOpenService.getByteDanceOpenComponentService()
            .getOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramCodeService()
            .upload(request);
    }

    /**
     * 提审代码
     * @param appid
     * @return
     */
    @PostMapping("/audit")
    public CodeAuditResponse audit(String appid){
        return byteDanceOpenService.getByteDanceOpenComponentService()
            .getOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramCodeService()
            .audit();
    }

    /**
     * 发布代码
     * @param appid
     * @return
     */
    @PostMapping("/release")
    public CodeReleaseResponse release(String appid){
        return byteDanceOpenService.getByteDanceOpenComponentService()
            .getOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramCodeService()
            .release();
    }

    /**
     * 回退代码版本
     * @param appid
     * @return
     */
    @PostMapping("/rollback")
    public CodeRollbackResponse rollback(String appid){
        return byteDanceOpenService.getByteDanceOpenComponentService()
            .getOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramCodeService()
            .rollback();
    }


    /**
     * 获取小程序版本列表信息
     * @param appid
     * @return
     */
    @GetMapping("/versions")
    public String versions(String appid){
        return byteDanceOpenService.getByteDanceOpenComponentService()
            .getOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramCodeService()
            .versions();
    }
}
