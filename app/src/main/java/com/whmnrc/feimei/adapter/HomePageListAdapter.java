package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.HomeDataBean;
import com.whmnrc.feimei.eventbus.HomeTableChangeEvent;
import com.whmnrc.feimei.ui.organization.SearchBusinessMoreActivity;
import com.whmnrc.feimei.ui.product.SearchProductMoreActivity;
import com.whmnrc.feimei.utils.EmptyListUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * @author yjyvi
 * @data 2018/5/18.
 */

public class HomePageListAdapter extends CommonAdapter<HomeDataBean.ResultdataBean> {


    public HomePageListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }


    @Override
    public void convert(ViewHolder holder, final HomeDataBean.ResultdataBean homeBean, final int position) {

        holder.setIsRecyclable(false);

        RecyclerView mRvSaleList = holder.getView(R.id.rv_list);
        mRvSaleList.setNestedScrollingEnabled(false);

        mRvSaleList.setLayoutManager(new LinearLayoutManager(mContext));

        int itemProductList;
        if (position == 0) {
            itemProductList = R.layout.item_product_list;
            holder.setText(R.id.tv_sale_name, "推荐行业产品库");
        } else if (position == 1) {
            itemProductList = R.layout.item_recruitment_list;
            holder.setText(R.id.tv_sale_name, "推荐特聘信息");
        } else if (position == 2) {
            itemProductList = R.layout.item_enterprise_list;
            holder.setText(R.id.tv_sale_name, "推荐企业名录");
        } else {
            holder.setText(R.id.tv_sale_name, "推荐行业资源");
            itemProductList = R.layout.item_resource_list;
        }

        final int selectPosition = position;

        HomeRecommendListAdapter mGoodListAdapter = new HomeRecommendListAdapter(mContext, itemProductList, position);
        if (position == 0) {
            mGoodListAdapter.setDataArray(homeBean.getCommodity());
        } else if (position == 1) {
            mGoodListAdapter.setDataArray(homeBean.getRecruit());
        } else if (position == 2) {
            mGoodListAdapter.setDataArray(homeBean.getEnterprise());
        } else {
            mGoodListAdapter.setDataArray(homeBean.getRead());
        }

        mRvSaleList.setAdapter(mGoodListAdapter);

        showEmpty(mGoodListAdapter, holder.getView(R.id.vs_empty));

        mGoodListAdapter.setGoToDetailsListener(position1 -> {
            if (selectPosition == 1) {
                HomeDataBean.ResultdataBean.RecruitBean recruitBean = homeBean.getRecruit().get(position1);
                recruitBean.setClickNumber(recruitBean.getClickNumber() + 1);
            } else {
                HomeDataBean.ResultdataBean.CommodityBean recruitBean = homeBean.getCommodity().get(position1);
                recruitBean.setClickNumber(recruitBean.getClickNumber() + 1);
            }
            mGoodListAdapter.notifyItemChanged(position1);
        });

        holder.setOnClickListener(R.id.tv_more, v -> {
            if (position == 0) {
                SearchProductMoreActivity.start(mContext, "");
            } else if (position == 1) {
                EventBus.getDefault().post(new HomeTableChangeEvent().setEventType(HomeTableChangeEvent.TO_SPECIAL_INFORMATION));
            } else if (position == 2) {
                SearchBusinessMoreActivity.start(mContext);
            } else {
                EventBus.getDefault().post(new HomeTableChangeEvent().setEventType(HomeTableChangeEvent.TO_INDUSTRY_RESOURCES));
            }
        });

        if (position == getDatas().size() - 1) {
            if (mDataLoadFinishListener != null) {
                mDataLoadFinishListener.loadFinish();
            }
        }
    }

    private DataLoadFinishListener mDataLoadFinishListener;

    public void setDataLoadFinishListener(DataLoadFinishListener dataLoadFinishListener) {
        mDataLoadFinishListener = dataLoadFinishListener;
    }

    public interface DataLoadFinishListener {
        void loadFinish();
    }


    public void showEmpty(HomeRecommendListAdapter mGoodListAdapter, ViewStub mVsEmpty) {
        if (mGoodListAdapter != null && mGoodListAdapter.getDatas() != null && mGoodListAdapter.getDatas().size() == 0) {
            EmptyListUtils.loadEmpty(true, "暂无任何信息!", 0, mVsEmpty);
        } else {
            if (mVsEmpty != null) {
                mVsEmpty.setVisibility(View.GONE);
            }
        }
    }

}
