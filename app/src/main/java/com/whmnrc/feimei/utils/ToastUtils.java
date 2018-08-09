package com.whmnrc.feimei.utils;

import android.widget.Toast;

import com.whmnrc.feimei.MyApplication;

/**
 * Created by yonghao zeng on 2017/3/27.
 */

public class ToastUtils {
    /** 之前显示的内容 */
    private static String oldMsg ;
    /** Toast对象 */
    private static Toast toast = null ;
    /** 第一次时间 */
    private static long oneTime = 0 ;
    /** 第二次时间 */
    private static long twoTime = 0 ;

    /**
     * 显示Toast
     * @param message
     */
    public static void showToast(String message){
        if(toast == null){
            toast = Toast.makeText(MyApplication.applicationContext, message, Toast.LENGTH_SHORT);
            toast.show() ;
            oneTime = System.currentTimeMillis() ;
        }else{
            twoTime = System.currentTimeMillis() ;
            if(message.equals(oldMsg)){
                if(twoTime - oneTime > Toast.LENGTH_SHORT){
                    toast.show() ;
                }
            }else{
                oldMsg = message ;
                toast.setText(message) ;
                toast.show() ;
            }
        }
        oneTime = twoTime ;
    }
}
