package com.whmnrc.feimei.ui;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.CommonConstant;
import com.whmnrc.feimei.MyApplication;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.UserBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.login.LoginActivity;
import com.whmnrc.feimei.utils.SPUtils;
import com.whmnrc.feimei.utils.evntBusBean.UserInfoEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/05
 *     desc   : 用户管理类
 *     version: 1.0
 * </pre>
 */
public class UserManager {

    private static UserBean.ResultdataBean sUser = null;

    public static UserBean.ResultdataBean getUser() {
        if (sUser == null) {
            String userJson = SPUtils.getString(MyApplication.applicationContext, "user_json");
            if (!TextUtils.isEmpty(userJson))
                sUser = JSON.parseObject(userJson, UserBean.ResultdataBean.class);
        }
        return sUser;
    }

    public static void refresh() {

        if (UserManager.getUser() == null) {
            return;
        }

        HashMap<String, Object> paramters = new HashMap<>(3);
        paramters.put("Mobile", String.valueOf(getUser().getMobile()));
        String url = MyApplication.applicationContext.getResources().getString(R.string.service_host_address).concat(MyApplication.applicationContext.getResources().getString(R.string.GetUserInfo));
        OKHttpManager.postString(url, paramters, new CommonCallBack<UserBean>() {
            @Override
            protected void onSuccess(UserBean data) {
                saveUser(data.getResultdata());
                EventBus.getDefault().post(new UserInfoEvent().setEventType(UserInfoEvent.UPDATE_USER_INFO));
            }
        });
    }


    public static void clearUser() {
        SPUtils.put(MyApplication.applicationContext, "user_json", "");
        sUser = null;
    }

    public static void saveUser(UserBean.ResultdataBean user) {
        SPUtils.put(MyApplication.applicationContext, "user_json", JSON.toJSONString(user));
        sUser = user;
    }

    public static boolean isLogin() {
        return getUser() != null;
    }

    public static boolean getIsLogin(Context context) {
        if (TextUtils.isEmpty(SPUtils.getString(context, CommonConstant.Common.LAST_LOGIN_ID))) {
            LoginActivity.start(context);
            return false;
        }
        return true;
    }


    public static boolean getUserIsVip() {
        if (UserManager.getUser() != null && UserManager.getUser().getVIP() != null && !TextUtils.isEmpty(UserManager.getUser().getVIP())) {
            long vipTime = Long.parseLong(UserManager.getUser().getVIP()) * 1000;
            long currentTimeMillis = System.currentTimeMillis();
            if (vipTime > currentTimeMillis) {
                return true;
            }
        }
        return false;
    }

}
