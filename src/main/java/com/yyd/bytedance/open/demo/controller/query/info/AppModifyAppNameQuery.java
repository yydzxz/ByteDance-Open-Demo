package com.yyd.bytedance.open.demo.controller.query.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/23
 **/
@Data
public class AppModifyAppNameQuery {

    /**
     * 授权小程序准备修改的名称
     */
    @NotEmpty(message = "授权小程序准备修改的名称不能为空")
    @JsonProperty("new_name")
    private String newName;

    /**
     * 如果调用 小程序名称检测 接口，返回 21002 错误，则必须先调用 上传图片材料 接口上传证明。
     *
     * 另外，需要使用 上传图片材料 接口返回的路径才可以，否则报错。
     */
    @JsonProperty("material_file_path")
    private String materialFilePath;
}
