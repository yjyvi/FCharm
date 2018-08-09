package com.whmnrc.feimei.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.whmnrc.feimei.R;

/**
 * @author yjyvi
 * @data 2018/5/10.
 */

public class CodeTimeUtils {

    private static CountDownTimer mCountDownTimer;

    /**
     * 验证码倒计时
     */
    public static void countDown(final TextView textView) {
        mCountDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setEnabled(false);
                textView.setText(String.format("%ss", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                textView.setEnabled(true);
                textView.setText(textView.getContext().getResources().getString(R.string.get_code));
            }
        };
        mCountDownTimer.start();
    }


}
