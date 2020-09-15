package com.yyd.bytedance.open.demo.controller;

import cn.hutool.core.io.FileUtil;
import com.github.yydzxz.open.api.IByteDanceOpenService;
import com.github.yydzxz.open.api.v1.request.material.UploadPicMaterialRequest;
import com.github.yydzxz.open.api.v1.response.material.UploadPicMaterialResponse;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 素材
 * @author yangyidian
 * @date 2020/07/21
 **/
@Slf4j
@RestController
@RequestMapping("/bytedance/material")
public class ByteDanceMaterialController {

    @Autowired
    private IByteDanceOpenService byteDanceOpenService;

    /**
     * 上传图片素材
     * @param materialFile
     * @param materialType
     * @return
     * @throws IOException
     */
    @PostMapping("/pic/upload")
    public UploadPicMaterialResponse uploadPicMaterial(@RequestParam("material_file") MultipartFile materialFile, @RequestParam("material_type") String materialType) throws IOException {
        UploadPicMaterialRequest request = new UploadPicMaterialRequest();
        request.setMaterialType(materialType);
        File file = File.createTempFile("bytedance-material", System.currentTimeMillis() + "");
        FileUtil.writeBytes(materialFile.getBytes(), file);
        request.setMaterialFile(file);
        return byteDanceOpenService.getByteDanceOpenComponentService()
            .getByteDanceOpenMaterialService()
            .uploadPicMaterial(request);
    }
}
