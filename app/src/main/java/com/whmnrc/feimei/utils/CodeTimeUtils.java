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

    /**
     * 倒计时
     */
    public static void payOrderTime(TextView textView, PayOrderTimeListener payOrderTimeListener) {
        mCountDownTimer = new CountDownTimer(5_60_000L, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(String.format("支付剩余时间  %s", TimeUtils.second2MMSS((int) millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                textView.setText("支付超时");
                if (payOrderTimeListener != null) {
                    payOrderTimeListener.payField();
                }
                if (mCountDownTimer != null) {
                    mCountDownTimer.cancel();
                    mCountDownTimer = null;
                }
            }
        };
        mCountDownTimer.start();
    }

    public static void cancelTimer(){
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    public interface PayOrderTimeListener {
        void payField();
    }


}
