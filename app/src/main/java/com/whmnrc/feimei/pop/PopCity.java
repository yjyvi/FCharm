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
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.JsonBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yong hao zeng on 2018/3/10.
 */

public class PopCity {
    RecyclerView oneRv;
    RecyclerView twoRv;
    private Activity activity;
    public PopupWindow mPopupWindow;
    private CityListener cityListener;
    ArrayList<JsonBean> oneList = new ArrayList<>();
    private int oneSelect = 0;
    private int twoSelect = -1;
    private List<JsonBean.CityBean> twoList;
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


    public PopCity(Activity activity, CityListener cityListener, View view) {
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


    public boolean isShow() {
        return mPopupWindow.isShowing();
    }

    private void initPayTypePop() {
        mPopupWindow = new PopupWindow(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.city_select_pop, null);
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
//                scroll(position, oneAdapter, view);
//                if (oneSelect != 0)
                cityListener.onSelect(position, 0);
                oneAdapter.notifyDataSetChanged();

                scroll(position, oneAdapter, view);
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


        if (visibleCount == 0) {
            visibleCount = oneRv.getChildCount();
            if (visibleCount == adapter1.getDatas().size()) {
                isOut = false;
            } else {
                ce = (visibleCount / 2) - 1;
            }
        }

        RecyclerView.LayoutManager layoutManager = oneRv.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;

            //上移
            int firstVisibleItemPosition = linearManager.findFirstVisibleItemPosition();
            if (position <= (firstVisibleItemPosition + ce+1)) {
                int position1 = position - ce;
                if (position1 < 0) {
                    position1 = 0;
                }
                Log.e("PopCity", "上移position1:" + position1);
                oneRv.smoothScrollToPosition(position1);
            } else {
                //下移
//                int lastVisibleItemPosition = linearManager.findLastVisibleItemPosition();
//                if ((firstVisibleItemPosition + ce + 1) <= adapter1.getItemCount()) {
                Log.e("PopCity", "下移:position1:" + (position + ce));
                oneRv.smoothScrollToPosition( (position + ce) );
//                } else {
//                    oneRv.smoothScrollToPosition(adapter1.getItemCount() - 1);
//                }
            }
        }

    }


    public void show(View view) {
        mPopupWindow.showAsDropDown(view);
    }

    public void dissmiss() {
        mPopupWindow.dismiss();
    }

    public void setTwoList(List<JsonBean.CityBean> towData) {
        twoList = towData;
        twoAdapter.setDataArray(twoList);
        twoAdapter.notifyDataSetChanged();
    }


    public interface CityListener {
        ArrayList<JsonBean> onLoad();

        void onSelect(int position, int type);

        void onLoad(String name);

    }

    class Adapter extends CommonAdapter<JsonBean> {

        public Adapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, JsonBean s, int position) {
            holder.setIsRecyclable(false);
            if (position == oneSelect) {
                holder.setBackgroundColor(R.id.tv, Color.parseColor("#EBEBEB"));
            } else {
                holder.setBackgroundColor(R.id.tv, Color.parseColor("#FFFFFF"));
            }
            holder.setText(R.id.tv, s.getName());
        }
    }

    class TwoAdapter extends CommonAdapter<JsonBean.CityBean> {

        public TwoAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, JsonBean.CityBean s, int position) {
            if (position == twoSelect) {
                holder.setBackgroundColor(R.id.tv, Color.parseColor("#EBEBEB"));
            } else {
                holder.setBackgroundColor(R.id.tv, Color.parseColor("#EEEEEE"));
            }
            holder.setText(R.id.tv, s.getName());
        }
    }
}
