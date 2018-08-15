package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.BaseBean;
import com.whmnrc.feimei.beans.UserBean;
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

public class AddOrDelCollectionPresenter extends PresenterBase {

    private AddOrDelCollectionListener mAddOrDelCollectionListener;

    public AddOrDelCollectionPresenter(AddOrDelCollectionListener addOrDelCollectionListener) {
        this.mAddOrDelCollectionListener = addOrDelCollectionListener;

    }

    public void addOrDelCollection(boolean isAdd, String productId) {
        HashMap<String, Object> params = new HashMap<>(5);
        params.put("type", isAdd ? 1 : 0);
        params.put("Mobile", UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());
        params.put("OtherID", productId);

        int api;
        if (isAdd) {
            api = R.string.AddCollection;
        } else {
            api = R.string.DelCollection;
        }

        OKHttpManager.postString(getUrl(api), params, new CommonCallBack<BaseBean>() {
            @Override
            protected void onSuccess(BaseBean data) {
                if (data.getType() == 1) {
                    mAddOrDelCollectionListener.collectionSuccess();
                } else {
                    mAddOrDelCollectionListener.collectionCodeField();
                }
                ToastUtils.showToast(data.getMessage());
            }
        });
    }


    public interface AddOrDelCollectionListener {
        void collectionSuccess();

        void collectionCodeField();
    }

}
