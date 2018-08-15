package com.whmnrc.feimei.pop;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.IndustryReadCommentAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.beans.ProductTypeBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by guox on 2017/6/2 0002.
 * 行业资源弹窗
 */

public class PopReadComment {

    private View showView;
    private PopupWindow mPopupWindow;
    private Context mContext;

    private PopHintListener mPopHintListener;

    private List<ProductTypeBean.ResultdataBean> mDataList = new ArrayList<>();


    public PopupWindow getPopupWindow() {
        return mPopupWindow;
    }

    public void setPopHintListener(PopHintListener popHintListener) {
        mPopHintListener = popHintListener;
    }


    public PopReadComment(Context context, View showView) {
        this.showView = showView;
        this.mContext = context;


        View view = LayoutInflater.from(context).inflate(R.layout.pop_read_comment, null);
        RecyclerView mRvType = view.findViewById(R.id.rv_list);


        mRvType.setLayoutManager(new LinearLayoutManager(mContext));
        mRvType.setNestedScrollingEnabled(false);
        DividerItemDecoration divider = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.custom_divider));
        mRvType.addItemDecoration(divider);
        IndustryReadCommentAdapter productLibraryTypeAdapter = new IndustryReadCommentAdapter(context, R.layout.item_organization_comment);
        productLibraryTypeAdapter.setDataArray(mDataList);
        mRvType.setAdapter(productLibraryTypeAdapter);
        productLibraryTypeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
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
        mPopupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 设置允许在外点击消失
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);
        view.findViewById(R.id.view_bg).setOnClickListener(v -> mPopupWindow.dismiss());

    }

    public void show() {
        // 设置弹出位置
        mPopupWindow.showAsDropDown(showView);

    }


    public interface PopHintListener {
        void confirm(ProductTypeBean.ResultdataBean bean);
    }

}
