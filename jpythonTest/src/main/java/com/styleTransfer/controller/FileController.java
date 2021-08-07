package com.styleTransfer.controller;

import com.styleTransfer.common.Const;
import com.styleTransfer.common.Json;
import com.styleTransfer.domain.Img;
import com.zaxxer.hikari.util.ConcurrentBag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 94423
 */
@RestController
public class FileController {
    @RequestMapping("/getInputImgList")
    @ResponseBody
    public Json getInputImgList() throws IOException {
        String oper = "获取内容图片集";
        String realPath = Const.inputImgDirectory;
        System.out.println(realPath);
        File file = new File(realPath);
        File[] tempList = file.listFiles();
        List<Img> fileNameList = new ArrayList<>();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                Img img = new Img(tempList[i].getName(),realPath+tempList[i].getName());
                fileNameList.add(img);
            }
        }
        return Json.succ(oper).data("msg","hyh").data(fileNameList);
    }


    @RequestMapping("/upload")
    @ResponseBody
    public Json fileUpLoad(MultipartFile file, HttpServletRequest req){
        String oper = "上传图片";
        String realPath = Const.inputImgDirectory;
        File folder = new File((realPath));
        if(!folder.exists()){
            folder.mkdirs();
        }
        String newName = file.getOriginalFilename();
        try {
            file.transferTo(new File(folder, newName));
            String url = realPath+newName;
            return Json.succ(oper).data("url",url).data("status","success");
        } catch (IOException e) {
             e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/getAfterStyleTransferImgList")
    @ResponseBody
    public Json getAfterStyleTransferImgList() throws IOException {
        String oper = "获取结果图片集";
        String realPath = Const.afterSytleTransferImgDirectory;
        File file = new File(realPath);
        File[] tempList = file.listFiles();
        List<Img> fileNameList = new ArrayList<>();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                Img img = new Img(tempList[i].getName(),realPath+tempList[i].getName());
                fileNameList.add(img);
            }
        }
        return Json.succ(oper).data("msg","hyh").data(fileNameList);
    }

    @RequestMapping("/deleteInputImg")
    @ResponseBody
    public Json deleteInputImg(@RequestParam String fileName) throws IOException {
        String oper = "删除内容文件";
        System.out.println(fileName);
        String realPath = Const.inputImgDirectory;
        File file = new File(realPath+fileName);
        if (file.exists()) {
            if (file.delete()) {
                return Json.succ(oper).data("msg","图片删除成功");
            } else {
                return Json.fail(oper).data("msg","图片删除失败");
            }
        } else {
            return Json.succ(oper).data("msg","图片不存在");
        }
    }
    @RequestMapping("/deleteAfterStyleTransferImg")
    @ResponseBody
    public Json deleteAfterStyleTransferImg(@RequestParam String fileName) throws IOException {
        String oper = "删除结果文件";
        System.out.println(fileName);
        String realPath = Const.afterSytleTransferImgDirectory;
        File file = new File(realPath+fileName);
        if (file.exists()) {
            if (file.delete()) {
                return Json.succ(oper).data("msg","图片删除成功");
            } else {
                return Json.fail(oper).data("msg","图片删除失败");
            }
        } else {
            return Json.fail(oper).data("msg","图片不存在");
        }
    }
}
