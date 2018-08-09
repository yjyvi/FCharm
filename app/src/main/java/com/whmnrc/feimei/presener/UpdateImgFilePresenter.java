package com.whmnrc.feimei.presener;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.luck.picture.lib.entity.LocalMedia;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.BaseBean;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.List;

import okhttp3.Call;

/**
 * @author yjyvi
 * @data 2018/5/30.
 * 上传头像
 */

public class UpdateImgFilePresenter extends PresenterBase {

    private UpdateHeadImgListener mUpdateHeadImgListener;

    public UpdateImgFilePresenter(UpdateHeadImgListener updateUpdateHeadImgListener) {
        this.mUpdateHeadImgListener = updateUpdateHeadImgListener;
    }



    public void uploadImg(final List<LocalMedia> datas, final int position) {

        if (OKHttpManager.getIsConnected()) {
            return;
        }

        if (datas.size() <= position) {
            return;
        }

        final File file = new File(datas.get(position).getPath());
        OkHttpUtils.post()
                .addFile("Image", file.getName(), file)
                .url(getUrl(R.string.UploadImg))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                BaseBean<String> netBaseBean = JSON.parseObject(response, BaseBean.class);
                if (netBaseBean.getType() == 1) {
                    String resultdata = netBaseBean.getResultdata();
                    Log.e("UpdateImgFilePresenter", resultdata);
                    mUpdateHeadImgListener.loadSuccess(resultdata, datas, position);
                } else {
                    ToastUtils.showToast(netBaseBean.getMessage());
                }
            }
        });
    }


    public interface UpdateHeadImgListener {
        void loadSuccess(String resultImgUrl, List<LocalMedia> datas, int position);
    }
}
