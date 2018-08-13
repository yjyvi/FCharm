package com.whmnrc.feimei.network;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.BuildConfig;
import com.whmnrc.feimei.utils.ToastUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/05
 *     desc   :
 *     version: 1.0
 * </pre>
 *
 * @author yjyvi
 */
public abstract class CommonCallBack<T> implements OKHttpManager.ObjectCallback {

    @Override
    public void onFailure(int call, String e) {
        if (BuildConfig.DEBUG && !TextUtils.isEmpty(e)) {
            Log.e("CommonCallBack==", e);
        }
        onError("网络请求错误！");
    }

    @Override
    public void onSuccess(String result) {
        try {
            Type genType = this.getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            T data = JSON.parseObject(result, params[0]);
            onSuccess(data);
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            onError("解析出错了！");
        }
    }

    protected abstract void onSuccess(T data);

    protected void onError(String msg) {
        ToastUtils.showToast(msg);
    }

}
