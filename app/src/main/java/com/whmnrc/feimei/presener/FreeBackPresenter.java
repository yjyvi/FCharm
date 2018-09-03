package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.ReadListBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/29.
 * 意见反馈
 */

public class FreeBackPresenter extends PresenterBase {

    private FreeBackListener mFreeBackListener;

    public FreeBackPresenter(FreeBackListener freeBackListener) {
        this.mFreeBackListener = freeBackListener;
    }

    public void getMsgList(String content) {
        HashMap<String, Object> params = new HashMap<>(4);
        params.put("Mobile", UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());
        params.put("Content", content);


        OKHttpManager.postString(getUrl(R.string.Feedback), params, new CommonCallBack<ReadListBean>() {
            @Override
            protected void onSuccess(ReadListBean data) {
                if (data.getType() == 1) {
                    mFreeBackListener.freeBackSuccess();
                }
                ToastUtils.showToast(data.getMessage());
            }
        });
    }


    public interface FreeBackListener {
        void freeBackSuccess();
    }

}
