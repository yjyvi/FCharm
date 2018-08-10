package com.whmnrc.feimei.ui.table;

import android.os.Bundle;
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
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.SpecialInformationListAdapter;
import com.whmnrc.feimei.beans.GetRecruitBean;
import com.whmnrc.feimei.beans.JsonBean;
import com.whmnrc.feimei.beans.SalaryListBean;
import com.whmnrc.feimei.beans.SpecialInformationFitterBean;
import com.whmnrc.feimei.pop.PopCity;
import com.whmnrc.feimei.pop.PopMoreFitter;
import com.whmnrc.feimei.pop.PopSalaryRange;
import com.whmnrc.feimei.presener.GetRecruitPresenter;
import com.whmnrc.feimei.presener.GetSalaryPresenter;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.home.SearchActivity;
import com.whmnrc.feimei.ui.mine.MineActivity;
import com.whmnrc.feimei.utils.GetCityUtils;
import com.whmnrc.feimei.utils.evntBusBean.BaseEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * @author yjyvi
 * @date 2018/1/30
 * 特聘信息
 */

public class SpecialInformationFragment extends LazyLoadFragment implements OnRefreshLoadMoreListener, PopMoreFitter.MoreFitterListener, PopCity.CityListener, PopSalaryRange.SalaryRangeListener, GetRecruitPresenter.GetRecruitListener, GetSalaryPresenter.GetSalaryListener {

