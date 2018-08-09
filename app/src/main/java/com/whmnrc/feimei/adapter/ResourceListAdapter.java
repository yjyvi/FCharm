package com.whmnrc.feimei.adapter;

import android.content.Context;
import android.view.View;

import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;
import com.whmnrc.feimei.ui.industry.IndustryDetailsActivity;
import com.whmnrc.feimei.ui.mine.PayActivity;

import java.util.Random;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class ResourceListAdapter extends CommonAdapter {
    public ResourceListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Object o, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type = new Random().nextInt(2) + 1;
                if (type == 1) {
                    PayActivity.start(v.getContext(), PayActivity.RESOURCE_PAY);
                } else {
                    IndustryDetailsActivity.start(v.getContext());
                }


            }
        });
    }


}
