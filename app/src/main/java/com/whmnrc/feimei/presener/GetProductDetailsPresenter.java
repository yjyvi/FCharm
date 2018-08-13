package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.ProductDetailsBean;
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

public class GetProductDetailsPresenter extends PresenterBase {

    private GetProductDetailsListener mGetProductDetailsListener;

    public GetProductDetailsPresenter(GetProductDetailsListener getProductTypeListener) {
        this.mGetProductDetailsListener = getProductTypeListener;

    }

    public void getProductDetails(String productId) {
        HashMap<String, Object> params = new HashMap<>(4);
        params.put("Mobile", UserManager.getUser()==null ?"":UserManager.getUser().getMobile());
        params.put("OtherID",productId);
        OKHttpManager.postString(getUrl(R.string.GetCommodityDetails), params, new CommonCallBack<ProductDetailsBean>() {
            @Override
            protected void onSuccess(ProductDetailsBean data) {
                if (data.getType() == 1) {
                    mGetProductDetailsListener.getProductDetailsSuccess(data.getResultdata());
                } else {
                    mGetProductDetailsListener.getProductDetailsField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetProductDetailsListener {
        void getProductDetailsSuccess(ProductDetailsBean.ResultdataBean bean);

        void getProductDetailsField();
    }

}
