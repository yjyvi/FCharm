package com.whmnrc.feimei.views;

import android.content.Context;
import android.util.AttributeSet;

import com.whmnrc.feimei.R;

import cn.jzvd.JZVideoPlayerStandard;


/**
 * Created by yong hao zeng on 2018/2/7.
 */

public class MyVideoPlayer extends JZVideoPlayerStandard {
    @Override
    public int getLayoutId() {
        return R.layout.my_video;
    }

    public MyVideoPlayer(Context context) {
        super(context);
    }

    public MyVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
