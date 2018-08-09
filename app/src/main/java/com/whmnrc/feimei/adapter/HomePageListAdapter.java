package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.eventbus.HomeTableChangeEvent;
import com.whmnrc.feimei.ui.CommonH5WebView;
import com.whmnrc.feimei.ui.industry.ColumnActivity;
import com.whmnrc.feimei.ui.organization.OrganizationDetailsActivity;
import com.whmnrc.feimei.ui.organization.SearchBusinessMoreActivity;
import com.whmnrc.feimei.ui.product.ProductDetailsActivity;
import com.whmnrc.feimei.ui.product.SearchProductMoreActivity;
import com.whmnrc.feimei.utils.TestDataUtils;
import com.whmnrc.feimei.pop.PopHintInfo;

import org.greenrobot.eventbus.EventBus;

/**
 * @author yjyvi
 * @data 2018/5/18.
 */

public class HomePageListAdapter extends CommonAdapter {

    public PopHintInfo mPopHintInfo;

    public HomePageListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }


    @Override
    public void convert(ViewHolder holder, final Object goodsBean, final int position) {
        RecyclerView mRvSaleList = holder.getView(R.id.rv_list);
        mRvSaleList.setNestedScrollingEnabled(false);

        mRvSaleList.setLayoutManager(new LinearLayoutManager(mContext));

        int itemProductList;
        if (position == 0) {
            itemProductList = R.layout.item_product_list;
        } else if (position == 1) {
            itemProductList = R.layout.item_recruitment_list;
        } else if (position == 2) {
            itemProductList = R.layout.item_enterprise_list;
        } else {
            itemProductList = R.layout.item_resource_list;
        }

        final int selectPosition = position;

        HomeRecommendListAdapter mGoodListAdapter = new HomeRecommendListAdapter(mContext, itemProductList, position);
        mGoodListAdapter.setDataArray(TestDataUtils.initTestData(5));
        mRvSaleList.setAdapter(mGoodListAdapter);

        mGoodListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (selectPosition == 0) {
                    ProductDetailsActivity.start(view.getContext());
                } else if (selectPosition == 1) {
                    CommonH5WebView.startCommonH5WebView(view.getContext(), "", "职位详情");
                } else if (selectPosition == 2) {
                    OrganizationDetailsActivity.start(view.getContext(),"");
                } else {
                    ColumnActivity.start(view.getContext());
                }

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        holder.setOnClickListener(R.id.tv_more, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopHintInfo == null) {
                    mPopHintInfo = new PopHintInfo(mContext, "查看更多信息需要付费\n请问是否继");
                }
                mPopHintInfo.show();
                mPopHintInfo.setPopHintListener(new PopHintInfo.PopHintListener() {
                    @Override
                    public void confirm() {
                        if (position == 0) {
                            SearchProductMoreActivity.start(mContext);
                        } else if (position == 1) {
                            EventBus.getDefault().post(new HomeTableChangeEvent().setEventType(HomeTableChangeEvent.TO_SPECIAL_INFORMATION));
                        } else if (position == 2) {
                            SearchBusinessMoreActivity.start(mContext);
                        } else {
                            EventBus.getDefault().post(new HomeTableChangeEvent().setEventType(HomeTableChangeEvent.TO_INDUSTRY_RESOURCES));
                        }
                    }
                });

            }
        });
    }


}
