package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.OrderDetailsBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/23.
 */

public class OrderDetailsPresenter extends PresenterBase {
    private OrderDetailsListener mOrderDetailsListener;


    public OrderDetailsPresenter(OrderDetailsListener orderDetailsListener) {
        this.mOrderDetailsListener = orderDetailsListener;
    }

    public void getOrderDetails(String  orderId) {
        HashMap<String, Object> params = new HashMap<>(4);
        params.put("Mobile",UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());
        params.put("ID", orderId);


        OKHttpManager.postString(getUrl(R.string.GetOrderDetails), params, new CommonCallBack<OrderDetailsBean>() {

            @Override
            public void onFailure(int call, String e) {
                super.onFailure(call, e);
                mOrderDetailsListener.getOrderDetailsField();
            }

            @Override
            protected void onSuccess(OrderDetailsBean data) {
                if (data.getType() == 1) {
                    mOrderDetailsListener.getOrderDetailsSuccess(data.getResultdata());
                } else {
                    ToastUtils.showToast(data.getMessage());
                    mOrderDetailsListener.getOrderDetailsField();
                }
            }
        });
    }

    public interface OrderDetailsListener {
        void getOrderDetailsSuccess(OrderDetailsBean.ResultdataBean bean);

        void getOrderDetailsField();
    }


}
