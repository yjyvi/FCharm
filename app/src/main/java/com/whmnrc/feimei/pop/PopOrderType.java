package com.whmnrc.feimei.pop;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by guox on 2017/6/2 0002.
 * 订单类型弹窗
 */

public class PopOrderType {

    private View showView;
    private PopupWindow mPopupWindow;
    private Context mContext;

    private PopHintListener mPopHintListener;

    private List<OrderTypeBean> mDataList = new ArrayList<>();
    public Adapter mAdapter;

    public class OrderTypeBean {
        private String typeName;
        private int type;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public OrderTypeBean(String typeName, int type) {
            this.typeName = typeName;
            this.type = type;
        }

        public OrderTypeBean() {
        }
    }


    public PopupWindow getPopupWindow() {
        return mPopupWindow;
    }

    public void setPopHintListener(PopHintListener popHintListener) {
        mPopHintListener = popHintListener;
    }


    public PopOrderType(Context context, View showView) {
        this.mContext = context;
        this.showView = showView;

        mDataList.add(new OrderTypeBean("全部", -1));
        mDataList.add(new OrderTypeBean("产品", 0));
        mDataList.add(new OrderTypeBean("企业", 3));
        mDataList.add(new OrderTypeBean("专栏", 4));
        mDataList.add(new OrderTypeBean("文库", 5));

        View view = LayoutInflater.from(context).inflate(R.layout.pop_information, null);
        RecyclerView mRvType = view.findViewById(R.id.rv_list);

        mRvType.setLayoutManager(new LinearLayoutManager(mContext));
        mRvType.setNestedScrollingEnabled(false);
        mAdapter = new Adapter(context, R.layout.item_city_select);
        mRvType.setAdapter(mAdapter);
        mAdapter.setDataArray(mDataList);
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                mPopHintListener.confirm(mDataList.get(position));
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        // 设置popwindow弹出大小
        mPopupWindow = new PopupWindow(context);

        mPopupWindow.setClippingEnabled(false);

        mPopupWindow.setContentView(view);
        mPopupWindow.setWidth(showView.getWidth());
        mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 设置允许在外点击消失
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);

    }

    public void show() {
        // 设置弹出位置
        mPopupWindow.showAsDropDown(showView);
    }


    public interface PopHintListener {
        void confirm(OrderTypeBean bean);
    }


    class Adapter extends CommonAdapter<OrderTypeBean> {

        public Adapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, OrderTypeBean s, int position) {
            holder.setText(R.id.tv, s.getTypeName());
        }
    }
}
