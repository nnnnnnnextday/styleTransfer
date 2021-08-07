package com.styleTransfer.controller;

import com.styleTransfer.common.Const;
import com.styleTransfer.common.Json;
import com.styleTransfer.domain.Img;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 94423
 */
@Controller
public class StyleTransferController {
    @RequestMapping("/styleTransfer")
    @ResponseBody
    public Json styleTransfer() {
        String oper = "风格迁移";
        //要遍历的路径
        String path = Const.inputImgDirectory;
        //获取其file对象
        File file = new File(path);
        //遍历path下的文件和目录，放在File数组中
        File[] fs = file.listFiles();
        for (File f : fs) {
            //若非目录(即文件)，则打印
            if (!f.isDirectory())
            {
                System.out.println("styleTransfer()中:"+f.getName());
                st(f.getName());
            }
        }
        return Json.succ(oper);
    }

    public void st(String imgName) {
        /**
         *python py脚本文件路径 参数1 参数2 ....
         * 传入内容图片文件名
         */
        String[] arguments = new String[]{"python",
                "C:\\Users\\94423\\Desktop\\LearningNotes\\Study\\python\\test_style_trans.py",
                imgName};
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            //re表示操作结果是否成功。。。不是返回值
            int re = process.waitFor();
            System.out.println(re);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
