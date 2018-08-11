package com.whmnrc.feimei.presener;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.CommentListBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class GetCommentPresenter extends PresenterBase {

    private GetCommentListener mGetCommentListener;
    private int page = 0;

    public GetCommentPresenter(GetCommentListener getCodeListener) {
        this.mGetCommentListener = getCodeListener;
    }

    public void getComment(String otherId, final boolean isRefresh) {
        HashMap<String, Object> params = new HashMap<>(7);
        HashMap<String, String> conditionJson = new HashMap<>(3);

        if (!TextUtils.isEmpty(otherId)) {
            conditionJson.put("OtherID", otherId);
        }

        params.put("rows", "5");
        if (isRefresh) {
            page = 1;
        } else {
            ++page;
        }
        params.put("page", String.valueOf(page));
        params.put("sidx", "");
        params.put("sord", "");
        params.put("conditionJson", JSON.toJSONString(conditionJson));
        OKHttpManager.postString(getUrl(R.string.GetComment), params, new CommonCallBack<CommentListBean>() {
            @Override
            protected void onSuccess(CommentListBean data) {
                if (data.getType() == 1) {
                    mGetCommentListener.getCommentListSuccess(data.getResultdata(), isRefresh);
                } else {
                    mGetCommentListener.getCommentListField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetCommentListener {
        void getCommentListSuccess(CommentListBean.ResultdataBean beans, boolean isRefresh);

        void getCommentListField();
    }

}
