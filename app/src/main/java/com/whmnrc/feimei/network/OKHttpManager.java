package com.whmnrc.feimei.network;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.BuildConfig;
import com.whmnrc.feimei.MyApplication;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.utils.EncryptUtils;
import com.whmnrc.feimei.utils.NetworkUtils;
import com.whmnrc.feimei.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * @author yjyvi
 * @date 2018/1/30
 */

public class OKHttpManager {

    public static void post(String url, Map<String, String> params, final CommonCallBack callback) {
        long timestamp = System.currentTimeMillis() / 1000;
        String sing = EncryptUtils.encryptMD5ToString("10WHMNRCHNB01HNC" + timestamp);
        params.put("Sing", sing);
        params.put("Timestamp", String.valueOf(timestamp));
        if (getIsConnected()) return;
        if (BuildConfig.DEBUG) {
            Log.e("请求参数=", url + JSON.toJSONString(params));
        }

        OkHttpUtils.post().url(url).params(params).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onFailure(id, e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                callback.onSuccess(response);
            }
        });


    }

    /**
     * 是否有网络
     *
     * @return
     */
    public static boolean getIsConnected() {
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showToast(MyApplication.applicationContext.getResources().getString(R.string.app_name).concat("：Abnormal network, please check the network settings"));
            return true;
        }
        return false;
    }


    public static void get(String url, Map<String, String> params,
                           final CommonCallBack callback) {

        if (getIsConnected()) return;


        try {
            if (BuildConfig.DEBUG) {
                Log.e("请求参数=", url + JSON.toJSONString(params));
            }
            OkHttpUtils
                    .get()

                    .url(url)
                    .params(params)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            callback.onFailure(id, e.toString());
                        }

                        @Override
                        public void onResponse(String responseString, int id) {
                            if (BuildConfig.DEBUG) {
                                Log.e("返回结果=", responseString);
                            }
                            if (callback != null) {
                                callback.onSuccess(responseString);
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void postString(String url, Map<String, Object> params, final ObjectCallback objectCallback) {

        if (getIsConnected()) return;
        long timestamp = System.currentTimeMillis() / 1000;
        String sing = EncryptUtils.encryptMD5ToString("10WHMNRCHNB01HNC" + timestamp);
        params.put("Sing", sing);
        params.put("Timestamp", String.valueOf(timestamp));

        if (BuildConfig.DEBUG) {
            Log.e("OkhttpUtil", url + JSON.toJSONString(params));
        }
        OkHttpUtils.postString().content(JSON.toJSONString(params))
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                objectCallback.onFailure(id, e.getLocalizedMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                if (BuildConfig.DEBUG) {
                    Log.e("返回结果=", response);
                }
                objectCallback.onSuccess(response);
            }
        });
    }


    public static void postString2(String url, Map<Object, Object> params, final ObjectCallback objectCallback) {

        if (getIsConnected()) return;
        long timestamp = System.currentTimeMillis() / 1000;
        String sing = EncryptUtils.encryptMD5ToString("10WHMNRCHNB01HNC" + timestamp);
        params.put("Sing", sing);
        params.put("Timestamp", String.valueOf(timestamp));

        if (BuildConfig.DEBUG) {
            Log.e("OkhttpUtil", url + JSON.toJSONString(params));
        }
        OkHttpUtils.postString().content(JSON.toJSONString(params))
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                objectCallback.onFailure(id, e.getLocalizedMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                if (BuildConfig.DEBUG) {
                    Log.e("返回结果=", response);
                }
                objectCallback.onSuccess(response);
            }
        });
    }


    public interface ObjectCallback {
        void onSuccess(String st);

        void onFailure(int code, String errorMsg);

    }
}
