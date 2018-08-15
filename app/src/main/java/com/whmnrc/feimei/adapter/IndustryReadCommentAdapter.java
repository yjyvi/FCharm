package com.whmnrc.feimei.adapter;

import android.content.Context;

import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.ViewHolder;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class IndustryReadCommentAdapter extends CommonAdapter {
    public IndustryReadCommentAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Object o, int position) {
//        GlideUtils.LoadImage(mContext, o.getUserHeadImg(), (ImageView) holder.getView(R.id.iv_user_img));
//        if (!TextUtils.isEmpty(o.getCreateTime())) {
//            holder.setText(R.id.tv_comment_time, TimeUtils.getDateToString(Long.parseLong(o.getCreateTime())));
//        }
//        holder.setText(R.id.tv_user_name, o.getUserName());
//        holder.setText(R.id.tv_comment_content, o.getConten());


//        RecyclerView rvGoodsCommentImg = holder.getView(R.id.rv_goods_comment_img);
//        rvGoodsCommentImg.setLayoutManager(new GridLayoutManager(mContext, 3));
//        OrganizationCommentImgsAdapter organizationCommentImgsAdapter = new OrganizationCommentImgsAdapter(mContext, R.layout.item_organization_comment_img);
//        organizationCommentImgsAdapter.setDataArray(o.getCommentAdd());
//        rvGoodsCommentImg.setAdapter(organizationCommentImgsAdapter);
    }

}