    @BindView(R.id.rv_product_list)
    RecyclerView mRvProductList;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.header_layout)
    LinearLayout headerLayout;
    @BindView(R.id.ll_city)
    LinearLayout mLlCity;
    @BindView(R.id.ll_price)
    LinearLayout mLlPrice;
    @BindView(R.id.ll_more)
    LinearLayout mLlMore;

    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.et_search)
    EditText mEtSearch;

    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.iv_city)
    ImageView mIvCity;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.iv_price)
    ImageView mIvPrice;
    @BindView(R.id.tv_more)
    TextView mTvMore;
    @BindView(R.id.iv_more)
    ImageView mIvMore;

    private PopMoreFitter mPopMoreFitter;
    private PopCity mPopCity;
    private ArrayList<JsonBean> mProvinceList;
    private List<JsonBean.CityBean> mCityList;
    private String mProvincial;
    private String mCity;
    public PopSalaryRange mPopSalaryRange;
    public GetRecruitPresenter mGetRecruitPresenter;
    public GetSalaryPresenter mGetSalaryPresenter;
    public SpecialInformationListAdapter mSpecialInformationListAdapter;
    private String mSearchContent;
    private List<SalaryListBean.ResultdataBean> mFitterData;
    private String mSalaryID;
    private String mEnterpriseId;
    private String mQualificationsId;
    private String mCrateTime;
    public List<SpecialInformationFitterBean> mSpecialInformationFitterBeans = new ArrayList<>();


    @Override
    protected int contentViewLayoutID() {
        return R.layout.fragment_special_information;
    }


    @Override
    protected void initViewData() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mGetRecruitPresenter = new GetRecruitPresenter(this);
        loadData();

        mGetSalaryPresenter = new GetSalaryPresenter(this);
        mGetSalaryPresenter.getSalary();

        mRefresh.setOnRefreshLoadMoreListener(this);
        mRvProductList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvProductList.setNestedScrollingEnabled(false);
        mSpecialInformationListAdapter = new SpecialInformationListAdapter(getActivity(), R.layout.item_recruitment_list);

        mRvProductList.setAdapter(mSpecialInformationListAdapter);


        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView view, int keyCode, KeyEvent event) {
                if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getActivity().getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    mSearchContent = view.getText().toString().trim();

                    if (!TextUtils.isEmpty(mSearchContent)) {
                        SearchActivity.start(view.getContext(), mSearchContent, SearchActivity.SEARCH_SPECIAL);
                        mEtSearch.setText("");
                        mSearchContent = "";
                        return true;
                    }
                }
                return false;
            }
        });

    }


    /**
     * 初始化实例
     *
     * @return
     */
    public static SpecialInformationFragment newInstance() {
        Bundle bundle = new Bundle();
        SpecialInformationFragment homeFragment = new SpecialInformationFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore();
        mGetRecruitPresenter.getRecruit(false, mSearchContent, mProvincial, mCity, mEnterpriseId, mQualificationsId, mSalaryID, mCrateTime);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh();
        refreshLayout.setEnableLoadMore(true);
        loadData();
    }


    /**
     * 修改货币显示
     *
     * @param goodsCommentEvent
     */
    @Subscribe
    public void changePrice(BaseEvent goodsCommentEvent) {

    }


    @OnClick({R.id.iv_user_info, R.id.ll_city, R.id.ll_price, R.id.ll_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_info:
                if (!UserManager.getIsLogin(getActivity())) {
                    return;
                }
                MineActivity.start(view.getContext());
                break;
            case R.id.ll_city:
                if (mPopCity != null && mPopCity.isShow()) {
                    mPopCity.dissmiss();
                }
                if (mPopCity == null) {
                    mPopCity = new PopCity(getActivity(), this, mLlCity);
                }
                isViewSelect(mTvCity, true);
                mIvCity.setRotation(180);
                mPopCity.show();
                mPopCity.getmPopupWindow().setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        mIvCity.setRotation(0);
                        isViewSelect(mTvCity, false);
                    }
                });
                break;
            case R.id.ll_price:
                if (mPopSalaryRange != null && mPopSalaryRange.isShow()) {
                    mPopSalaryRange.dissmiss();
                }
                if (mFitterData == null) {
                    return;
                }
                ArrayList<SalaryListBean.ResultdataBean> beans = new ArrayList<>();
                for (SalaryListBean.ResultdataBean fitterDatum : mFitterData) {
                    if (fitterDatum.getType() == 1) {
                        beans.add(fitterDatum);
                    }
                }
                if (mPopSalaryRange == null) {
                    mPopSalaryRange = new PopSalaryRange(getActivity(), this, mLlPrice, beans);
                }
                mPopSalaryRange.show();
                mPopSalaryRange.getPopupWindow().setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        mIvPrice.setRotation(0);
                        isViewSelect(mTvPrice, false);
                    }
                });

                mIvPrice.setRotation(180);
                isViewSelect(mTvPrice, true);
                break;
            case R.id.ll_more:
                if (mPopMoreFitter != null && mPopMoreFitter.isShow()) {
                    mPopMoreFitter.dissmiss();
                }
                if (mPopMoreFitter == null) {
                    mPopMoreFitter = new PopMoreFitter(getActivity(), this, mLlCity);
                }
                mPopMoreFitter.show();
                mPopMoreFitter.getmPopupWindow().setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        mIvMore.setRotation(0);
                        isViewSelect(mTvMore, false);
                    }
                });
                mIvMore.setRotation(180);
                isViewSelect(mTvMore, true);
                break;
            default:
                break;
        }
    }

    @Override
    public ArrayList<JsonBean> onLoad() {
        if (mProvinceList != null)
            return mProvinceList;

        //解析数据
        String jsonData = GetCityUtils.getJson(getActivity(), "province.json");
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
                loadData();
            } else {
                mProvincial = mProvinceList.get(position).getName();
                mCityList = mProvinceList.get(position).getCityList();
                mPopCity.setTwoList(mCityList);
            }
        } else {
            mCity = mCityList.get(position).getName();
            mPopCity.dissmiss();
            loadData();
        }
    }

    @Override
    public void onLoad(String name) {

    }

    private void isViewSelect(TextView view, boolean isSelect) {
        view.setTextColor(isSelect ? ContextCompat.getColor(view.getContext(), R.color.normal_blue_text_color) : ContextCompat.getColor(view.getContext(), R.color.black));
    }


    @Override
    public List<SpecialInformationFitterBean> onLoadFitterData() {
        if (mSpecialInformationFitterBeans.size() == 0) {
            SpecialInformationFitterBean specialInformationFitterBean;
            for (int i = 0; i < 2; i++) {
                switch (i) {
                    case 0:
                        specialInformationFitterBean = new SpecialInformationFitterBean();
                        specialInformationFitterBean.setName("发布日期");
                        specialInformationFitterBean.setPosition(0);
                        //发布日期
                        ArrayList<SpecialInformationFitterBean.DataListBean> dataListBeans = new ArrayList<>();
                        for (int j = 0; j < 7; j++) {
                            SpecialInformationFitterBean.DataListBean dataListBean = new SpecialInformationFitterBean.DataListBean();
                            switch (j) {
                                case 0:
                                    dataListBean.setName("所有日期");
                                    break;
                                case 1:
                                    dataListBean.setName("24小时内");
                                    dataListBean.setId("1");
                                    break;
                                case 2:
                                    dataListBean.setName("近三天");
                                    dataListBean.setId("3");
                                    break;
                                case 3:
                                    dataListBean.setName("近一周");
                                    dataListBean.setId("7");
                                    break;
                                case 4:
                                    dataListBean.setName("近一月");
                                    dataListBean.setId("30");
                                    break;
                                case 5:
                                    dataListBean.setName("近半年");
                                    dataListBean.setId(String.valueOf(365 / 2));
                                    break;
                                case 6:
                                    dataListBean.setName("近一年");
                                    dataListBean.setId("365");
                                    break;
                                default:
                                    break;

                            }
                            dataListBeans.add(dataListBean);
                            specialInformationFitterBean.setDataListBeans(dataListBeans);
                        }

                        mSpecialInformationFitterBeans.add(specialInformationFitterBean);
                        break;
                    case 1:
                        if (mFitterData != null) {
                            specialInformationFitterBean = new SpecialInformationFitterBean();
                            specialInformationFitterBean.setPosition(1);
                            specialInformationFitterBean.setName("学历要求");
                            ArrayList<SpecialInformationFitterBean.DataListBean> educationBeans = new ArrayList<>();
                            for (SalaryListBean.ResultdataBean fitterDatum : mFitterData) {
                                if (fitterDatum.getType() == 0) {
                                    SpecialInformationFitterBean.DataListBean educationBean = new SpecialInformationFitterBean.DataListBean();
                                    educationBean.setId(fitterDatum.getID());
                                    educationBean.setName(fitterDatum.getName());
                                    educationBeans.add(educationBean);
                                    specialInformationFitterBean.setDataListBeans(educationBeans);
                                }
                            }
                            mSpecialInformationFitterBeans.add(specialInformationFitterBean);
                        }
                        break;
                    default:
                        break;
                }
            }
        }


        return mSpecialInformationFitterBeans;
    }

    @Override
    public void onSelectFitter(int parentPosition, int position, int type) {
        if (type == 1) {
            if (parentPosition == 0) {
                if (position == -1) {
                    mCrateTime = "";
                } else {
                    mCrateTime = mSpecialInformationFitterBeans.get(parentPosition).getDataListBeans().get(position).getId();
                }
            } else if (parentPosition == 1) {
                if (position == -1) {
                    mQualificationsId = "";
                } else {
                    mQualificationsId = mSpecialInformationFitterBeans.get(parentPosition).getDataListBeans().get(position).getId();
                }
            }else {
                mCrateTime = "";
                mQualificationsId = "";
            }
            loadData();
        }

    }

    private void loadData() {
        mGetRecruitPresenter.getRecruit(true, mSearchContent, mProvincial, mCity, mEnterpriseId, mQualificationsId, mSalaryID, mCrateTime);
    }


    @Override
    public void onSelectSalaryRange(String contentId) {
        mSalaryID = contentId;
        loadData();
    }

    @Override
    public void getRecruitSuccess(GetRecruitBean.ResultdataBean bean, boolean isRefresh) {

        if (isRefresh) {

            mSpecialInformationListAdapter.setDataArray(bean.getRecruit());
        } else {
            if (mSpecialInformationListAdapter.getDatas().size() == bean.getPagination().getRecords()) {
                mRefresh.setEnableLoadMore(false);
            }

            List<GetRecruitBean.ResultdataBean.RecruitBean> datas = mSpecialInformationListAdapter.getDatas();
            datas.addAll(bean.getRecruit());
            mSpecialInformationListAdapter.setDataArray(datas);
        }


        mSpecialInformationListAdapter.notifyDataSetChanged();

        showEmpty(mSpecialInformationListAdapter, mVsEmpty);
    }

    @Override
    public void getRecruitField() {

    }

    @Override
    public void getSalarySuccess(List<SalaryListBean.ResultdataBean> beans) {
        this.mFitterData = beans;
    }

    @Override
    public void getSalaryField() {

    }
}
