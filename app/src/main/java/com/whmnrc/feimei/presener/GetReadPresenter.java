package com.whmnrc.feimei.presener;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
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
 */

public class GetReadPresenter extends PresenterBase {

    private GetReadListener mGetReadListener;
    private int page = 0;

    public GetReadPresenter(GetReadListener getReadListener) {
        this.mGetReadListener = getReadListener;
    }

    public void getReadList(boolean isRefresh) {
        getReadList(isRefresh, "", "", -1);
    }


    public void getReadList(boolean isRefresh, String searchContent, String columnId, int type) {
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

        HashMap<String, Object> conditionJson = new HashMap<>(4);
        if (!TextUtils.isEmpty(searchContent)) {
            conditionJson.put("Title", searchContent);
        }

        if (!TextUtils.isEmpty(columnId)) {
            conditionJson.put("ColumnID", columnId);
        }

        if (type >= 0) {
            conditionJson.put("Type", type);
        }

        params.put("conditionJson", JSON.toJSONString(conditionJson));

        OKHttpManager.postString(getUrl(R.string.GeRead), params, new CommonCallBack<ReadListBean>() {
            @Override
            protected void onSuccess(ReadListBean data) {
                if (data.getType() == 1) {
                    mGetReadListener.getReadSuccess(isRefresh, data.getResultdata());
                } else {
                    mGetReadListener.getReadField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetReadListener {
        void getReadSuccess(boolean isRefresh, ReadListBean.ResultdataBean bean);

        void getReadField();
    }

}
