package com.whmnrc.feimei.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.AddressManagerAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.beans.AddressBean;
import com.whmnrc.feimei.presener.AddressEditPresenter;
import com.whmnrc.feimei.presener.AddressListPresenter;
import com.whmnrc.feimei.presener.DelAddressPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.utils.EmptyListUtils;
import com.whmnrc.feimei.utils.evntBusBean.AddressEvent;
import com.whmnrc.feimei.views.AlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/5/19.
 */

public class AddressManagerActivity extends BaseActivity implements AddressListPresenter.AddressListListener, OnRefreshListener, DelAddressPresenter.DelAddressListener, AddressEditPresenter.AddressEditListener {

    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.rv_address_list)
    RecyclerView mRvAddressList;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.tv_commit)
    TextView mTvCommit;
    public AddressManagerAdapter mAddressManagerAdapter;
    public AddressListPresenter mAddressListPresenter;
    public boolean mIsSelect;
    private String mAddressId = "";
    public DelAddressPresenter mDelAddressPresenter;
    public AddressEditPresenter mAddressEditPresenter;


    @Override
    protected void initViewData() {
        EventBus.getDefault().register(this);
        mIsSelect = getIntent().getBooleanExtra("isSelect", false);
        mAddressId = getIntent().getStringExtra("addressId");
        if (mIsSelect) {
            setTitle("选择收货地址");
        } else {
            setTitle("收货地址管理");
        }
        mAddressListPresenter = new AddressListPresenter(this);
        mAddressListPresenter.getAddressList();

        mDelAddressPresenter = new DelAddressPresenter(this);

        mAddressEditPresenter = new AddressEditPresenter(this);

        mRvAddressList.setLayoutManager(new LinearLayoutManager(this));
        mRvAddressList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = getResources().getDimensionPixelOffset(R.dimen.dm_10);
            }
        });
        mAddressManagerAdapter = new AddressManagerAdapter(this, R.layout.item_address, mIsSelect);
        mRvAddressList.setAdapter(mAddressManagerAdapter);

        mAddressManagerAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (mIsSelect) {
                    EventBus.getDefault().post(new AddressEvent().setEventType(AddressEvent.ORDER_SELECT_ADDRESS).setData(mAddressManagerAdapter.getDatas().get(position)));
                    finish();
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, final int position) {

                return true;
            }
        });

        mAddressManagerAdapter.setAddressListener(new AddressManagerAdapter.AddressListener() {
            @Override
            public void delAddress(int position) {
                new AlertDialog(AddressManagerActivity.this).builder().setTitle("提示!")
                        .setMsg("确认要删除地址?")
                        .setCancelable(true)
                        .setPositiveButton("确定", view -> {
                            mDelAddressPresenter.delAddress(mAddressManagerAdapter.getDatas().get(position).getID());
                            mAddressManagerAdapter.getDatas().remove(position);
                            mAddressManagerAdapter.notifyDataSetChanged();
                        })
                        .setNegativeButton("取消", view -> {

                        }).show();
            }

            @Override
            public void setAddressDefault(int position, View view) {
                AddressBean.ResultdataBean bean = mAddressManagerAdapter.getDatas().get(position);
                mAddressEditPresenter.editAddress(bean.getID(), bean.getName(), bean.getProvice(), bean.getCity(), bean.getRegion(), bean.getDetail(), "1", bean.getMobile());
            }
        });
        mRefresh.setEnableLoadMore(false);
        mRefresh.setOnRefreshListener(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_address_manager;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AddressManagerActivity.class);
        context.startActivity(starter);
    }

    public static void start(Context context, boolean isSelect) {
        Intent starter = new Intent(context, AddressManagerActivity.class);
        starter.putExtra("isSelect", isSelect);
        context.startActivity(starter);
    }

    public static void start(Context context, boolean isSelect, String addressId) {
        Intent starter = new Intent(context, AddressManagerActivity.class);
        starter.putExtra("isSelect", isSelect);
        starter.putExtra("addressId", addressId);
        context.startActivity(starter);
    }


    @OnClick(R.id.tv_commit)
    public void onClick() {
        AddAddressActivity.start(this, "", mAddressManagerAdapter.getDatas().size());
    }

    @Override
    public void getListSuccess(List<AddressBean.ResultdataBean> resultdataBeans) {
        mAddressManagerAdapter.setDataArray(resultdataBeans);
        mAddressManagerAdapter.notifyDataSetChanged();
        showEmpty();
    }

    @Override
    public void getAddressListField() {

    }


    public void showEmpty() {
        if (mAddressManagerAdapter != null && mAddressManagerAdapter.getDatas().size() == 0) {

            EmptyListUtils.loadEmpty(true, "没有地址", R.mipmap.no_address, mVsEmpty);
        } else {
            if (mVsEmpty != null) {
                mVsEmpty.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe
    public void addressEvent(AddressEvent addressEvent) {
        if (addressEvent.getEventType() == AddressEvent.ADD_ADDRESS_SUCCESS) {
            mAddressListPresenter.getAddressList();
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        mAddressListPresenter.getAddressList();
        refreshLayout.finishRefresh();
    }

    @Override
    public void delAdressSuccess() {
        mAddressListPresenter.getAddressList();
        EventBus.getDefault().post(new AddressEvent().setEventType(AddressEvent.ORDER_SELECT_ADDRESS).setData(null));

    }

    @Override
    public void delAddressField() {

    }

    @Override
    public void addSuccess() {
        mAddressListPresenter.getAddressList();
    }

    @Override
    public void addField() {

    }
}
