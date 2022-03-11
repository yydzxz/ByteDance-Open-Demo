package com.yyd.bytedance.open.demo.controller.query.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/23
 **/
@Data
public class AppModifyAppIconQuery {

    /**
     * 授权小程序准备修改的图标路径
     * 注意：需要使用 上传图片材料 接口返回的路径才可以，否则报错。
     */
    @NotEmpty(message = "授权小程序准备修改的图标路径不能为空")
    @JsonProperty("new_icon_path")
    private String newIconPath;
}
