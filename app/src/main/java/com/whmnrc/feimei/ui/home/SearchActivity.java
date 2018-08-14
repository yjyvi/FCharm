package com.whmnrc.feimei.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.SearchResultListAdapter;
import com.whmnrc.feimei.beans.EnterpriseListBean;
import com.whmnrc.feimei.beans.GetRecruitBean;
import com.whmnrc.feimei.beans.ProductListBean;
import com.whmnrc.feimei.beans.SearchConditionBean;
import com.whmnrc.feimei.presener.GetEnterprisePresenter;
import com.whmnrc.feimei.presener.GetProductListPresenter;
import com.whmnrc.feimei.presener.GetRecruitPresenter;
import com.whmnrc.feimei.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/24.
 * 综合搜索页面
 */

public class SearchActivity extends BaseActivity implements GetRecruitPresenter.GetRecruitListener, GetProductListPresenter.GetProductListListener, GetEnterprisePresenter.GetEnterpriseListener {
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
    private GetRecruitPresenter mGetRecruitPresenter;
    private GetProductListPresenter mGetProductListPresenter;
    private GetEnterprisePresenter mGetEnterprisePresenter;
    public SearchConditionBean mSearchConditionBean;
    public SearchResultListAdapter mAdapter;
    private String mSearchContent;

    @Override
    protected void initViewData() {
        mType = getIntent().getIntExtra("type", -1);

        mRvProductList.setLayoutManager(new LinearLayoutManager(this));

        if (mType != 0) {
            mLlSearchTop.setVisibility(View.GONE);
            mFlTitleBar.setVisibility(View.VISIBLE);
            setTitle("搜索结果");
            mSearchConditionBean = getIntent().getParcelableExtra("searchConditionBean");
        } else {
            mLlSearchTop.setVisibility(View.VISIBLE);
            mFlTitleBar.setVisibility(View.GONE);


            mEtSearchContent.setOnEditorActionListener((view, keyCode, event) -> {
                if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    mSearchContent = view.getText().toString().trim();

                    if (!TextUtils.isEmpty(mSearchContent)) {
                        mSearchContent = "";
                        mEtSearchContent.setText("");
                        return true;
                    }
                }
                return false;
            });
        }

        if (mSearchConditionBean == null) {
            return;
        }

        switch (mType) {
            case SEARCH_ALL:
                break;
            case SEARCH_PRODUCT:

                mAdapter = new SearchResultListAdapter(this, R.layout.item_product_list, mType);

                mGetProductListPresenter = new GetProductListPresenter(this);
                mGetProductListPresenter.getProductList(true,
                        mSearchConditionBean.getSort(),
                        mSearchConditionBean.getContent(),
                        mSearchConditionBean.getCommodityClassId(),
                        mSearchConditionBean.getDesc());


                break;
            case SEARCH_ORG:

                mAdapter = new SearchResultListAdapter(this, R.layout.item_order_list, mType);

                mGetEnterprisePresenter = new GetEnterprisePresenter(this);
                mGetEnterprisePresenter.searchEnterpriseList(true,
                        mSearchConditionBean.getContent(),
                        mSearchConditionBean.getProvincial(),
                        mSearchConditionBean.getCity(),
                        mSearchConditionBean.getEnterpriseTypeID(),
                        mSearchConditionBean.getIndustryPid(),
                        mSearchConditionBean.getIndustryId());

                break;
            case SEARCH_RESOURCE:
                break;
            case SEARCH_COLUMN:
                break;
            case SEARCH_SPECIAL:

                mAdapter = new SearchResultListAdapter(this, R.layout.item_recruitment_list, mType);

                mGetRecruitPresenter = new GetRecruitPresenter(this);
                mGetRecruitPresenter.getRecruit(true,
                        mSearchConditionBean.getContent(),
                        mSearchConditionBean.getProvincial(),
                        mSearchConditionBean.getCity(),
                        mSearchConditionBean.getEnterpriseId(),
                        mSearchConditionBean.getQualificationsId(),
                        mSearchConditionBean.getSalaryID(),
                        mSearchConditionBean.getCrateTime());
                break;
            default:
                break;
        }

        mRvProductList.setAdapter(mAdapter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_search;
    }

    public static void start(Context context, int type, SearchConditionBean searchConditionBean) {
        Intent starter = new Intent(context, SearchActivity.class);
        starter.putExtra("type", type);
        starter.putExtra("searchConditionBean", searchConditionBean);
        context.startActivity(starter);
    }


    @OnClick({R.id.iv_search_back, R.id.tv_all_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search_back:
                finish();
                break;
            case R.id.tv_all_search:
                break;
            default:
                break;
        }
    }

    @Override
    public void getRecruitSuccess(GetRecruitBean.ResultdataBean bean, boolean isRefresh) {
        mAdapter.setDataArray(bean.getRecruit());
        mAdapter.notifyDataSetChanged();
        showEmpty(mAdapter, mVsEmpty);
    }

    @Override
    public void getRecruitField() {

    }

    @Override
    public void getProductListSuccess(ProductListBean.ResultdataBean bean, boolean isRefresh) {
        mAdapter.setDataArray(bean.getEnterprise());
        mAdapter.notifyDataSetChanged();
        showEmpty(mAdapter, mVsEmpty);
    }

    @Override
    public void getProductListField() {

    }

    @Override
    public void getEnterpriseSuccess(EnterpriseListBean.ResultdataBean beans, boolean isRefresh) {
        mAdapter.setDataArray(beans.getEnterprise());
        mAdapter.notifyDataSetChanged();
        showEmpty(mAdapter, mVsEmpty);
    }

    @Override
    public void getEnterpriseField() {

    }
}
