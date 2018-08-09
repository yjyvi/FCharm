package com.whmnrc.feimei.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.SalaryListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yong hao zeng on 2018/3/10.
 * 薪酬范围弹窗
 */

public class PopSalaryRange {

    RecyclerView twoRv;
    private Activity activity;
    public PopupWindow mPopupWindow;
    private SalaryRangeListener mSalaryRangeListener;
    private List<SalaryListBean.ResultdataBean> oneList = new ArrayList<>();
    private int oneSelect = 0;
    private TwoAdapter twoAdapter;
    private View showView;


    public PopSalaryRange(Activity activity, SalaryRangeListener salaryRangeListener, View view, List<SalaryListBean.ResultdataBean> oneList) {
        this.activity = activity;
        this.mSalaryRangeListener = salaryRangeListener;
        this.showView = view;
        this.oneList = oneList;
        initPayTypePop();
    }


    public PopupWindow getPopupWindow() {
        return mPopupWindow;
    }

    public boolean isShow() {
        return mPopupWindow.isShowing();
    }

    private void initPayTypePop() {
        mPopupWindow = new PopupWindow(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.more_salary_range_select_pop, null);
        twoRv = view.findViewById(R.id.two_rv);
        TextView tvReturn = view.findViewById(R.id.tv_return);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);
        view.findViewById(R.id.view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopupWindow.setClippingEnabled(false);

        mPopupWindow.setContentView(view);
        mPopupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 设置允许在外点击消失
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);

        twoRv.setLayoutManager(new GridLayoutManager(activity, 2));
        twoAdapter = new TwoAdapter(activity, R.layout.item_salary_range_select);
        twoRv.setAdapter(twoAdapter);
        twoAdapter.setDataArray(oneList);
        twoAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                selectedView(view);
                oneSelect = position;
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        tvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFalse(twoRv);
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oneSelect != -1) {
                    mSalaryRangeListener.onSelectSalaryRange(oneList.get(oneSelect).getID());
                } else {
                    mSalaryRangeListener.onSelectSalaryRange("");
                }
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        });
    }


    public void show() {
        mPopupWindow.showAsDropDown(showView);
    }

    public void dissmiss() {
        mPopupWindow.dismiss();
    }


    /**
     * 选择重置
     */
    private void selectedFalse(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int childCount = layoutManager.getChildCount();
        for (int i = 0; i < childCount; i++) {
            layoutManager.getChildAt(i).setSelected(false);
        }
        oneSelect = -1;
    }


    private View lastView;

    private void selectedView(View view) {
        if (lastView != null) {
            lastView.setSelected(false);
        }
        if (!view.isSelected()) {
            view.setSelected(true);
            lastView = view;
        } else {
            view.setSelected(false);
        }

    }


    public interface SalaryRangeListener {
        void onSelectSalaryRange(String contentId);
    }

//    class Adapter extends CommonAdapter<SalaryListBean.ResultdataBean> {
//
//        public Adapter(Context context, int layoutId) {
//            super(context, layoutId);
//        }
//
//        @Override
//        public void convert(ViewHolder holder, SalaryListBean.ResultdataBean s, int position) {
//            int getWidth = (holder.itemView.getContext().getResources().getDisplayMetrics().widthPixels - holder.itemView.getContext().getResources().getDimensionPixelOffset(R.dimen.dm_10)) / 2;
//            TextView view = holder.getView(R.id.tv);
//            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//            layoutParams.width = getWidth;
//            view.setLayoutParams(layoutParams);
//
//
//            if (position == oneSelect) {
//                holder.setBackgroundColor(R.id.tv, Color.parseColor("#EBEBEB"));
//            } else {
//                holder.setBackgroundColor(R.id.tv, Color.parseColor("#FFFFFF"));
//            }
//            holder.setText(R.id.tv, s.getName());
//        }
//    }

    class TwoAdapter extends CommonAdapter<SalaryListBean.ResultdataBean> {

        public TwoAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, SalaryListBean.ResultdataBean s, int position) {
//            if (position == twoSelect) {
//                holder.getView(R.id.tv).setSelected(true);
//            } else {
//                holder.getView(R.id.tv).setSelected(false);
//            }
            holder.setText(R.id.tv, s.getName());
        }
    }
}
