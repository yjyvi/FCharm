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
 * 上传头像
 */

public class UpdateUserInfoPresenter extends PresenterBase {

    private UpdateUserInfoListener mUpdateUserInfoListener;

    public UpdateUserInfoPresenter(UpdateUserInfoListener updateUserInfoListener) {
        this.mUpdateUserInfoListener = updateUserInfoListener;
    }

    public void updateUserInfo(String headImgUrl, String nickname) {
        HashMap<String, Object> paramters = new HashMap<>(5);
        paramters.put("HeadImg", headImgUrl);
        paramters.put("NickName", nickname);
        paramters.put("Mobile", UserManager.getUser().getMobile());
        OKHttpManager.postString(getUrl(R.string.ModifyUserBasicData), paramters, new CommonCallBack<BaseBean>() {
            @Override
            protected void onSuccess(BaseBean data) {
                if (data.getType() == 1) {
                    mUpdateUserInfoListener.uploadUserInfoSuccess(data.getMessage());
                }
                ToastUtils.showToast(data.getMessage());
            }
        });
    }


    public interface UpdateUserInfoListener {
        void uploadUserInfoSuccess(String resultMsg);
    }
}
