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
 * 光通资迅选择分类弹窗
 */

public class PopInformation {

    private View showView;
    private PopupWindow mPopupWindow;
    private Context mContext;

    private PopHintListener mPopHintListener;

    private List<InformationTypeBean> mDataList = new ArrayList<>();
    public Adapter mAdapter;

    public class InformationTypeBean {
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

        public InformationTypeBean(String typeName, int type) {
            this.typeName = typeName;
            this.type = type;
        }

        public InformationTypeBean() {
        }
    }


    public PopupWindow getPopupWindow() {
        return mPopupWindow;
    }

    public void setPopHintListener(PopHintListener popHintListener) {
        mPopHintListener = popHintListener;
    }


    public PopInformation(Context context, View showView) {
        this.mContext = context;
        this.showView = showView;

        mDataList.add(new InformationTypeBean("全部", -1));
        mDataList.add(new InformationTypeBean("技术", 0));
        mDataList.add(new InformationTypeBean("市场", 1));
        mDataList.add(new InformationTypeBean("其它", 2));

        View view = LayoutInflater.from(context).inflate(R.layout.pop_information, null);
        RecyclerView mRvType = view.findViewById(R.id.rv_list);

        mRvType.setLayoutManager(new LinearLayoutManager(mContext));
//        mRvType.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
//                outRect.bottom = mContext.getResources().getDimensionPixelOffset(R.dimen.dm_0_5);
//            }
//        });
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
        void confirm(InformationTypeBean bean);
    }


    class Adapter extends CommonAdapter<InformationTypeBean> {

        public Adapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, InformationTypeBean s, int position) {
            holder.setText(R.id.tv, s.getTypeName());
        }
    }
}
