package com.yyd.bytedance.open.demo.controller.v2;

import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.v1.response.code.CodeAuditHostsResponse;
import com.github.yydzxz.open.api.v2.request.code.CodeAuditRequest;
import com.github.yydzxz.open.api.v2.response.code.CodeAuditResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangyidian
 * @date 2020/09/21
 **/
@RestController
@RequestMapping("/bytedance/v2/miniprogram/code")
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
        CodeAuditHostsResponse auditHostsResponse = byteDanceOpenService.getByteDanceOpenV1ComponentService()
            .getByteDanceOpenV1MiniProgramServiceByAppid(appid)
            .getByteDanceOpenV1MiniProgramCodeService()
            .auditHosts();

        CodeAuditRequest request = new CodeAuditRequest();
        request.setHostNames(auditHostsResponse.getData().getHostNames());
        return byteDanceOpenService.getByteDanceOpenV2ComponentService()
            .getByteDanceOpenV2MiniProgramServiceByAppid(appid)
            .getByteDanceOpenV2MiniProgramCodeService()
            .audit(request);
    }
}
