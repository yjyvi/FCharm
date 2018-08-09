package com.whmnrc.mylibrary.utils;

import android.util.Base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yong hao zeng on 2017/12/7.
 */

public class EncodUtils {
    //base64

    public static String getBase64(String path) {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = path;//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        return Base64.encodeToString(data, Base64.DEFAULT);//返回Base64编码过的字节数组字符串
    }
    }
