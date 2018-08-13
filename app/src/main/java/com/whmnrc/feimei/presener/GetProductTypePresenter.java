package com.whmnrc.feimei.presener;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.ProductTypeBean;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class GetProductTypePresenter extends PresenterBase {

    private GetProductTypeListener mGetProductTypeListener;

    public GetProductTypePresenter(GetProductTypeListener getProductTypeListener) {
        this.mGetProductTypeListener = getProductTypeListener;

    }

    public void getProductType() {
        HashMap<String, Object> params = new HashMap<>(2);
        OKHttpManager.postString(getUrl(R.string.GetCommodityClass), params, new OKHttpManager.ObjectCallback() {
            @Override
            public void onSuccess(String st) {
                ProductTypeBean productTypeBean = JSON.parseObject(st, ProductTypeBean.class);
                if (productTypeBean.getType() == 1) {
                    mGetProductTypeListener.getProductTypeSuccess(productTypeBean.getResultdata());
                } else {
                    mGetProductTypeListener.getProductTypeField();
                    ToastUtils.showToast(productTypeBean.getMessage());
                }
            }

            @Override
            public void onFailure(int code, String errorMsg) {
                mGetProductTypeListener.getProductTypeField();
            }


        });
    }


    public interface GetProductTypeListener {
        void getProductTypeSuccess(List<ProductTypeBean.ResultdataBean> bean);

        void getProductTypeField();
    }

}
