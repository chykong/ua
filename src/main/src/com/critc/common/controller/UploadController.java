package com.critc.common.controller;

import com.critc.util.code.SerialNumUtil;
import com.critc.util.config.PubConfig;
import com.critc.util.date.DateUtil;
import com.critc.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * 文件上传controller
 *
 * @author chykong
 */
@Controller
@RequestMapping("/common")
public class UploadController {
    @Autowired
    private PubConfig pubConfig;

    @RequestMapping("/upload")
    public void upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String uploadPath = pubConfig.getImageUploadPath();
        String storePath = "/proPic/" + DateUtil.getShortSystemDate() + "/";
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        //        String fileName = new Date().getTime()+".jpg";
        String createFilename = DateUtil.getShortSystemTime() + SerialNumUtil.createRandowmNum(6) + "." + suffix;
        File targetFile = new File(uploadPath + storePath, createFilename);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        String json = getjson(file, createFilename, storePath, targetFile);
        WebUtil.out(response, json);
    }


    public String getjson(MultipartFile file, String createFilename, String storePath, File targetFile) {
        String json = "";
        if (file.getSize() > 1 * 1024 * 1024) {
            json = "{success:" + false + ",msgText:'" + "文件超过1M" + "'}";
        } else {
            //保存
            try {
                file.transferTo(targetFile);
                json = "{success:" + true + ",msgText:'" + "成功" + "',createFilename:'" + createFilename + "',createFilepath:'" + storePath + "'}";
            } catch (Exception e) {
                json = "{success:" + false + ",msgText:'" + "上传失败" + e.getMessage() + "'}";
                e.printStackTrace();
            }
        }
        return json;
    }

    @RequestMapping("/uploadError")
    public void uploadError(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("文件超出大小");
        String json = "{success:" + false + ",msgText:'" + "大小查出1M" + "'}";
        WebUtil.out(response, json);
    }
}
