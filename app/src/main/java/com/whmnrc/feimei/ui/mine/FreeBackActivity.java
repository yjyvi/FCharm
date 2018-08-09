package com.whmnrc.feimei.ui.mine;

import android.content.Context;
import android.content.Intent;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.ui.BaseActivity;

/**
 * @author yjyvi
 * @data 2018/7/25.
 */

public class FreeBackActivity extends BaseActivity {
    @Override
    protected void initViewData() {
        setTitle("意见反馈");
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_free_back;
    }


    public static void start(Context context) {
        Intent starter = new Intent(context, FreeBackActivity.class);
        context.startActivity(starter);
    }
}
