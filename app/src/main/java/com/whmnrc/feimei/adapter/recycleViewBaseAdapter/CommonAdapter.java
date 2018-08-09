package com.whmnrc.feimei.adapter.recycleViewBaseAdapter;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.List;


/**
 * recycleView通用adapter  注意避免重写bindviewholder
 * Created by yonghao zeng on 16/4/9.
 */
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T>

{
    protected Context mContext;
    protected int mLayoutId;
    protected LayoutInflater mInflater;

    public CommonAdapter(final Context context, final int layoutId) {
        super(context);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;


        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }



    @Override
    public void setDataArray(List<T> mData) {
        super.setDataArray(mData);
    }

    /**
     * 重写次方法 对viewholder中的view进行数据填充
     *
     * @param holder
     * @param t
     * @param position
     */
    public abstract void convert(ViewHolder holder, T t, int position);




}
