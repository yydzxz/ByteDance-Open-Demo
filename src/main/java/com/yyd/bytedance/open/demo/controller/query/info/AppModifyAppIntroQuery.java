package com.yyd.bytedance.open.demo.controller.query.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/23
 **/
@Data
public class AppModifyAppIntroQuery {

    @Size(min = 10, max = 60,message = "App 简介长度不符合规范，需10至60个字")
    @NotEmpty(message = "授权小程序准备修改的简介不能为空")
    @JsonProperty("new_intro")
    private String newIntro;
}
