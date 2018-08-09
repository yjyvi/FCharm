package com.whmnrc.feimei.adapter;

import android.content.Context;

import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;

/**
 * @author yjyvi
 * @data 2018/5/19.
 */

public class AddressManagerAdapter extends CommonAdapter {
    private boolean mIsSelect;

    public AddressManagerAdapter(Context context, int layoutId, boolean isSelect) {
        super(context, layoutId);
        this.mIsSelect = isSelect;
    }

    @Override
    public void convert(ViewHolder holder, final Object resultdataBean, int position) {
//        holder.setText(R.id.tv_address_name, String.format("Receiver：%s %s", resultdataBean.getShipTo(), resultdataBean.getAddress_LastName()));
//        holder.setText(R.id.tv_address_tel, resultdataBean.getPhone());
//        holder.setText(R.id.tv_address_desc, resultdataBean.getAddress());
//        holder.setOnClickListener(R.id.iv_address_edit, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AddAddressActivity.start(v.getContext(), JSON.toJSONString(resultdataBean), 1);
//            }
//        });
//
//
//        if (resultdataBean.getAddress_IsDefault() == 1) {
//            holder.setVisible(R.id.v_isDefault, true);
//        } else {
//            holder.setVisible(R.id.v_isDefault, false);
//        }
//
//        if (mIsSelect) {
//            holder.setVisible(R.id.iv_selected, true);
//            holder.setSelected(R.id.iv_selected, resultdataBean.isSelect());
//        } else {
//            holder.setVisible(R.id.iv_selected, false);
//        }
    }
}
