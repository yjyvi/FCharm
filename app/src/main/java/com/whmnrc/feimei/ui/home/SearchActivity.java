package com.whmnrc.feimei.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.EditText;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class SearchActivity extends BaseActivity {
    @BindView(R.id.et_search_content)
    EditText mEtSearchContent;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.rv_product_list)
    RecyclerView mRvProductList;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;

    @Override
    protected void initViewData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_search;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, SearchActivity.class);
        context.startActivity(starter);
    }


    @OnClick({R.id.iv_back, R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                break;
            default:
                break;
        }
    }
}
