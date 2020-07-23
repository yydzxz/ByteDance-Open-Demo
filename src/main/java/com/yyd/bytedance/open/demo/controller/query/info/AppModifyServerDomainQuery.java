package com.yyd.bytedance.open.demo.controller.query.info;

import java.util.List;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/23
 **/
@Data
public class AppModifyServerDomainQuery {

    private List<String> request;

    private List<String> socket;

    private List<String> upload;

    private List<String> download;

    /**
     * add 添加，delete 删除，set 覆盖，get 获取
     */
    private String action;
}
