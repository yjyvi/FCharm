package com.whmnrc.feimei.presener;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.NewsListBean;
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

public class GetNewsPresenter extends PresenterBase {

    private GetNewsListener mGetNewsListener;
    private int page = 0;

    public GetNewsPresenter(GetNewsListener getNewsListener) {
        this.mGetNewsListener = getNewsListener;
    }

    public void getNewsList() {
        getNewsList(true, "", -1);
    }


    public void getNewsList(boolean isRefresh, String searchContent, int type) {
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
            conditionJson.put("Name", searchContent);
        }
        if (!TextUtils.isEmpty(searchContent)) {
            conditionJson.put("Name", searchContent);
        }

        if (type > 0) {
            conditionJson.put("Type", type);
        }

        params.put("conditionJson", JSON.toJSONString(conditionJson));

        OKHttpManager.postString(getUrl(R.string.GetNews), params, new CommonCallBack<NewsListBean>() {
            @Override
            protected void onSuccess(NewsListBean data) {
                if (data.getType() == 1) {
                    mGetNewsListener.getNewsSuccess(isRefresh, data.getResultdata());
                } else {
                    mGetNewsListener.getNewsField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetNewsListener {
        void getNewsSuccess(boolean isRefresh, NewsListBean.ResultdataBean bean);

        void getNewsField();
    }

}
