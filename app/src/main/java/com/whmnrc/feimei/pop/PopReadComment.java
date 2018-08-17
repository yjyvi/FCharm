package com.whmnrc.feimei.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.AllCommentAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.beans.CommentListBean;
import com.whmnrc.feimei.beans.ProductTypeBean;
import com.whmnrc.feimei.presener.GetCommentPresenter;
import com.whmnrc.feimei.ui.mine.CommentActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by guox on 2017/6/2 0002.
 * 行业资源弹窗
 */

public class PopReadComment implements GetCommentPresenter.GetCommentListener {

    private String mReadId;
    private View showView;
    private PopupWindow mPopupWindow;
    private Context mContext;

    private PopHintListener mPopHintListener;

    private List<ProductTypeBean.ResultdataBean> mDataList = new ArrayList<>();
    public GetCommentPresenter mGetCommentPresenter;
    public AllCommentAdapter mAllCommentAdapter;
    public SmartRefreshLayout mRefreshLayout;


    public PopupWindow getPopupWindow() {
        return mPopupWindow;
    }

    public void setPopHintListener(PopHintListener popHintListener) {
        mPopHintListener = popHintListener;
    }


    public PopReadComment(Context context, String readId) {
        this.mContext = context;
        this.mReadId = readId;

        mGetCommentPresenter = new GetCommentPresenter(this);

        View view = LayoutInflater.from(context).inflate(R.layout.pop_read_comment, null);
        RecyclerView mRvType = view.findViewById(R.id.rv_list);
        mRefreshLayout = view.findViewById(R.id.refresh);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.setEnableLoadMore(true);
                mGetCommentPresenter.getComment(mReadId, true);
                refreshLayout.finishRefresh();
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mGetCommentPresenter.getComment(mReadId, false);
                refreshLayout.finishLoadMore();
            }
        });


        mRvType.setLayoutManager(new LinearLayoutManager(mContext));
        mRvType.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = mContext.getResources().getDimensionPixelOffset(R.dimen.dm_0_5);
            }
        });
        mRvType.setNestedScrollingEnabled(false);
        mAllCommentAdapter = new AllCommentAdapter(context, R.layout.item_organization_comment);
        mRvType.setAdapter(mAllCommentAdapter);
        mAllCommentAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                mPopHintListener.confirm(mDataList.get(position));
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        // 设置popwindow弹出大小
        mPopupWindow = new PopupWindow(context);

        mPopupWindow.setClippingEnabled(false);

        mPopupWindow.setContentView(view);
        mPopupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 设置允许在外点击消失
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);

        view.findViewById(R.id.tv_send_comment).setOnClickListener(v -> {
            CommentActivity.start(mContext, mReadId);
            CommentActivity.setCommentListener(() -> mGetCommentPresenter.getComment(mReadId, true));
        });
    }

    public void show() {
        // 设置弹出位置
        mGetCommentPresenter.getComment(mReadId, true);
    }

    @Override
    public void getCommentListSuccess(CommentListBean.ResultdataBean beans, boolean isRefresh) {
        if (isRefresh) {
            mAllCommentAdapter.setDataArray(beans.getComment());
        } else {
            List<CommentListBean.ResultdataBean.CommentBean> datas = mAllCommentAdapter.getDatas();
            datas.addAll(beans.getComment());

            if (beans.getPagination().getRecords() == datas.size()) {
                mRefreshLayout.setEnableLoadMore(false);
            }

            mAllCommentAdapter.setDataArray(datas);
        }
        mAllCommentAdapter.notifyDataSetChanged();

        if (mPopupWindow != null) {
            PopUtils.setBackgroundAlpha((Activity) mContext, 0.5f);

            mPopupWindow.showAtLocation(((Activity) mContext).getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
            // 刷新状态
            mPopupWindow.update();

            mPopupWindow.setOnDismissListener(() -> PopUtils.setBackgroundAlpha((Activity) mContext, 1f));

        }



    }

    @Override
    public void getCommentListField() {

    }


    public interface PopHintListener {
        void confirm(ProductTypeBean.ResultdataBean bean);
    }

}
