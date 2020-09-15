package com.yyd.bytedance.open.demo.controller;

import com.github.yydzxz.open.api.v1.IByteDanceOpenService;
import com.github.yydzxz.open.api.v1.request.template.TemplateAddTplRequest;
import com.github.yydzxz.open.api.v1.request.template.TemplateDelTplRequest;
import com.github.yydzxz.open.api.v1.response.template.TemplateAddTplResponse;
import com.github.yydzxz.open.api.v1.response.template.TemplateDelTplResponse;
import com.github.yydzxz.open.api.v1.response.template.TemplateGetDraftListResponse;
import com.github.yydzxz.open.api.v1.response.template.TemplateGetTplListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模版管理相关controller
 * @author yangyidian
 * @date 2020/07/01
 **/
@Slf4j
@RestController
@RequestMapping("/bytedance/template")
public class ByteDanceTemplateController {

    @Autowired
    private IByteDanceOpenService byteDanceOpenService;

    /**
     * 获取第三方应用的草稿
     * @return
     */
    @GetMapping("/draft/list")
    public TemplateGetDraftListResponse draftList(){
        return byteDanceOpenService.getByteDanceOpenComponentService().getByteDanceOpenTemplateService().getDraftList();
    }

    /**
     * 将草稿设置为模版
     * @param draftId
     * @return
     */
    @PostMapping("/draft/{draftId}")
    public TemplateAddTplResponse addTpl(@PathVariable("draftId") Integer draftId){
        TemplateAddTplRequest request = new TemplateAddTplRequest();
        request.setDraftId(draftId);
        return byteDanceOpenService.getByteDanceOpenComponentService().getByteDanceOpenTemplateService().addTpl(request);
    }

    /**
     * 删除模版
     * @param templateId
     * @return
     */
    @DeleteMapping("/{templateId}")
    public TemplateDelTplResponse delTpl(@PathVariable("templateId") Integer templateId){
        TemplateDelTplRequest request = new TemplateDelTplRequest();
        request.setTemplateId(templateId);
        return byteDanceOpenService.getByteDanceOpenComponentService().getByteDanceOpenTemplateService().delTpl(request);
    }


    /**
     * 获取第三方应用的所有模版
     * @return
     */
    @GetMapping("/list")
    public TemplateGetTplListResponse getTplList(){
        return byteDanceOpenService.getByteDanceOpenComponentService().getByteDanceOpenTemplateService().getTplList();
    }
}
