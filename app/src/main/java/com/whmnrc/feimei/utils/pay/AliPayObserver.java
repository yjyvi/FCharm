package com.whmnrc.feimei.utils.pay;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by yong hao zeng on 2018/1/5.
 */

public class AliPayObserver implements ObservableOnSubscribe<String> {
    String sign;
    Activity mContext;

    public AliPayObserver(String sign, Activity mContext) {
        this.sign = sign;
        this.mContext = mContext;
    }

    @Override
    public void subscribe(ObservableEmitter<String> e) throws Exception {
        PayTask alipay = new PayTask(mContext);
        String result = alipay.pay(sign, true);
        e.onNext(result);
        e.onComplete();
    }
}
