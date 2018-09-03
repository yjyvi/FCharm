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
 * 评价商品
 */

public class SendCommentPresenter extends PresenterBase {

    private SendCommentListener mSendCommentListener;

    public SendCommentPresenter(SendCommentListener addEvaluateListener) {
        this.mSendCommentListener = addEvaluateListener;
    }


    public void sendComment(String content, Object imgs, String goodsId) {
        HashMap<Object, Object> params = new HashMap<>(6);
        params.put("OtherID", goodsId);
        params.put("Conten", content);
        params.put("ImgList", imgs);
        params.put("Mobile", UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());
        OKHttpManager.postString2(getUrl(R.string.SendComment), params, new CommonCallBack<BaseBean>() {
            @Override
            protected void onSuccess(BaseBean data) {
                if (data.getType() == 1) {
                    mSendCommentListener.sendEvaluateSuccess();
                } else {
                    mSendCommentListener.sendEvaluateField();
                }
                ToastUtils.showToast(data.getMessage());
            }
        });
    }


    public interface SendCommentListener {
        void sendEvaluateSuccess();

        void sendEvaluateField();
    }

}
