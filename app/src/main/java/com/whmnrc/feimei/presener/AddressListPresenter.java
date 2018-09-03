package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.AddressBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.utils.ToastUtils;

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
        HashMap<String, Object> paramters = new HashMap<>(3);
        paramters.put("Mobile", UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());
        OKHttpManager.postString(getUrl(R.string.GetAddress), paramters, new CommonCallBack<AddressBean>() {
            @Override
            protected void onSuccess(AddressBean data) {
                if (data.getType() == 1) {
                    mAddressListListener.getListSuccess(data.getResultdata());
                } else {
                    mAddressListListener.getAddressListField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface AddressListListener {
        void getListSuccess(List<AddressBean.ResultdataBean> resultdataBeans);
        void getAddressListField();
    }
}
