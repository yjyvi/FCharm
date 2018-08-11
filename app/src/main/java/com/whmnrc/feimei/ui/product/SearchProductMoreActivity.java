package com.whmnrc.feimei.ui.product;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.ProductLibraryListAdapter;
import com.whmnrc.feimei.pop.PopProductType;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.utils.TestDataUtils;
import com.whmnrc.feimei.utils.ViewRoUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class SearchProductMoreActivity extends BaseActivity {


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


    private String mSearchContent;
    public PopProductType mPopProductType;

    @Override
    protected void initViewData() {

        mRvBusinessList.setLayoutManager(new LinearLayoutManager(this));
        mRvBusinessList.setNestedScrollingEnabled(false);
        mRvBusinessList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = getResources().getDimensionPixelOffset(R.dimen.dm_8);
            }
        });
        ProductLibraryListAdapter productLibraryListAdapter = new ProductLibraryListAdapter(this, R.layout.item_product_list);
        productLibraryListAdapter.setDataArray(TestDataUtils.initTestData(15));
        mRvBusinessList.setAdapter(productLibraryListAdapter);
        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int keyCode, KeyEvent event) {
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
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_search_product_more;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, SearchProductMoreActivity.class);
        context.startActivity(starter);
    }


    @OnClick({R.id.iv_back, R.id.ll_composite, R.id.ll_type, R.id.ll_price})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_composite:
                isViewSelect(mTvComposite, !mTvComposite.isSelected());
                break;
            case R.id.ll_type:
                if (mPopProductType == null) {
                    mPopProductType = new PopProductType(this, mLlType);
                }

                mPopProductType.show();
                ViewRoUtils.roView(mIvType,180f);
                mPopProductType.setPopHintListener(new PopProductType.PopHintListener() {
                    @Override
                    public void confirm() {

                    }
                });
                mIvType.setImageResource(R.mipmap.icon_pay_year_select2);
                isViewSelect(mTvType, true);
                mPopProductType.getPopupWindow().setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        isViewSelect(mTvType, false);
                        ViewRoUtils.roView(mIvType,180f);
                        mIvType.setImageResource(R.mipmap.icon_pay_year_select);
                    }
                });

                break;
            case R.id.ll_price:
                isViewSelect(mTvPrice, !mIvPrice.isSelected());
                mIvPrice.setSelected(!mIvPrice.isSelected());
                break;
            default:
                break;
        }
    }


    private void isViewSelect(TextView view, boolean isSelect) {
//        selectedView(view);
        view.setTextColor(isSelect ? ContextCompat.getColor(view.getContext(), R.color.normal_blue_text_color) : ContextCompat.getColor(view.getContext(), R.color.black));
        view.setSelected(isSelect);
    }



}
