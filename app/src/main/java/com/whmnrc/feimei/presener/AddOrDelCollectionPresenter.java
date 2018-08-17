package com.whmnrc.feimei.presener;

import com.alibaba.fastjson.JSON;
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
 * @data 2018/5/29.
 */

public class AddOrDelCollectionPresenter extends PresenterBase {

    /**
     * 商品收藏
     */
    public static final int PRODUCT_COLLECTION = 0;
    /**
     * 阅读收藏
     */
    public static final int READ_COLLECTION = 1;
    /**
     * 文库收藏
     */
    public static final int FILE_COLLECTION = 2;
    /**
     * 规格书收藏
     */
    public static final int SPECIFICATION_COLLECTION = 3;
    /**
     * 资讯收藏
     */
    public static final int INFORMATION_COLLECTION = 4;


    private AddOrDelCollectionListener mAddOrDelCollectionListener;

    public AddOrDelCollectionPresenter(AddOrDelCollectionListener addOrDelCollectionListener) {
        this.mAddOrDelCollectionListener = addOrDelCollectionListener;

    }

    public void addCollection(String productId, int type) {
        HashMap<String, Object> params = new HashMap<>(5);
        params.put("type", type);
        params.put("Mobile", UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());
        params.put("OtherID", productId);


        OKHttpManager.postString(getUrl(R.string.AddCollection), params, new CommonCallBack<BaseBean>() {
            @Override
            protected void onSuccess(BaseBean data) {
                if (data.getType() == 1) {
                    mAddOrDelCollectionListener.collectionSuccess(true);
                } else {
                    mAddOrDelCollectionListener.collectionCodeField();
                }
                ToastUtils.showToast(data.getMessage());
            }
        });
    }

    public void delCollection(Object productIds) {
        HashMap<String, Object> params = new HashMap<>(4);
        params.put("Mobile", UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());
        params.put("CollectionID", productIds);
        OKHttpManager.postString(getUrl(R.string.DelCollection), params, new OKHttpManager.ObjectCallback() {
            @Override
            public void onSuccess(String st) {
                BaseBean baseBean = JSON.parseObject(st, BaseBean.class);
                if (baseBean.getType() == 1) {
                    mAddOrDelCollectionListener.collectionSuccess(false);
                } else {
                    mAddOrDelCollectionListener.collectionCodeField();
                    ToastUtils.showToast(baseBean.getMessage());
                }
            }

            @Override
            public void onFailure(int code, String errorMsg) {
                mAddOrDelCollectionListener.collectionCodeField();
            }
        });

    }


    public interface AddOrDelCollectionListener {
        void collectionSuccess(boolean isAdd);

        void collectionCodeField();
    }

}
