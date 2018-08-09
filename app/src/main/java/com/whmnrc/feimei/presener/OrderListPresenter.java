package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.ui.PresenterBase;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/23.
 */

public class OrderListPresenter extends PresenterBase {
    private OrderListListener mOrderListListener;

    public OrderListPresenter(OrderListListener orderListListener) {
        this.mOrderListListener = orderListListener;
    }

    public void getOrderList( int orderState,  int page) {
        HashMap<String, String> paramters = new HashMap<>(4);
//        paramters.put("userId", UserManager.getUser().getId());
//        paramters.put("pageNo", String.valueOf(page));
//        paramters.put("orderStatus", String.valueOf(orderState));
//        paramters.put("pageSize", "10");
//        String url = getUrl(R.string.GetAllOrder);
//        OKHttpManager.get(url, paramters, new CommonCallBack<OrderListBean>() {
//
//            @Override
//            public void onFailure(int call, String e) {
//                super.onFailure(call, e);
//                mOrderListListener.getOrderListField();
//            }
//
//            @Override
//            protected void onSuccess(OrderListBean data) {
//                if (data.getType() == 1) {
//                    mOrderListListener.getOrderListSuccess(data.getResultdata());
//                } else {
//                    ToastUtils.showToast(data.getMessage());
//                    mOrderListListener.getOrderListField();
//                }
//            }
//        });
    }

    public interface OrderListListener {
        void getOrderListSuccess();
        void getOrderListField();
    }


}
