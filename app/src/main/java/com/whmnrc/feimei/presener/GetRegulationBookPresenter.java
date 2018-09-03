package com.whmnrc.feimei.presener;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.RegulationBookListBean;
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

public class GetRegulationBookPresenter extends PresenterBase {

    private GetBookListener mGetBookListener;
    private int page = 0;

    public GetRegulationBookPresenter(GetBookListener getReadListener) {
        this.mGetBookListener = getReadListener;
    }

    public void getBookList() {
        getBookList(true, "", "","");
    }

    public void getBook(String bookId) {
        getBookList(true, "", "",bookId);
    }


    public void getBookList(boolean isRefresh, String searchContent, String columnId, String bookId) {
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
        if (!TextUtils.isEmpty(columnId)) {
            conditionJson.put("ColumnID", columnId);
        }

        if (!TextUtils.isEmpty(bookId)) {
            conditionJson.put("ID", bookId);
        }
        params.put("conditionJson", JSON.toJSONString(conditionJson));

        OKHttpManager.postString(getUrl(R.string.GetRegulationBook), params, new CommonCallBack<RegulationBookListBean>() {
            @Override
            protected void onSuccess(RegulationBookListBean data) {
                if (data.getType() == 1) {
                    mGetBookListener.getBookSuccess(isRefresh, data.getResultdata());
                } else {
                    mGetBookListener.getBookField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetBookListener {
        void getBookSuccess(boolean isRefresh, RegulationBookListBean.ResultdataBean bean);

        void getBookField();
    }

}
