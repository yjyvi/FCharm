package com.whmnrc.feimei.presener;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.ProductListBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class GetProductListPresenter extends PresenterBase {

    private GetProductListListener mGetProductTypeListener;
    private int page = 0;
    private String sidx = "Sort";
    private String desc = "desc";


    public GetProductListPresenter(GetProductListListener getProductListListener) {
        this.mGetProductTypeListener = getProductListListener;

    }

    public void getProductList(String name, String commodityClassID) {
        getProductList(true, "Sort", name, commodityClassID, "");
    }

    public void getProductList(boolean isRefresh, String sort, String name, String commodityClassID, String desc) {
        HashMap<String, Object> params = new HashMap<>(7);
        HashMap<String, String> conditionJson = new HashMap<>(3);

        if (!TextUtils.isEmpty(name)) {
            conditionJson.put("Name", name);
        }

        if (!TextUtils.isEmpty(sort)) {
            this.sidx = sort;
        }
        if (!TextUtils.isEmpty(desc)) {
            this.desc = desc;
        }

        if (!TextUtils.isEmpty(commodityClassID)) {
            conditionJson.put("CommodityClassID", commodityClassID);
        }

        params.put("rows", "10");
        if (isRefresh) {
            page = 1;
        } else {
            ++page;
        }
        params.put("page", String.valueOf(page));
        params.put("sidx", sort);
        params.put("sord", desc);
        params.put("conditionJson", JSON.toJSONString(conditionJson));
        OKHttpManager.postString(getUrl(R.string.GetCommodity), params, new CommonCallBack<ProductListBean>() {
            @Override
            protected void onSuccess(ProductListBean data) {
                if (data.getType() == 1) {
                    mGetProductTypeListener.getProductListSuccess(data.getResultdata(), isRefresh);
                } else {
                    mGetProductTypeListener.getProductListField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetProductListListener {
        void getProductListSuccess(ProductListBean.ResultdataBean bean, boolean isRefresh);

        void getProductListField();
    }

}
