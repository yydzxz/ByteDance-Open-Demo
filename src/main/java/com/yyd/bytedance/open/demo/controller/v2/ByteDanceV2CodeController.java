package com.yyd.bytedance.open.demo.controller.v2;

import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.v2.request.code.CodeAuditRequest;
import com.github.yydzxz.open.api.v2.response.code.CodeAuditResponse;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangyidian
 * @date 2020/09/21
 **/
@RestController
@RequestMapping("/bytedance/miniprogram/v2/code")
public class ByteDanceV2CodeController {

    @Autowired
    private IByteDanceOpenService byteDanceOpenService;

    /**
     * 提审代码
     * @param appid
     * @return
     */
    @PostMapping("/audit")
    public CodeAuditResponse audit(String appid){
        CodeAuditRequest request = new CodeAuditRequest();
        request.setHostNames(Arrays.asList("toutiao", "douyin"));
        return byteDanceOpenService.getByteDanceOpenV2ComponentService()
            .getByteDanceOpenMiniProgramServiceByAppid(appid)
            .getByteDanceOpenMiniProgramCodeService()
            .audit(request);
    }
}
