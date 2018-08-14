package com.whmnrc.feimei.ui.product;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.ProductLibraryListAdapter;
import com.whmnrc.feimei.beans.ProductListBean;
import com.whmnrc.feimei.beans.ProductTypeBean;
import com.whmnrc.feimei.pop.PopProductType;
import com.whmnrc.feimei.presener.GetProductListPresenter;
import com.whmnrc.feimei.presener.GetProductTypePresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.utils.ViewRoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class SearchProductMoreActivity extends BaseActivity implements GetProductListPresenter.GetProductListListener, GetProductTypePresenter.GetProductTypeListener {


    @BindView(R.id.rv_business_list)
    RecyclerView mRvBusinessList;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.tv_composite)
    TextView mTvComposite;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.iv_type)
    ImageView mIvType;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.iv_price)
    ImageView mIvPrice;
    @BindView(R.id.ll_type)
    LinearLayout mLlType;
    @BindView(R.id.v_line)
    View mVLine;


    private String mSearchContent;
    public PopProductType mPopProductType;
    private GetProductListPresenter mGetProductListPresenter;
    public ProductLibraryListAdapter mProductLibraryListAdapter;
    private String mName = "";
    private String mCommodityClassId = "";
    private String mDesc = "";
    private String mSort = "Sort";
    private GetProductTypePresenter mGetProductTypePresenter;

    @Override
    protected void initViewData() {
        mCommodityClassId = getIntent().getStringExtra("productType");
        mGetProductListPresenter = new GetProductListPresenter(this);
        mGetProductListPresenter.getProductList(mName, mCommodityClassId);

        mGetProductTypePresenter = new GetProductTypePresenter(this);

        initRv();
        mEtSearch.setOnEditorActionListener((view, keyCode, event) -> {
            if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
                // 先隐藏键盘
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(getCurrentFocus()
                                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                mSearchContent = view.getText().toString().trim();

                if (!TextUtils.isEmpty(mSearchContent)) {

                    return true;
                }
            }
            return false;
        });

        mRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
                mGetProductListPresenter.getProductList(false, mSort, mName, mCommodityClassId, mDesc);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
                refreshLayout.setEnableLoadMore(true);
                mGetProductListPresenter.getProductList(true, mSort, mName, mCommodityClassId, mDesc);
            }
        });
    }

    private void initRv() {
        mRvBusinessList.setLayoutManager(new LinearLayoutManager(this));
        mRvBusinessList.setNestedScrollingEnabled(false);
        mProductLibraryListAdapter = new ProductLibraryListAdapter(this, R.layout.item_product_list);
        mRvBusinessList.setAdapter(mProductLibraryListAdapter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_search_product_more;
    }

    public static void start(Context context, String productType) {
        Intent starter = new Intent(context, SearchProductMoreActivity.class);
        starter.putExtra("productType", productType);
        context.startActivity(starter);
    }


    @OnClick({R.id.iv_back, R.id.ll_composite, R.id.ll_type, R.id.ll_price})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_composite:
                isViewSelect(mTvComposite, true);
                isViewSelect(mTvPrice, false);
                mIvPrice.setSelected(false);
                mDesc = "desc";
                mSort = "Sort";
                mCommodityClassId = "";
                mGetProductListPresenter.getProductList(mName, mCommodityClassId);
                break;
            case R.id.ll_type:
                mGetProductTypePresenter.getProductType();
                break;
            case R.id.ll_price:
                isViewSelect(mTvComposite, false);
                isViewSelect(mTvPrice, !mIvPrice.isSelected());
                mIvPrice.setSelected(!mIvPrice.isSelected());
                if (!mIvPrice.isSelected()) {
                    mDesc = "desc";
                } else {
                    mDesc = "asc";
                }
                mSort = "Price";
                mGetProductListPresenter.getProductList(true, mSort, mName, mCommodityClassId, mDesc);
                break;
            default:
                break;
        }
    }


    private void isViewSelect(TextView view, boolean isSelect) {

        view.setTextColor(isSelect ? ContextCompat.getColor(view.getContext(), R.color.normal_blue_text_color) : ContextCompat.getColor(view.getContext(), R.color.black));
        view.setSelected(isSelect);
    }


    @Override
    public void getProductListSuccess(ProductListBean.ResultdataBean bean, boolean isRefresh) {
        if (isRefresh) {
            mProductLibraryListAdapter.setDataArray(bean.getEnterprise());
        } else {
            List<ProductListBean.ResultdataBean.EnterpriseBean> datas = mProductLibraryListAdapter.getDatas();
            if (bean.getPagination().getRecords() == datas.size()) {
                mRefresh.setEnableLoadMore(false);
            }
            datas.addAll(bean.getEnterprise());
            mProductLibraryListAdapter.setDataArray(datas);
        }

        mProductLibraryListAdapter.notifyDataSetChanged();

        showEmpty(mProductLibraryListAdapter, mVsEmpty);
    }

    @Override
    public void getProductListField() {

    }

    @Override
    public void getProductTypeSuccess(List<ProductTypeBean.ResultdataBean> bean) {
        if (mPopProductType == null) {
            mPopProductType = new PopProductType(this, mVLine, bean);
        }

        mPopProductType.show();
        ViewRoUtils.roView(mIvType, 360f);
        mPopProductType.setPopHintListener((ProductTypeBean.ResultdataBean bean1) -> {
            mCommodityClassId = bean1.getID();
            mSort = "Sort";
            mDesc = "desc";
            mGetProductListPresenter.getProductList(true, mSort, mName, mCommodityClassId, mDesc);
        });
        mIvType.setImageResource(R.mipmap.icon_pay_year_select2);
        isViewSelect(mTvType, true);
        mPopProductType.getPopupWindow().setOnDismissListener(() -> {
            isViewSelect(mTvType, false);
            ViewRoUtils.roView(mIvType, 0f);
            mIvType.setImageResource(R.mipmap.icon_pay_year_select);
        });
    }

    @Override
    public void getProductTypeField() {

    }
}
