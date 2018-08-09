package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.BaseBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class FindPwdPresenter extends PresenterBase {

    private FindPwdListener mFindPwdListener;

    public FindPwdPresenter(FindPwdListener findPwdListener) {
        this.mFindPwdListener = findPwdListener;

    }

    public void findPwd(String pwd, String phoneCode,String tel) {
        HashMap<String, Object> params = new HashMap<>(5);
        params.put("Pwd", pwd);
        params.put("PhoneCode", phoneCode);
        params.put("Mobile", tel);
        OKHttpManager.postString(getUrl(R.string.RecoverUserPassword), params, new CommonCallBack<BaseBean>() {
            @Override
            protected void onSuccess(BaseBean data) {
                if (data.getType() == 1) {
                    mFindPwdListener.findPwdSuccess();
                }else {
                    mFindPwdListener.findPwdField();
                }
                ToastUtils.showToast(data.getMessage());
            }
        });
    }


    public interface FindPwdListener {
        void findPwdSuccess();
        void findPwdField();
    }

}
