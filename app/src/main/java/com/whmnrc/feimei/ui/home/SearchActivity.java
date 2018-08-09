package com.whmnrc.feimei.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

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
    @BindView(R.id.ll_search_top)
    LinearLayout mLlSearchTop;
    @BindView(R.id.fl_title_bar)
    FrameLayout mFlTitleBar;
    public String mContent;


    /**
     * 产品库搜索
     */
    public static final int SEARCH_ALL = 0;
    /**
     * 产品库搜索
     */
    public static final int SEARCH_PRODUCT = 1;
    /**
     * 企业名录搜索
     */
    public static final int SEARCH_ORG = 2;
    /**
     * 行业资源搜索
     */
    public static final int SEARCH_RESOURCE = 3;
    /**
     * 专栏搜索
     */
    public static final int SEARCH_COLUMN = 4;
    /**
     * 职业搜索
     */
    public static final int SEARCH_SPECIAL = 5;
    public int mType;

    @Override
    protected void initViewData() {
        mContent = getIntent().getStringExtra("content");
        mType = getIntent().getIntExtra("type",-1);

        if (mType !=0) {
            mLlSearchTop.setVisibility(View.GONE);
            mFlTitleBar.setVisibility(View.VISIBLE);
            setTitle("搜索结果");
        }else {
            mLlSearchTop.setVisibility(View.VISIBLE);
            mFlTitleBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_search;
    }

    public static void start(Context context, String content, int type) {
        Intent starter = new Intent(context, SearchActivity.class);
        starter.putExtra("content", content);
        starter.putExtra("type", type);
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
