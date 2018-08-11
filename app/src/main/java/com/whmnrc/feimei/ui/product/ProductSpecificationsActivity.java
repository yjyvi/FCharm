package com.whmnrc.feimei.ui.product;

import android.content.Context;
import android.content.Intent;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.ui.BaseActivity;

/**
 * @author yjyvi
 * @data 2018/8/10.
 */

public class ProductSpecificationsActivity extends BaseActivity {
    @Override
    protected void initViewData() {
        setTitle("行业资源");
        rightVisible(R.mipmap.icon_share);

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_product_specifications;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ProductSpecificationsActivity.class);
        context.startActivity(starter);
    }
}
