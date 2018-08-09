package com.whmnrc.feimei.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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

import butterknife.Unbinder;

/**
 * Created by yong hao zeng on 2018/3/10.
 * 更多筛选弹窗
 */

public class PopMoreFitter {
    RecyclerView oneRv;
    RecyclerView twoRv;
    private Activity activity;
    public PopupWindow mPopupWindow;
    private MoreFitterListener mMoreFitterListener;
    private List<String> oneList = new ArrayList<>();
    private int oneSelect = 0;
    private int twoSelect = -1;
    private int mTowPosition;
    private List<SalaryListBean.ResultdataBean> twoList;
    private Unbinder bind;
    private Adapter oneAdapter;
    private TwoAdapter twoAdapter;
    private View showView;

    public PopupWindow getmPopupWindow() {
        return mPopupWindow;
    }

    public void setmPopupWindow(PopupWindow mPopupWindow) {
        this.mPopupWindow = mPopupWindow;
    }

    private WindowManager.LayoutParams wlBackground;


    public PopMoreFitter(Activity activity, MoreFitterListener moreFitterListener, View view) {
        this.activity = activity;
        this.mMoreFitterListener = moreFitterListener;
        this.showView = view;
        initPayTypePop();
        initData();
    }

    private void initData() {
        oneList = mMoreFitterListener.onLoadFitterData();
        oneAdapter.setDataArray(oneList);
        oneAdapter.notifyDataSetChanged();
    }


    public boolean isShow() {
        return mPopupWindow.isShowing();
    }

    private void initPayTypePop() {
        mPopupWindow = new PopupWindow(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.more_fitter_select_pop, null);
        oneRv = view.findViewById(R.id.one_rv);
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
        oneRv.setLayoutManager(new LinearLayoutManager(activity));
        twoRv.setLayoutManager(new GridLayoutManager(activity, 2));
        oneRv.addItemDecoration(new RecyclerView.ItemDecoration() {

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
                c.drawColor(ContextCompat.getColor(parent.getContext(), R.color.common_line));
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = view.getContext().getResources().getDimensionPixelOffset(R.dimen.dm_0_5);
            }
        });
        oneAdapter = new Adapter(activity, R.layout.item_city_select);
        oneRv.setAdapter(oneAdapter);
        oneAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                oneSelect = position;
//                if (oneSelect != 0)
                mMoreFitterListener.onSelectFitter(0, oneSelect, 0);
                oneAdapter.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        twoAdapter = new TwoAdapter(activity, R.layout.item_more_fitter_select);
        twoRv.setAdapter(twoAdapter);
        twoAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {


            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                selectedView(view);
                mTowPosition = position;
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
                mMoreFitterListener.onSelectFitter(1, mTowPosition, 1);
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        });
    }


    public void show() {
        mPopupWindow.showAsDropDown(showView);
        if (mMoreFitterListener != null && twoList == null || twoList.size() == 0) {
            mMoreFitterListener.onSelectFitter(0, 0, 0);
        }
    }

    public void dissmiss() {
        mPopupWindow.dismiss();
    }

    public void setTwoList(List<SalaryListBean.ResultdataBean> towData) {
        twoList = towData;
        twoAdapter.setDataArray(twoList);
        twoAdapter.notifyDataSetChanged();
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
        mTowPosition = -1;
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


    public interface MoreFitterListener {
        ArrayList<String> onLoadFitterData();

        void onSelectFitter(int parentPosition, int position, int type);
    }

    class Adapter extends CommonAdapter<String> {

        public Adapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, String s, int position) {
            if (position == oneSelect) {
                holder.setBackgroundColor(R.id.tv, Color.parseColor("#EBEBEB"));
            } else {
                holder.setBackgroundColor(R.id.tv, Color.parseColor("#FFFFFF"));
            }
            holder.setText(R.id.tv, s);
        }
    }

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
