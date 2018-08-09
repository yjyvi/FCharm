package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.ui.UserManager;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/30.
 */

public class AddressEditPresenter extends PresenterBase {

    private AddressEditListener mAddressEditListener;

    public AddressEditPresenter(AddressEditListener addressEditListener) {
        this.mAddressEditListener = addressEditListener;
    }

    public void delAddress(String addressId) {
        HashMap<String, String> paramters = new HashMap<>(2);
        paramters.put("Address_ID", addressId);
        paramters.put("UserId", UserManager.getUser().getMobile());
//        OKHttpManager.get(getUrl(R.string.DelAddress), paramters, new CommonCallBack<AddressBean>() {
//            @Override
//            protected void onSuccess(AddressBean data) {
//                if (data.getType() == 1) {
//                    mAddressEditListener.delSuccess();
//                }
//                ToastUtils.showToast(data.getMessage());
//            }
//        });
    }


    public interface AddressEditListener {
        void delSuccess();
    }
}
