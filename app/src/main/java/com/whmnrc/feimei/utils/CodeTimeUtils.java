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
     * 订单支付剩余时间
     */
    public static long ORDER_PAY_TIME = 900_000L;
    public static long resultTime;

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
     * 订单支付倒计时
     */
    public static void payOrderTime(TextView textView, long orderCreateTime, PayOrderTimeListener payOrderTimeListener) {

        if (orderCreateTime == 0) {
            resultTime = ORDER_PAY_TIME;
        } else {
            resultTime = System.currentTimeMillis() - orderCreateTime - ORDER_PAY_TIME;

            if (resultTime >= 0) {
                textView.setText("支付超时");
                if (payOrderTimeListener != null) {
                    payOrderTimeListener.payField();
                }
                if (mCountDownTimer != null) {
                    mCountDownTimer.cancel();
                    mCountDownTimer = null;
                }
                return;
            }

            resultTime = Math.abs(resultTime);
        }


        mCountDownTimer = new CountDownTimer(resultTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(String.format("支付剩余时间  %s自动取消订单", TimeUtils.second2MMSS((int) millisUntilFinished / 1000)));
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

    public static void startTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.start();
        }
    }

    public static void cancelTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    public interface PayOrderTimeListener {
        void payField();
    }


}
