package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.BaseBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class OrderCancelOrRefundPresenter extends PresenterBase {

    private OrderOperationListener mOrderOperationListener;

    public OrderCancelOrRefundPresenter(OrderOperationListener orderOperationListener) {
        this.mOrderOperationListener = orderOperationListener;

    }


    /**
     * 取消订单
     * @param orderId
     */
    public void cancelOrder(String orderId) {
        HashMap<String, Object> params = new HashMap<>(4);
        params.put("ID", orderId);
        params.put("Mobile", UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());
        OKHttpManager.postString(getUrl(R.string.CancelOrder), params, new CommonCallBack<BaseBean>() {
            @Override
            protected void onSuccess(BaseBean data) {
                if (data.getType() == 1) {
                    mOrderOperationListener.cancelSuccess();
                } else {
                    mOrderOperationListener.cancelField();
                }
                ToastUtils.showToast(data.getMessage());
            }
        });
    }


    /**
     * 发起退款
     * @param orderId
     */
    public void refundOrder(String orderId) {
        HashMap<String, Object> params = new HashMap<>(4);
        params.put("ID", orderId);
        params.put("Mobile", UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());
        OKHttpManager.postString(getUrl(R.string.RefundOrder), params, new CommonCallBack<BaseBean>() {
            @Override
            protected void onSuccess(BaseBean data) {
                if (data.getType() == 1) {
                    mOrderOperationListener.refundSuccess();
                } else {
                    mOrderOperationListener.refundField();
                }
                ToastUtils.showToast(data.getMessage());
            }
        });
    }


    public interface OrderOperationListener {
        void cancelSuccess();

        void refundSuccess();

        void cancelField();

        void refundField();
    }

}
