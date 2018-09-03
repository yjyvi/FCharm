package com.whmnrc.feimei.presener;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.ProductCollectionListBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class GetCollectionListPresenter extends PresenterBase {

    private GetCollectionListListener mGetCollectionListListener;
    private int page = 0;

    public GetCollectionListPresenter(GetCollectionListListener getCollectionListListener) {
        this.mGetCollectionListListener = getCollectionListListener;
    }

    public void getProductCollectionList(int type, final boolean isRefresh) {
        HashMap<String, Object> params = new HashMap<>(7);
        HashMap<String, String> conditionJson = new HashMap<>(3);


        conditionJson.put("Type", String.valueOf(type));
        params.put("Mobile", UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());

        params.put("rows", "10");

        if (isRefresh) {
            page = 1;
        } else {
            ++page;
        }
        params.put("page", String.valueOf(page));
        params.put("sidx", "");
        params.put("sord", "");
        params.put("conditionJson", JSON.toJSONString(conditionJson));
        OKHttpManager.postString(getUrl(R.string.GetCollection), params, new CommonCallBack<ProductCollectionListBean>() {
            @Override
            protected void onSuccess(ProductCollectionListBean data) {
                if (data.getType() == 1) {
                    mGetCollectionListListener.getProductCollectionSuccess(data.getResultdata(), isRefresh);
                } else {
                    mGetCollectionListListener.getCollectionField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }

//    public void getCollectionList(final boolean isRefresh) {
//        HashMap<String, Object> params = new HashMap<>(7);
//        HashMap<String, String> conditionJson = new HashMap<>(3);
//
//
//        conditionJson.put("Type", String.valueOf(1));
//
//        params.put("rows", "10");
//
//        if (isRefresh) {
//            page = 1;
//        } else {
//            ++page;
//        }
//        params.put("page", String.valueOf(page));
//        params.put("sidx", "");
//        params.put("sord", "");
//        params.put("conditionJson", JSON.toJSONString(conditionJson));
//        OKHttpManager.postString(getUrl(R.string.GetCollection), params, new CommonCallBack<OtherCollectionListBean>() {
//            @Override
//            protected void onSuccess(OtherCollectionListBean data) {
//                if (data.getType() == 1) {
//                    mGetCollectionListListener.getOtherCollectionSuccess(data.getResultdata(), isRefresh);
//                } else {
//                    mGetCollectionListListener.getCollectionField();
//                    ToastUtils.showToast(data.getMessage());
//                }
//            }
//        });
//    }


    public interface GetCollectionListListener {
        void getProductCollectionSuccess(List<ProductCollectionListBean.ResultdataBean> beans, boolean isRefresh);

//        void getOtherCollectionSuccess(List<OtherCollectionListBean.ResultdataBean> beans, boolean isRefresh);

        void getCollectionField();
    }

}
