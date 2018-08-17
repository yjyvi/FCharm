package com.whmnrc.feimei.presener;

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

public class DownloadFilePresenter extends PresenterBase {

    private DownloadFileListener mDownloadFileListener;

    public DownloadFilePresenter(DownloadFileListener downloadFileListener) {
        this.mDownloadFileListener = downloadFileListener;

    }

    /**
     * @param type      0-规格书  1-文库
     * @param productId  下载的文件ID
     */
    public void downloadFileReq(int type, String productId) {
        HashMap<String, Object> params = new HashMap<>(4);
        params.put("Type", type);
        params.put("OtherID", productId);
        OKHttpManager.postString(getUrl(R.string.DownloadNumber), params, new CommonCallBack<BaseBean>() {
            @Override
            protected void onSuccess(BaseBean data) {
                if (data.getType() == 1) {
                    mDownloadFileListener.downloadFileSuccess();
                } else {
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface DownloadFileListener {
        void downloadFileSuccess();
    }

}
