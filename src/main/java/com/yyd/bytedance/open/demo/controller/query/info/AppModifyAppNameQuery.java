package com.yyd.bytedance.open.demo.controller.query.info;

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
    private String newName;

    /**
     * 如果调用 小程序名称检测 接口，返回 21002 错误，则必须先调用 上传图片材料 接口上传证明。
     *
     * 另外，需要使用 上传图片材料 接口返回的路径才可以，否则报错。
     */
    private String materialFilePath;
}
