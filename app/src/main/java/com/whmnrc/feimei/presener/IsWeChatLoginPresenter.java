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

public class IsWeChatLoginPresenter extends PresenterBase {

    private IsWeChatLoginListener mIsWeChatLoginListener;

    public IsWeChatLoginPresenter(IsWeChatLoginListener isWeChatLoginListener) {
        this.mIsWeChatLoginListener = isWeChatLoginListener;

    }

    public void isWeChatLogin(String openId) {
        HashMap<String, Object> params = new HashMap<>(5);
        params.put("OpenID", openId);
        params.put("Auth_EquipmentCode", SPUtils.getStringToken(MyApplication.applicationContext, CommonConstant.Common.DEVICE_TOKEN));
        params.put("Auth_Type", "1");
        OKHttpManager.postString(getUrl(R.string.WeChatLogin), params, new CommonCallBack<UserBean>() {
            @Override
            protected void onSuccess(UserBean data) {
                mIsWeChatLoginListener.isWeChat(data.getResultdata());
                ToastUtils.showToast(data.getMessage());
            }
        });
    }

    public void weChatLogin(String openId, String phoneCode, String mobile) {
        HashMap<String, Object> params = new HashMap<>(7);
        params.put("PhoneCode", phoneCode);
        params.put("OpenID", openId);
        params.put("Mobile", mobile);
        params.put("Auth_EquipmentCode", SPUtils.getStringToken(MyApplication.applicationContext, CommonConstant.Common.DEVICE_TOKEN));
        params.put("Auth_Type", "1");
        OKHttpManager.postString(getUrl(R.string.WeChatIsRegister), params, new CommonCallBack<UserBean>() {
            @Override
            protected void onSuccess(UserBean data) {
                if (data.getType() == 1) {
                    mIsWeChatLoginListener.isWeChat(data.getResultdata());
                }
                ToastUtils.showToast(data.getMessage());
            }
        });
    }


    public interface IsWeChatLoginListener {
        void isWeChat(UserBean.ResultdataBean userBean);
    }

}
