package com.whmnrc.feimei.presener;

import com.alibaba.fastjson.JSON;
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

public class GetReadPresenter extends PresenterBase {

    private GetReadListener mGetReadListener;
    private int page = 0;

    public GetReadPresenter(GetReadListener getReadListener) {
        this.mGetReadListener = getReadListener;
    }


    public void getReadList(boolean isRefresh, String searchContent) {
        HashMap<String, Object> params = new HashMap<>(4);
        params.put("rows", 10);
        if (isRefresh) {
            page = 1;
        } else {
            ++page;
        }
        params.put("page", page);
        params.put("sidx", "desc");
        params.put("sord", "Sord");

        HashMap<String, Object> conditionJson = new HashMap<>(4);
        conditionJson.put("Name", searchContent);
        params.put("conditionJson", JSON.toJSONString(conditionJson));

        OKHttpManager.postString(getUrl(R.string.GeRead), params, new CommonCallBack<BaseBean>() {
            @Override
            protected void onSuccess(BaseBean data) {
                if (data.getType() == 1) {
                    mGetReadListener.getReadSuccess(isRefresh);
                } else {
                    mGetReadListener.getReadField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetReadListener {
        void getReadSuccess(boolean isRefresh);

        void getReadField();
    }

}
