package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.CommonConstant;
import com.whmnrc.feimei.MyApplication;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.UserBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.SPUtils;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class RegisterPresenter extends PresenterBase {

    private RegisterListener mRegisterListener;

    public RegisterPresenter(RegisterListener registerListener) {
        this.mRegisterListener = registerListener;

    }

    public void register(String pwd, String phoneCode,String openId,String tel) {
        HashMap<String, Object> params = new HashMap<>(8);
        params.put("Pwd", pwd);
        params.put("PhoneCode", phoneCode);
        params.put("Auth_EquipmentCode", SPUtils.getStringToken(MyApplication.applicationContext, CommonConstant.Common.DEVICE_TOKEN));
        params.put("Auth_Type", "1");
        params.put("OpenID", openId);
        params.put("Mobile", tel);
        OKHttpManager.postString(getUrl(R.string.Register), params, new CommonCallBack<UserBean>() {
            @Override
            protected void onSuccess(UserBean data) {
                if (data.getType() == 1 && data.getResultdata()!=null ) {
                    mRegisterListener.registerSuccess(data.getResultdata());
                }else {
                    mRegisterListener.registerField();
                }
                ToastUtils.showToast(data.getMessage());
            }
        });
    }


    public interface RegisterListener {
        void registerSuccess(UserBean.ResultdataBean userBean);
        void registerField();
    }

}
