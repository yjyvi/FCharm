package com.whmnrc.feimei.presener;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.OrderListBean;
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

public class OrderListPresenter extends PresenterBase {
    private OrderListListener mOrderListListener;

    private int page;

    public OrderListPresenter(OrderListListener orderListListener) {
        this.mOrderListListener = orderListListener;
    }

    public void getOrderList(boolean isRefresh, int state, int orderType) {
        HashMap<String, Object> params = new HashMap<>(8);
        params.put("Mobile", UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());
        params.put("rows", "10");
        if (isRefresh) {
            page = 1;
        } else {
            ++page;
        }
        params.put("page", page);
        params.put("sidx", "");
        params.put("sord", "");

        HashMap<String, Object> conditionJson = new HashMap<>(4);
        if (state >= 0) {
            conditionJson.put("State", state);
        }
        if (orderType >= 0) {
            conditionJson.put("OrderType", orderType);
        }

        params.put("conditionJson", JSON.toJSONString(conditionJson));

        OKHttpManager.postString(getUrl(R.string.GetOrder), params, new CommonCallBack<OrderListBean>() {

            @Override
            public void onFailure(int call, String e) {
                super.onFailure(call, e);
                mOrderListListener.getOrderListField();
            }

            @Override
            protected void onSuccess(OrderListBean data) {
                if (data.getType() == 1) {
                    mOrderListListener.getOrderListSuccess(data.getResultdata(), isRefresh);
                } else {
                    ToastUtils.showToast(data.getMessage());
                    mOrderListListener.getOrderListField();
                }
            }
        });
    }

    public interface OrderListListener {
        void getOrderListSuccess(OrderListBean.ResultdataBean orderListBean, boolean isRefresh);

        void getOrderListField();
    }


}
