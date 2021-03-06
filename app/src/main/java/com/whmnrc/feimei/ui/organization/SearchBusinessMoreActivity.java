package com.whmnrc.feimei.ui.organization;

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
import com.whmnrc.feimei.adapter.BusinessMoreListAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.beans.EnterpriseListBean;
import com.whmnrc.feimei.beans.IndustryBean;
import com.whmnrc.feimei.beans.JsonBean;
import com.whmnrc.feimei.pop.PopCity;
import com.whmnrc.feimei.pop.PopIndustry;
import com.whmnrc.feimei.pop.PopMoreFitter;
import com.whmnrc.feimei.presener.GetEnterprisePresenter;
import com.whmnrc.feimei.presener.GetIndustryPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.mine.PayVipActivity;
import com.whmnrc.feimei.utils.GetCityUtils;
import com.whmnrc.feimei.utils.ToastUtils;
import com.whmnrc.feimei.utils.ViewRoUtils;
import com.whmnrc.feimei.utils.evntBusBean.PayEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class SearchBusinessMoreActivity extends BaseActivity implements PopCity.CityListener, GetIndustryPresenter.GetIndustryListener, GetEnterprisePresenter.GetEnterpriseListener {


    @BindView(R.id.rv_business_list)
    RecyclerView mRvBusinessList;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.iv_vip_dialog)
    ImageView mIvVipDialog;
    @BindView(R.id.ll_city)
    LinearLayout mLlCity;


    public PopCity mPopCity;
    public PopMoreFitter mPopMoreFitter;
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.iv_city)
    ImageView mIvCity;
    @BindView(R.id.tv_industry)
    TextView mTvIndustry;
    @BindView(R.id.iv_industry)
    ImageView mIvIndustry;

    private ArrayList<JsonBean> mProvinceList;
    public List<JsonBean.CityBean> mCityList;
    public PopIndustry mPopIndustry;
    private GetIndustryPresenter mGetIndustryPresenter;
    public List<IndustryBean.ResultdataBean.SubsetBean> mSubset;
    public GetEnterprisePresenter mGetEnterprisePresenter;
    public BusinessMoreListAdapter mBusinessMoreListAdapter;
    public String mIndustryId;
    private String mIndustryPid;
    public String mEnterpriseTypeID;
    private String mSearchContent;
    private String mProvincial;
    private String mCity;

    @Override
    protected void initViewData() {

        EventBus.getDefault().register(this);

        isShowDialog(true);
        mEnterpriseTypeID = getIntent().getStringExtra("enterpriseTypeID");

        if (UserManager.getUserIsVip()) {
            mIvVipDialog.setVisibility(View.GONE);
        } else {
            mIvVipDialog.setVisibility(View.VISIBLE);
        }

        mGetIndustryPresenter = new GetIndustryPresenter(this);
        mGetEnterprisePresenter = new GetEnterprisePresenter(this);


        mRvBusinessList.setLayoutManager(new LinearLayoutManager(this));
        mRvBusinessList.setNestedScrollingEnabled(false);
        mBusinessMoreListAdapter = new BusinessMoreListAdapter(this, R.layout.item_enterprise_list);
        mRvBusinessList.setAdapter(mBusinessMoreListAdapter);
        mBusinessMoreListAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                OrganizationDetailsActivity.start(view.getContext(), mBusinessMoreListAdapter.getDatas().get(position).getID());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        mGetEnterprisePresenter.searchEnterpriseList(true, mSearchContent, mProvincial, mCity, mEnterpriseTypeID, mIndustryPid, mIndustryId);


        mEtSearch.setOnEditorActionListener((view, keyCode, event) -> {
            if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
                // 先隐藏键盘
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(getCurrentFocus()
                                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                mSearchContent = view.getText().toString().trim();

                if (!TextUtils.isEmpty(mSearchContent)) {
                    isShowDialog(true);
                    mGetEnterprisePresenter.searchEnterpriseList(true, mSearchContent, mProvincial, mCity, mEnterpriseTypeID, mIndustryPid, mIndustryId);
                    return true;
                }
            }
            return false;
        });


        mRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
                refreshLayout.setEnableLoadMore(true);
                mGetEnterprisePresenter.searchEnterpriseList(true, mSearchContent, mProvincial, mCity, mEnterpriseTypeID, mIndustryPid, mIndustryId);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
                if (UserManager.getUserIsVip()) {
                    mGetEnterprisePresenter.searchEnterpriseList(false, mSearchContent, mProvincial, mCity, mEnterpriseTypeID, mIndustryPid, mIndustryId);
                } else {
                    mRefresh.setEnableLoadMore(false);
                    ToastUtils.showToast("开通VIP会员，可以查看更多");
                }
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_search_business_more;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, SearchBusinessMoreActivity.class);
        context.startActivity(starter);
    }

    public static void start(Context context, boolean isRecommend, String enterpriseTypeID) {
        Intent starter = new Intent(context, SearchBusinessMoreActivity.class);
        starter.putExtra("isRecommend", isRecommend);
        starter.putExtra("enterpriseTypeID", enterpriseTypeID);
        context.startActivity(starter);
    }


    @OnClick({R.id.iv_back, R.id.ll_city, R.id.ll_industry, R.id.iv_vip_dialog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_city:
                mIvCity.setImageResource(R.mipmap.icon_type_more_select);
                ViewRoUtils.roView(mIvCity, 360f);
                if (mPopCity == null) {
                    mPopCity = new PopCity(SearchBusinessMoreActivity.this, this, mLlCity);
                }
                mPopCity.mPopupWindow.setOnDismissListener(() -> {
                    mIvCity.setImageResource(R.mipmap.icon_type_more);
                    ViewRoUtils.roView(mIvCity, 0f);
                    isViewSelect(mTvCity, false);
                    mTvCity.setText("城市");
                });
                mLlCity.post(() -> mPopCity.show(mLlCity));
                isViewSelect(mTvCity, true);
                break;
            case R.id.ll_industry:
                mGetIndustryPresenter.getIndustryList();
                break;
            case R.id.iv_vip_dialog:

                if (!UserManager.getIsLogin(view.getContext())) {
                    return;
                }

                PayVipActivity.start(view.getContext());
                break;
            default:
                break;
        }
    }


    private void isViewSelect(TextView view, boolean isSelect) {
        view.setTextColor(isSelect ? ContextCompat.getColor(view.getContext(), R.color.normal_blue_text_color) : ContextCompat.getColor(view.getContext(), R.color.black));
    }


    @Override
    public ArrayList<JsonBean> onLoad() {
        if (mProvinceList != null)
            return mProvinceList;

        //解析数据
        String jsonData = GetCityUtils.getJson(this, "province.json");
        mProvinceList = GetCityUtils.parseData(jsonData);
        JsonBean jsonBean = new JsonBean();
        jsonBean.setName("全国");
        mProvinceList.add(0, jsonBean);
        return mProvinceList;
    }

    @Override
    public void onSelect(int position, int type) {
        if (type == 0) {
            if (TextUtils.equals(mProvinceList.get(position).getName(), "全国")) {
                if (mPopCity != null) {
                    mPopCity.dissmiss();
                }
                mProvincial = "";
                mCity = "";
                mGetEnterprisePresenter.searchEnterpriseList(true, mSearchContent, mProvincial, mCity, mEnterpriseTypeID, mIndustryPid, mIndustryId);
            } else {
                mCityList = mProvinceList.get(position).getCityList();
                if (mPopCity != null) {
                    mProvincial = mProvinceList.get(position).getName();
                    mPopCity.setTwoList(mCityList);
                }
            }


        } else {
            mCity = mCityList.get(position).getName();
            if (mPopCity != null) {
                mPopCity.dissmiss();
            }
            mTvCity.setText(mCity);
            mGetEnterprisePresenter.searchEnterpriseList(true, mSearchContent, mProvincial, mCity, mEnterpriseTypeID, mIndustryPid, mIndustryId);
        }
    }

    @Override
    public void onLoad(String name) {

    }


    @Override
    public void getIndustrySuccess(final List<IndustryBean.ResultdataBean> beans) {
        mIvIndustry.setImageResource(R.mipmap.icon_type_more_select);
        if (mPopIndustry == null) {
            mPopIndustry = new PopIndustry(SearchBusinessMoreActivity.this, new PopIndustry.CityListener() {
                @Override
                public ArrayList<IndustryBean.ResultdataBean> onLoad() {
                    IndustryBean.ResultdataBean element = new IndustryBean.ResultdataBean();
                    element.setName("不限行业");
                    element.setID("-1");
                    beans.add(0, element);
                    return (ArrayList<IndustryBean.ResultdataBean>) beans;
                }

                @Override
                public void onSelect(int position, int type) {
                    if (type == 0) {
                        if (mPopIndustry != null) {
                            if (TextUtils.equals("-1", beans.get(position).getID())) {
                                mIndustryPid = "";
                                mIndustryId = "";
                                mGetEnterprisePresenter.searchEnterpriseList(true, mSearchContent, mProvincial, mCity, mEnterpriseTypeID, mIndustryPid, mIndustryId);
                                if (mPopIndustry != null) {
                                    mPopIndustry.dissmiss();
                                }
                            } else {
                                mSubset = beans.get(position).getSubset();
                                mIndustryPid = beans.get(position).getID();
                                IndustryBean.ResultdataBean.SubsetBean element = new IndustryBean.ResultdataBean.SubsetBean();
                                element.setName("全部");
                                element.setPID(beans.get(position).getID());

                                if (mSubset.size() > 0 && !"全部".equals(mSubset.get(0).getName())) {
                                    mSubset.add(0, element);
                                }

                                if (mSubset.size() == 0) {
                                    mSubset.add(element);
                                }

                                mPopIndustry.setTwoList(mSubset);
                            }
                        }
                    } else {
//                        if (TextUtils.equals("全部", mSubset.get(position).getName())) {
//                            mIndustryPid = mSubset.get(position).getPID();
//                        } else {
                            mIndustryPid = mSubset.get(position).getPID();
//                        }
                        mIndustryId = mSubset.get(position).getID();
                        if (mPopIndustry != null) {
                            mPopIndustry.dissmiss();
                        }
                        mTvIndustry.setText(mSubset.get(position).getName());
                        mGetEnterprisePresenter.searchEnterpriseList(true, mSearchContent, mProvincial, mCity, mEnterpriseTypeID, mIndustryPid, mIndustryId);
                    }
                }

                @Override
                public void onLoad(String name) {

                }
            }, mLlCity);
        }

        mLlCity.post(() -> mPopIndustry.show(mLlCity));

        mPopIndustry.getmPopupWindow().setOnDismissListener(() -> {
            mIvIndustry.setImageResource(R.mipmap.icon_type_more);
            isViewSelect(mTvIndustry, false);
        });
        isViewSelect(mTvIndustry, true);
    }

    @Override
    public void getIndustryField() {

    }

    @Override
    public void getEnterpriseSuccess(EnterpriseListBean.ResultdataBean beans, boolean isRefresh) {

        if (isRefresh) {
            mBusinessMoreListAdapter.setDataArray(beans.getEnterprise());
            mBusinessMoreListAdapter.notifyDataSetChanged();
        } else {
            if (beans.getPagination().getRecords() == mBusinessMoreListAdapter.getDatas().size()) {
                mRefresh.setEnableLoadMore(false);
                return;
            }

            List<EnterpriseListBean.ResultdataBean.EnterpriseBean> datas = mBusinessMoreListAdapter.getDatas();
            datas.addAll(beans.getEnterprise());
            mBusinessMoreListAdapter.setDataArray(datas);
            mBusinessMoreListAdapter.notifyDataSetChanged();
        }

        showEmpty(mBusinessMoreListAdapter, mVsEmpty);

        isShowDialog(false);
    }

    @Override
    public void getEnterpriseField() {

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
        mPopMoreFitter = null;
        mPopCity = null;
        mPopIndustry = null;
    }


    @Subscribe
    public void payEvent(PayEvent payEvent) {
        if (payEvent.getEventType() == PayEvent.PAY_VIP_SUCCESS) {
            mIvVipDialog.setVisibility(View.GONE);
        }
    }
}
