package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.ui.mine.AddAddressActivity;

/**
 * @author yjyvi
 * @data 2018/5/19.
 */

public class AddressManagerAdapter extends CommonAdapter {
    private boolean mIsSelect;

    private  AddressListener mAddressListener;

    public void setAddressListener(AddressListener addressListener) {
        mAddressListener = addressListener;
    }

    public AddressManagerAdapter(Context context, int layoutId, boolean isSelect) {
        super(context, layoutId);
        this.mIsSelect = isSelect;
    }

    @Override
    public void convert(ViewHolder holder, final Object resultdataBean, final int position) {
        holder.setOnClickListener(R.id.tv_edit, v -> AddAddressActivity.start(v.getContext(), JSON.toJSONString(resultdataBean), 1));

        holder.setOnClickListener(R.id.tv_delete, v -> {
            if (mAddressListener != null) {
                mAddressListener.delAddress(position);
            }
        });
        holder.setOnClickListener(R.id.ll_is_default, v -> {
            if (mAddressListener != null) {
                mAddressListener.setAddressDefault(position,v);
            }
        });
//
//
//        if (resultdataBean.getAddress_IsDefault() == 1) {
//            holder.setVisible(R.id.v_isDefault, true);
//        } else {
//            holder.setVisible(R.id.v_isDefault, false);
//        }
//
        if (mIsSelect) {
            holder.setVisible(R.id.ll_is_select, false);
        } else {
            holder.setVisible(R.id.ll_is_select, true);
        }
    }

    public interface AddressListener{

        void delAddress(int position);

        void setAddressDefault(int position,View view);
    }
}
