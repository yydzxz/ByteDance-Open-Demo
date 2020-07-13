package com.yyd.bytedance.open.demo.controller.query.code;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/06
 **/
@Data
public class CodeUploadQuery {

    /**
     * 模版 ID
     */
    @NotNull
    private Long templateId;
    /**
     * 提交描述
     */
    @NotBlank
    private String userDesc;
    /**
     * 提交版本
     */
    @NotBlank
    private String userVersion;
    /**
     * ext_json 配置信息，必须是 JSON String
     */
    @NotNull
    private Object extJson;
}
