package com.example.demo;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class JpythonTestApplicationTests {

    @Test
    void Function1() {
        String inputImgPath =
                "C:\\Users\\94423\\Desktop\\LearningNotes\\Study\\jpythonTest-vue\\src\\assets\\img\\inputImg\\logo.png";
        /**
         *python py脚本文件路径 参数1 参数2 ....
         * 陈曦给的py是没有参数的
         *  所以不用参数，原图片和风格图片路径在py文件设置
         */
        String[] arguments = new String[]{"python",
                "C:\\Users\\94423\\Desktop\\LearningNotes\\Study\\python\\test_style_trans.py",
                "inputImgPath"};
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            int re = process.waitFor();//re表示操作结果是否成功。。。不是返回值
            System.out.println(re);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
