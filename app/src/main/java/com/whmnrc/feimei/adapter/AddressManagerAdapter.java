package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.beans.AddressBean;
import com.whmnrc.feimei.ui.mine.AddAddressActivity;

/**
 * @author yjyvi
 * @data 2018/5/19.
 */

public class AddressManagerAdapter extends CommonAdapter<AddressBean.ResultdataBean> {
    private boolean mIsSelect;

    private AddressListener mAddressListener;

    public void setAddressListener(AddressListener addressListener) {
        mAddressListener = addressListener;
    }

    public AddressManagerAdapter(Context context, int layoutId, boolean isSelect) {
        super(context, layoutId);
        this.mIsSelect = isSelect;
    }

    @Override
    public void convert(ViewHolder holder, final AddressBean.ResultdataBean resultdataBean, final int position) {

        holder.setText(R.id.tv_name, resultdataBean.getName());
        holder.setText(R.id.tv_phone_number, resultdataBean.getMobile());
        holder.setText(R.id.tv_address, resultdataBean.getProvice() + resultdataBean.getCity() + resultdataBean.getRegion() + resultdataBean.getDetail());

        holder.setOnClickListener(R.id.tv_edit, v -> AddAddressActivity.start(v.getContext(), JSON.toJSONString(resultdataBean), 1));

        holder.setOnClickListener(R.id.tv_delete, v -> {
            if (mAddressListener != null) {
                mAddressListener.delAddress(position);
            }
        });
        holder.setOnClickListener(R.id.ll_is_default, v -> {
            if (mAddressListener != null) {
                mAddressListener.setAddressDefault(position, v);
            }
        });


        if (resultdataBean.getIsDefault() == 1) {
            holder.setSelected(R.id.ll_is_default, true);
            holder.setText(R.id.tv_default,"默认地址");
        } else {
            holder.setText(R.id.tv_default,"设为默认");
            holder.setSelected(R.id.ll_is_default, false);
        }

    }

    public interface AddressListener {

        void delAddress(int position);

        void setAddressDefault(int position, View view);
    }
}
