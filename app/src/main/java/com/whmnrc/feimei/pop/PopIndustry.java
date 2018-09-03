package com.whmnrc.feimei.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.IndustryBean;
import com.whmnrc.feimei.presener.GetIndustryPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yong hao zeng on 2018/3/10.
 * 行业选择
 */

public class PopIndustry {
    RecyclerView oneRv;
    RecyclerView twoRv;
    private Activity activity;
    public PopupWindow mPopupWindow;
    private CityListener cityListener;
    private int oneSelect = 0;
    private int twoSelect = -1;
    private List<IndustryBean.ResultdataBean.SubsetBean> twoList;
    private Adapter oneAdapter;
    private TwoAdapter twoAdapter;
    private View showView;
    public GetIndustryPresenter mGetIndustryPresenter;
    private List<IndustryBean.ResultdataBean> oneList;

    public PopupWindow getmPopupWindow() {
        return mPopupWindow;
    }

    public void setmPopupWindow(PopupWindow mPopupWindow) {
        this.mPopupWindow = mPopupWindow;
    }


    public PopIndustry(Activity activity, CityListener cityListener, View view) {
        this.activity = activity;
        this.cityListener = cityListener;
        this.showView = view;
        initPayTypePop();
        initData();
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

    private void initData() {
        oneList = cityListener.onLoad();
        oneAdapter.setDataArray(oneList);
        oneAdapter.notifyDataSetChanged();

    }


    public void setTwoList(List<IndustryBean.ResultdataBean.SubsetBean> towData) {
        twoList = towData;
        twoAdapter.setDataArray(twoList);
        twoAdapter.notifyDataSetChanged();
    }


    public boolean isShow() {
        return mPopupWindow.isShowing();
    }

    private void initPayTypePop() {
        mPopupWindow = new PopupWindow(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.industry_select_pop, null);
        oneRv = view.findViewById(R.id.one_rv);
        twoRv = view.findViewById(R.id.two_rv);
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
        twoRv.setLayoutManager(new LinearLayoutManager(activity));
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
        twoRv.addItemDecoration(new RecyclerView.ItemDecoration() {
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
        twoAdapter = new TwoAdapter(activity, R.layout.item_city_select);
        twoRv.setAdapter(twoAdapter);
        oneAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                oneSelect = position;
                cityListener.onSelect(position, 0);
                oneAdapter.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        twoAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                cityListener.onSelect(position, 1);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });


    }


    //可见列表项的数量
    private int visibleCount = 0;
    //上次点击的位置
    private int lastPosition = 0;
    private int ce = 0;
    //实际列表是否超出屏幕
    private boolean isOut = true;
    private View lastLineView;

    /**
     * 将选择的目录滑动到中间
     *
     * @param position
     * @param view
     */
    public void scroll(int position, CommonAdapter adapter1, View view) {
        if (adapter1.getDatas() == null) {
            return;
        }
        if (view == null) {
            return;
        }

        //改变选中状态
        if (!view.isSelected()) {
            //去除上一次控件的状态
            if (lastLineView != null) {
                lastLineView.setSelected(false);
            }
            lastLineView = view;

            view.setSelected(true);
        }


        if (visibleCount == 0) {
            visibleCount = oneRv.getChildCount();
            if (visibleCount == adapter1.getDatas().size()) {
                isOut = false;
            } else {
                ce = visibleCount / 2;
            }
        }

        RecyclerView.LayoutManager layoutManager = oneRv.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
            //上移
            if (position <= (linearManager.findFirstVisibleItemPosition() + ce)) {
                oneRv.smoothScrollToPosition(position - ce);
            } else {
                //下移
                if ((linearManager.findLastVisibleItemPosition() + ce + 1) <= adapter1.getItemCount()) {
                    oneRv.smoothScrollToPosition(position + ce);
                } else {
                    oneRv.smoothScrollToPosition(adapter1.getItemCount() - 1);
                }
            }
            lastPosition = position;
        }

    }


    public void show(View view) {
        mPopupWindow.showAsDropDown(view);

//        if (cityListener == null) {
//            return;
//        }
//        //默认选择第一个
//        if (twoList == null || twoList.size() == 0) {
//            cityListener.onSelect(0, 0);
//        }
    }

    public void dissmiss() {
        mPopupWindow.dismiss();
    }


    public interface CityListener {
        ArrayList<IndustryBean.ResultdataBean> onLoad();

        void onSelect(int position, int type);

        void onLoad(String name);
    }

    class Adapter extends CommonAdapter<IndustryBean.ResultdataBean> {

        public Adapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, IndustryBean.ResultdataBean s, int position) {
            holder.setIsRecyclable(false);
            if (position == oneSelect) {
                holder.setBackgroundColor(R.id.tv, Color.parseColor("#EBEBEB"));
            } else {
                holder.setBackgroundColor(R.id.tv, Color.parseColor("#FFFFFF"));
            }
            holder.setText(R.id.tv, s.getName());
        }
    }

    class TwoAdapter extends CommonAdapter<IndustryBean.ResultdataBean.SubsetBean> {

        public TwoAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, IndustryBean.ResultdataBean.SubsetBean s, int position) {
            if (position == twoSelect) {
                holder.setBackgroundColor(R.id.tv, Color.parseColor("#EBEBEB"));
            } else {
                holder.setBackgroundColor(R.id.tv, Color.parseColor("#EEEEEE"));
            }
            holder.setText(R.id.tv, s.getName());
        }
    }
}
