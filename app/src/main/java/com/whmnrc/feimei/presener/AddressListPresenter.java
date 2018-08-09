package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.beans.AddressBean;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.ui.UserManager;

import java.util.HashMap;
import java.util.List;

/**
 * @author yjyvi
 * @data 2018/5/30.
 */

public class AddressListPresenter extends PresenterBase {

    private AddressListListener mAddressListListener;

    public AddressListPresenter(AddressListListener addressListListener) {
        this.mAddressListListener = addressListListener;

    }


    public void getAddressList() {
        HashMap<String, String> paramters = new HashMap<>();
        paramters.put("UserInfoID", UserManager.getUser().getMobile());
//        OKHttpManager.get(getUrl(R.string.GetAllAddress), paramters, new CommonCallBack<AddressBean>() {
//            @Override
//            protected void onSuccess(AddressBean data) {
//                if (data.getType() == 1) {
//                    mAddressListListener.getListSuccess(data.getResultdata());
//                } else {
//                    ToastUtils.showToast(data.getMessage());
//                }
//            }
//        });
    }


    public interface AddressListListener {
        void getListSuccess(List<AddressBean.ResultdataBean> resultdataBeans);
    }
}
