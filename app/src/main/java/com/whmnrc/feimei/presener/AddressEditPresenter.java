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
 * @data 2018/5/30.
 */

public class AddressEditPresenter extends PresenterBase {

    private AddressEditListener mAddressEditListener;

    public AddressEditPresenter(AddressEditListener addressEditListener) {
        this.mAddressEditListener = addressEditListener;
    }


    public void editAddress(String addressId,String name, String provice, String city, String region, String detail, String isDeftault, String tel) {
        HashMap<String, Object> params = new HashMap<>(11);
        params.put("ID", addressId);
        params.put("UserInfoID", UserManager.getUser().getID());
        params.put("Name", name);
        params.put("Provice", provice);
        params.put("City", city);
        params.put("Region", region);
        params.put("Detail", detail);
        params.put("IsDefault", isDeftault);
        params.put("Mobile", tel);
        OKHttpManager.postString(getUrl(R.string.Address), params, new CommonCallBack<BaseBean>() {
            @Override
            protected void onSuccess(BaseBean data) {
                if (data.getType() == 1) {
                    mAddressEditListener.addSuccess();
                } else {
                    mAddressEditListener.addField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface AddressEditListener {
        void addSuccess();
        void addField();
    }
}
