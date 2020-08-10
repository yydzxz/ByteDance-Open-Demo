package com.yyd.bytedance.open.demo.controller.query.code;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("template_id")
    private Long templateId;

    /**
     * 提交描述
     */
    @NotBlank
    @JsonProperty("user_desc")
    private String userDesc;

    /**
     * 提交版本
     * 这个字段目前不起作用，该bug已经反馈给字节的开发人员，暂未修复
     */
    @NotBlank
    @JsonProperty("user_version")
    private String userVersion;

    /**
     * ext_json 配置信息，必须是 JSON String
     */
    @NotNull
    @JsonProperty("ext_json")
    private Object extJson;
}
