package com.whmnrc.feimei.utils.pay;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.whmnrc.feimei.CommonConstant;
import com.whmnrc.feimei.MyApplication;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.BaseBean;
import com.whmnrc.feimei.beans.WxPayBean;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/06/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class PayUtils {

    public static final String EVENT_WECHAT_PAY_SUCCESS = "wechat_pay_success";

    public static final String EVENT_WECHAT_PAY_FAILURE = "wechat_pay_failure";

    public static final String EVENT_WECHAT_PAY_CANCLE = "wechat_pay_cancel";

    private static OKHttpManager.ObjectCallback sObjectCallback;


    private void payWeChat(Context context, final String packageValue, final String sign, final String partnerId, final String prepayId, final String nonceStr, final String timeStamp) {
        final IWXAPI api = WXAPIFactory.createWXAPI(context, CommonConstant.Common.WX_APP_ID);
        api.registerApp(CommonConstant.Common.WX_APP_ID);

        PayReq req = new PayReq();
        req.appId = CommonConstant.Common.WX_APP_ID;
        req.partnerId = partnerId;
        req.prepayId = prepayId;
        req.packageValue = packageValue;
        req.nonceStr = nonceStr;
        req.timeStamp = timeStamp;
        req.sign = sign;
        api.sendReq(req);

    }


    /**
     * 创建签名
     *
     * @param orderId
     * @param payType
     * @param objectCallback
     */
    private void createOrderSign(String orderId, int payType, OKHttpManager.ObjectCallback objectCallback) {
        String url = MyApplication.applicationContext.getString(R.string.service_host_address).concat(MyApplication.applicationContext.getString(R.string.Pay));
        Map params = new HashMap();
        params.put("Mobile", UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());
        params.put("OrderID", orderId);
        params.put("PayType", payType);
        OKHttpManager.postString(url, params, objectCallback);
    }


    public PayUtils() {
        EventBus.getDefault().register(this);
    }


    /**
     * @param payType        1-支付宝，2-微信
     * @param order
     * @param objectCallback
     */
    public void playPay(Context context, final int payType, final String order, final OKHttpManager.ObjectCallback objectCallback) {
        //创建sign
        createOrderSign(order, payType, new OKHttpManager.ObjectCallback() {
            @Override
            public void onSuccess(String st) {
                if (TextUtils.isEmpty(st)) {
                    return;
                }
                BaseBean baseBean = JSON.parseObject(st, BaseBean.class);
                if (baseBean == null) {
                    return;
                }
                if (baseBean.getType() != 1) {
                    ToastUtils.showToast(baseBean.getMessage());
                    return;
                }

                switch (payType) {
                    //支付宝
                    case CommonConstant.Common.PAY_METHOD_ZFB:
                        zfb(context, st, objectCallback);
                        break;
                    //微信
                    case CommonConstant.Common.PAY_METHOD_WX:
                        wx(context, st, objectCallback);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(int code, String errorMsg) {
                ToastUtils.showToast("创建sign失败" + errorMsg);
            }
        });
    }

    /**
     * 微信的支付
     *
     * @param st
     * @param objectCallback
     */
    private void wx(Context context, String st, OKHttpManager.ObjectCallback objectCallback) {
        WxPayBean baseBean1 = JSON.parseObject(st, WxPayBean.class);
        if (baseBean1 == null) {
            return;
        }

        if (baseBean1.getResultdata() == null) {
            return;
        }

        if (baseBean1.getResultdata().getWeCharPay() == null) {
            return;
        }

        WxPayBean.ResultdataBean.WeCharPayBean.DataBean data = baseBean1.getResultdata().getWeCharPay().getData();
        if (data == null) {
            ToastUtils.showToast(baseBean1.getMessage());
            return;
        }
        payWeChat(context, data.getPackageValue(), data.getSign(), data.getPartnerId(),
                data.getPrepayId(), data.getNonceStr(), data.getTimeStamp());
        sObjectCallback = objectCallback;
    }


    /**
     * 支付宝的支付
     *
     * @param st
     * @param objectCallback
     */
    private void zfb(Context context, String st, final OKHttpManager.ObjectCallback objectCallback) {
        PaySignBean baseBean = JSON.parseObject(st, PaySignBean.class);
        if (baseBean.getType() == 1) {
            String sign = baseBean.getResultdata().getSign();

            Observable.create(new AliPayObserver(sign, (Activity) context))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(String stringStringMap) {

                            PayResult payResult = new PayResult(stringStringMap);
                            String resultStatus = payResult.getResultStatus();
                            // 判断resultStatus 为9000则代表支付成功
                            if (TextUtils.equals(resultStatus, "9000")) {
                                objectCallback.onSuccess("支付成功");
                            } else {
                                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                                objectCallback.onFailure(0, payResult.getMemo());
                            }

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }


    /**
     * 微信支付回调
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onWechatPayEvent(String event) {
        switch (event) {
            case PayUtils.EVENT_WECHAT_PAY_SUCCESS:
                if (sObjectCallback != null) {
                    sObjectCallback.onSuccess("支付成功");
                }
                break;
            case PayUtils.EVENT_WECHAT_PAY_FAILURE:
                if (sObjectCallback != null) {
                    sObjectCallback.onFailure(0, "支付失败");
                }
                break;
            case PayUtils.EVENT_WECHAT_PAY_CANCLE:
                if (sObjectCallback != null) {
                    sObjectCallback.onFailure(0, "支付取消");
                }
                break;
            default:
                if (sObjectCallback != null) {
                    sObjectCallback.onFailure(0, "支付失败");
                }
                break;
        }

        EventBus.getDefault().unregister(this);


        sObjectCallback = null;

    }
}
