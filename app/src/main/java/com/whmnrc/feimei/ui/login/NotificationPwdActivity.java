package com.whmnrc.feimei.ui.login;

import android.content.Context;
import android.content.Intent;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.ui.BaseActivity;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class NotificationPwdActivity extends BaseActivity {
    @Override
    protected void initViewData() {
        setTitle("修改密码");
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_find_pwd;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, NotificationPwdActivity.class);
        context.startActivity(starter);
    }
}
