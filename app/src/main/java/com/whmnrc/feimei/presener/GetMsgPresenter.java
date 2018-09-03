package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.BaseBean;
import com.whmnrc.feimei.beans.MsgListBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class GetMsgPresenter extends PresenterBase {

    private GetMsgListener mGetMsgListener;
    private int page = 0;

    public GetMsgPresenter(GetMsgListener getMsgListener) {
        this.mGetMsgListener = getMsgListener;
    }


    public void getMsgList(boolean isRefresh) {
        HashMap<String, Object> params = new HashMap<>(8);
        params.put("rows", 10);
        params.put("Mobile", UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());
        if (isRefresh) {
            page = 1;
        } else {
            ++page;
        }
        params.put("page", page);
        params.put("sidx", "");
        params.put("sord", "");
        params.put("conditionJson", "");

        OKHttpManager.postString(getUrl(R.string.GetPush), params, new CommonCallBack<MsgListBean>() {
            @Override
            protected void onSuccess(MsgListBean data) {
                if (data.getType() == 1) {
                    mGetMsgListener.getMsgSuccess(isRefresh, data.getResultdata());
                } else {
                    mGetMsgListener.getMsgField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }

    public void readMsg(String msgId) {
        HashMap<String, Object> params = new HashMap<>(8);
        params.put("ID", msgId);
        params.put("Mobile", UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());


        OKHttpManager.postString(getUrl(R.string.ReadPush), params, new CommonCallBack<BaseBean>() {
            @Override
            protected void onSuccess(BaseBean data) {
                if (data.getType() == 1) {
                    mGetMsgListener.readMsgSuccess();
                } else {
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetMsgListener {


        void getMsgSuccess(boolean isRefresh, MsgListBean.ResultdataBean bean);

        void getMsgField();

        void readMsgSuccess();

    }

}
