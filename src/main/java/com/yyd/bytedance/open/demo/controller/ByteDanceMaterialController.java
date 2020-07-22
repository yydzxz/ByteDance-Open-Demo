package com.yyd.bytedance.open.demo.controller;

import cn.hutool.core.io.IoUtil;
import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.request.material.UploadPicMaterialRequest;
import com.yyd.bytedance.open.demo.controller.query.material.UploadPicMaterialQuery;
import java.io.File;
import java.io.IOException;
import jodd.io.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yangyidian
 * @date 2020/07/21
 **/
@RestController
@RequestMapping("/bytedance/material")
public class ByteDanceMaterialController {

    @Autowired
    private IByteDanceOpenService byteDanceOpenService;

    @PostMapping("/pic-material/upload")
    public void uploadPicMaterial(MultipartFile materialFile, @RequestBody UploadPicMaterialQuery query) throws IOException {
        UploadPicMaterialRequest request = new UploadPicMaterialRequest();
        request.setMaterial_type(query.getMaterialType());

        File file = File.createTempFile("bytedance-material", System.currentTimeMillis() + "");
        FileUtil.writeBytes(file, materialFile.getBytes());

        request.setMaterial_file(file);
        byteDanceOpenService.getByteDanceOpenComponentService()
            .getByteDanceOpenMaterialService()
            .uploadPicMaterial(request);
    }

}
