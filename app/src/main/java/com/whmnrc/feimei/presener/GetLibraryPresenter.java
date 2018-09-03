package com.whmnrc.feimei.presener;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.ResourcesFileBean;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class GetLibraryPresenter extends PresenterBase {

    private GetLibraryListener mGetLibraryListener;
    private int page = 0;

    public GetLibraryPresenter(GetLibraryListener getLibraryListener) {
        this.mGetLibraryListener = getLibraryListener;
    }

    public void getLibraryList() {
        getLibraryList(true, "", "");
    }


    public void getLibraryList(boolean isRefresh, String searchContent, String libraryId) {
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
        if (!TextUtils.isEmpty(libraryId)) {
            conditionJson.put("ID", libraryId);
            params.put("rows", 1);
        }
        if (!TextUtils.isEmpty(searchContent)) {
            conditionJson.put("Name", searchContent);
        }

        params.put("conditionJson", JSON.toJSONString(conditionJson));

        OKHttpManager.postString(getUrl(R.string.GetLibrary), params, new OKHttpManager.ObjectCallback() {
            @Override
            public void onSuccess(String st) {
                ResourcesFileBean resourcesFileBean = JSON.parseObject(st, ResourcesFileBean.class);
                if (resourcesFileBean.getType() == 1) {
                    mGetLibraryListener.getReadSuccess(isRefresh, resourcesFileBean.getResultdata());
                } else {
                    mGetLibraryListener.getReadField();
                    ToastUtils.showToast(resourcesFileBean.getMessage());
                }
            }

            @Override
            public void onFailure(int code, String errorMsg) {
                mGetLibraryListener.getReadField();
            }

        });
    }


    public interface GetLibraryListener {
        void getReadSuccess(boolean isRefresh, ResourcesFileBean.ResultdataBean bean);

        void getReadField();
    }

}
