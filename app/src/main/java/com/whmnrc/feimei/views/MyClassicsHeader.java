package com.whmnrc.feimei.views;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author yjyvi
 * @data 2018/6/9.
 */

public class MyClassicsHeader extends ClassicsHeader {


    public static String REFRESH_HEADER_PULLDOWN = "Pull down to refresh";
    public static String REFRESH_HEADER_REFRESHING = "Refreshing...";
    public static String REFRESH_HEADER_LOADING = "loading...";
    public static String REFRESH_HEADER_RELEASE = "Release immediately refresh";
    public static String REFRESH_HEADER_FINISH = "Refresh completed";
    public static String REFRESH_HEADER_FAILED = "Refresh failed";
    public static String REFRESH_HEADER_SECOND_FLOOR = "Release into the second floor";
    public static String REFRESH_HEADER_LASTTIME = "'Last update' M-d HH:mm";
    protected String KEY_LAST_UPDATE_TIME = "LAST_UPDATE_TIME";


    protected DateFormat mFormat = new SimpleDateFormat(REFRESH_HEADER_LASTTIME, Locale.getDefault());

    public MyClassicsHeader(Context context) {
        super(context);
    }

    public MyClassicsHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyClassicsHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyClassicsHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case None:
                mLastUpdateText.setVisibility(mEnableLastTime ? VISIBLE : GONE);
            case PullDownToRefresh:
                mTitleText.setText(REFRESH_HEADER_PULLDOWN);
                mArrowView.setVisibility(VISIBLE);
                mProgressView.setVisibility(GONE);
                mArrowView.animate().rotation(0);
                break;
            case Refreshing:
            case RefreshReleased:
                mTitleText.setText(REFRESH_HEADER_REFRESHING);
                mProgressView.setVisibility(VISIBLE);
                mArrowView.setVisibility(GONE);
                break;
            case ReleaseToRefresh:
                mTitleText.setText(REFRESH_HEADER_RELEASE);
                mArrowView.animate().rotation(180);
                break;
            case ReleaseToTwoLevel:
                mTitleText.setText(REFRESH_HEADER_SECOND_FLOOR);
                mArrowView.animate().rotation(0);
                break;
            case Loading:
                mArrowView.setVisibility(GONE);
                mProgressView.setVisibility(GONE);
                mLastUpdateText.setVisibility(mEnableLastTime ? INVISIBLE : GONE);
                mTitleText.setText(REFRESH_HEADER_LOADING);
                break;
        }
    }

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        if (mProgressDrawable != null) {
            mProgressDrawable.stop();
        } else {
            Drawable drawable = mProgressView.getDrawable();
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).stop();
            } else {
                mProgressView.animate().rotation(0).setDuration(300);
            }
        }
        mProgressView.setVisibility(GONE);
        if (success) {
            mTitleText.setText(REFRESH_HEADER_FINISH);
            if (mLastTime != null) {
                setLastUpdateTime(new Date());
            }
        } else {
            mTitleText.setText(REFRESH_HEADER_FAILED);
        }
        return mFinishDuration;//延迟500毫秒之后再弹回
    }

    @Override
    public ClassicsHeader setLastUpdateTime(Date time) {
        mLastTime = time;
        if (mLastUpdateText != null && mFormat != null) {
            mLastUpdateText.setText(mFormat.format(time));
        }
        if (mShared != null && !isInEditMode()) {
            mShared.edit().putLong(KEY_LAST_UPDATE_TIME, time.getTime()).apply();
        }
        return this;
    }

    @Override
    public ClassicsHeader setTimeFormat(DateFormat format) {
        mFormat = format;
        if (mLastTime != null) {
            if (mLastUpdateText != null) {
                mLastUpdateText.setText(mFormat.format(mLastTime));
            }
        }
        return this;
    }


}
