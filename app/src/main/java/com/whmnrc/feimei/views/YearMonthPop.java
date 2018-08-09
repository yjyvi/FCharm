package com.whmnrc.feimei.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;

/**
 * Created by yong hao zeng on 2018/3/10.
 */

public class YearMonthPop {
    private boolean mIsCountry;
    RecyclerView oneRv;
    private Activity activity;
    public PopupWindow mPopupWindow;
    private CityListener cityListener;
    List<String> oneList = new ArrayList<>();
    private int oneSelect = 0;
    private int twoSelect = -1;
    private List<String> twoList;
    private Unbinder bind;
    private Adapter oneAdapter;
    private View showView;

    public PopupWindow getmPopupWindow() {
        return mPopupWindow;
    }

    public void setmPopupWindow(PopupWindow mPopupWindow) {
        this.mPopupWindow = mPopupWindow;
    }

    private WindowManager.LayoutParams wlBackground;


    public YearMonthPop(Activity activity, CityListener cityListener, View view) {
        this.activity = activity;
        this.cityListener = cityListener;
        this.showView = view;

        initPayTypePop();
    }

    public void setOneList(List<String> oneList) {
        this.oneList = oneList;
    }

    public boolean isShow() {
        return mPopupWindow.isShowing();
    }

    private void initPayTypePop() {
        mPopupWindow = new PopupWindow(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.city_select_pop, null);
        oneRv = view.findViewById(R.id.one_rv);
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
        oneAdapter = new Adapter(activity, R.layout.item_city_select);
        oneRv.setAdapter(oneAdapter);
        oneAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                oneSelect = position;
                cityListener.onSelect(oneAdapter.getDatas().get(position));
                oneAdapter.notifyDataSetChanged();
                mPopupWindow.dismiss();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }


    public void show(boolean isCountry) {
        this.mIsCountry = isCountry;
        oneAdapter.setDataArray(oneList);
        oneAdapter.notifyDataSetChanged();
        mPopupWindow.showAtLocation(showView, Gravity.BOTTOM, 0, 0);
    }

    public void dissmiss() {
        mPopupWindow.dismiss();
    }


    public interface CityListener {
        void onSelect(String result);
    }

    class Adapter extends CommonAdapter<String> {
        public Adapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, String s, int position) {
            holder.setText(R.id.tv, s);
        }
    }
}
