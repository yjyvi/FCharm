package com.whmnrc.feimei.ui.industry;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.ResourceListAdapter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.home.SearchActivity;
import com.whmnrc.feimei.utils.TestDataUtils;
import com.whmnrc.feimei.pop.PopShare;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/31.
 */

public class ColumnActivity extends BaseActivity {
    @BindView(R.id.tv_search)
    EditText mTvSearch;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.rv_product_list)
    RecyclerView mRvProductList;

    public String mSearchContent;
    public PopShare mPopShare;

    @Override
    protected void initViewData() {
        setTitle("专栏");
        rightVisible(R.mipmap.icon_share);

        mTvSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView view, int keyCode, KeyEvent event) {
                if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    mSearchContent = view.getText().toString().trim();

                    if (!TextUtils.isEmpty(mSearchContent)) {
                        SearchActivity.start(view.getContext(),mSearchContent,SearchActivity.SEARCH_COLUMN);
                        mTvSearch.setText("");
                        return true;
                    }
                }
                return false;
            }
        });

        mRvProductList.setLayoutManager(new LinearLayoutManager(this));
        mRvProductList.setNestedScrollingEnabled(false);
        ResourceListAdapter resourceListAdapter = new ResourceListAdapter(this, R.layout.item_information_resource_list);
        resourceListAdapter.setDataArray(TestDataUtils.initTestData(15));
        mRvProductList.setAdapter(resourceListAdapter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_column;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ColumnActivity.class);
        context.startActivity(starter);
    }


    @OnClick(R.id.rl_right)
    public void onClick() {
        if (mPopShare == null) {
            mPopShare = new PopShare(this, "1", "1", "1", "1");
        }
        mPopShare.show();
    }
}
