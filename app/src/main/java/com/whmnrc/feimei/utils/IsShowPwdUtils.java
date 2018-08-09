package com.whmnrc.feimei.utils;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;

/**
 * @author yjyvi
 * @data 2018/5/10.
 */

public class IsShowPwdUtils {


    //是否显示密码
    public static void showPwd(boolean showPwd, EditText editText) {
        if (showPwd) {
            //如果选中，显示密码
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            //否则隐藏密码
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        editText.setSelection(editText.getText().toString().length());

    }
}
