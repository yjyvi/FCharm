package com.whmnrc.feimei.presener;

import android.text.TextUtils;

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

public class CreateOrderPresenter extends PresenterBase {

    private CreateOrderListener mCreateOrderListener;

    public CreateOrderPresenter(CreateOrderListener createOrderListener) {
        this.mCreateOrderListener = createOrderListener;

    }

    /**
     * @param number    商品数量
     * @param addressId 地址ID
     * @param orderType 0-商品  1-会员 2-赞赏 3-企业 4-专栏 5-文库
     * @param otherId   购买的商品ID
     */
    public void createOrder(int number, String addressId, int orderType, String otherId) {
        createOrder(number, addressId, orderType, otherId, "");
    }

    public void createOrder(int number, String addressId, int orderType, String otherId, String remark) {
        HashMap<String, Object> params = new HashMap<>(7);
        params.put("Number", number);
        params.put("AddressID", addressId);
        params.put("OrderType", orderType);
        params.put("OtherID", otherId);
        params.put("Remark", remark);

        if (UserManager.getUser()==null) {
            return;
        }

        if (TextUtils.isEmpty(UserManager.getUser() == null ? "" : UserManager.getUser().getMobile())) {
            return;
        }

        params.put("Mobile", UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());
        OKHttpManager.postString(getUrl(R.string.SendOrder), params, new CommonCallBack<BaseBean>() {
            @Override
            protected void onSuccess(BaseBean data) {
                if (data.getType() == 1 && data.getResultdata() != null) {
                    mCreateOrderListener.createOrderSuccess((String) data.getResultdata());
                } else {
                    mCreateOrderListener.createOrderField();
                    ToastUtils.showToast(data.getMessage());
                }
            }

            @Override
            public void onFailure(int call, String e) {
                super.onFailure(call, e);
                mCreateOrderListener.createOrderField();
            }
        });
    }


    public interface CreateOrderListener {
        void createOrderSuccess(String orderId);

        void createOrderField();
    }

}
