package com.whmnrc.feimei.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

/**
 * @author yjyvi
 * @data 2018/6/14.
 */

public class MyClassicsFooter extends ClassicsFooter {

    public static String REFRESH_FOOTER_PULLUP = "Pull up to load more";
    public static String REFRESH_FOOTER_RELEASE = "Release immediate loading";
    public static String REFRESH_FOOTER_LOADING = "loading...";
    public static String REFRESH_FOOTER_REFRESHING = "Refreshing...";
    public static String REFRESH_FOOTER_FINISH = "Loading completed";
    public static String REFRESH_FOOTER_FAILED = "Failed to load";
    public static String REFRESH_FOOTER_ALLLOADED = "No more data";

    public MyClassicsFooter(Context context) {
        super(context,null);
    }

    public MyClassicsFooter(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public MyClassicsFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置数据全部加载完成，将不能再次触发加载功能
     */
    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        if (mNoMoreData != noMoreData) {
            mNoMoreData = noMoreData;
            if (noMoreData) {
                mTitleText.setText(REFRESH_FOOTER_ALLLOADED);
                mArrowView.setVisibility(GONE);
            } else {
                mTitleText.setText(REFRESH_FOOTER_PULLUP);
                mArrowView.setVisibility(VISIBLE);
            }
            if (mProgressDrawable != null) {
                mProgressDrawable.stop();
            } else {
                mProgressView.animate().rotation(0).setDuration(300);
            }
            mProgressView.setVisibility(GONE);
        }
        return true;
    }

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        if (!mNoMoreData) {
            if (mProgressDrawable != null) {
                mProgressDrawable.stop();
            } else {
                mProgressView.animate().rotation(0).setDuration(300);
            }
            mProgressView.setVisibility(GONE);
            if (success) {
                mTitleText.setText(REFRESH_FOOTER_FINISH);
            } else {
                mTitleText.setText(REFRESH_FOOTER_FAILED);
            }
            return mFinishDuration;
        }
        return 0;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        if (!mNoMoreData) {
            switch (newState) {
                case None:
//                    restoreRefreshLayoutBackground();
                    mArrowView.setVisibility(VISIBLE);
                case PullUpToLoad:
                    mTitleText.setText(REFRESH_FOOTER_PULLUP);
                    mArrowView.animate().rotation(180);
                    break;
                case Loading:
                case LoadReleased:
                    mArrowView.setVisibility(GONE);
                    mTitleText.setText(REFRESH_FOOTER_LOADING);
                    break;
                case ReleaseToLoad:
                    mTitleText.setText(REFRESH_FOOTER_RELEASE);
                    mArrowView.animate().rotation(0);
//                    replaceRefreshLayoutBackground(refreshLayout);
                    break;
                case Refreshing:
                    mTitleText.setText(REFRESH_FOOTER_REFRESHING);
                    mProgressView.setVisibility(GONE);
                    mArrowView.setVisibility(GONE);
                    break;
            }
        }
    }
}
