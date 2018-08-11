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

    private  DelAddressListener mDelAddressListener;

    public void setDelAddressListener(DelAddressListener delAddressListener) {
        mDelAddressListener = delAddressListener;
    }

    public AddressManagerAdapter(Context context, int layoutId, boolean isSelect) {
        super(context, layoutId);
        this.mIsSelect = isSelect;
    }

    @Override
    public void convert(ViewHolder holder, final Object resultdataBean, final int position) {
//        holder.setText(R.id.tv_address_name, String.format("Receiver：%s %s", resultdataBean.getShipTo(), resultdataBean.getAddress_LastName()));
//        holder.setText(R.id.tv_address_tel, resultdataBean.getPhone());
//        holder.setText(R.id.tv_address_desc, resultdataBean.getAddress());
        holder.setOnClickListener(R.id.tv_edit, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAddressActivity.start(v.getContext(), JSON.toJSONString(resultdataBean), 1);
            }
        });

        holder.setOnClickListener(R.id.tv_delete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDelAddressListener != null) {
                    mDelAddressListener.delAddress(position);
                }
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

    public interface DelAddressListener{
        void delAddress(int position);
    }
}
