package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.AllSearchBean;
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

public class AllSearchPresenter extends PresenterBase {

    private AllSearchListener mAllSearchListener;

    public AllSearchPresenter(AllSearchListener allSearchListener) {
        this.mAllSearchListener = allSearchListener;
    }

    public void search(String searchContent) {
        HashMap<String, Object> params = new HashMap<>(4);
        params.put("Condition", searchContent);
        params.put("Mobile", UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());
        OKHttpManager.postString(getUrl(R.string.Search), params, new CommonCallBack<AllSearchBean>() {
            @Override
            protected void onSuccess(AllSearchBean data) {
                if (data.getType() == 1) {
                    mAllSearchListener.searchSuccess(data.getResultdata());
                } else {
                    mAllSearchListener.searchField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface AllSearchListener {
        void searchSuccess(AllSearchBean.ResultdataBean bean);

        void searchField();
    }

}
