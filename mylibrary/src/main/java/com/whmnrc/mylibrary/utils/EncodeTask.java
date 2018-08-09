package com.whmnrc.mylibrary.utils;

import android.os.AsyncTask;

/**
 * Created by yong hao zeng on 2017/12/7.
 */

public class EncodeTask extends AsyncTask<String,Integer,String> {
    private onResponse onResponse;
    public EncodeTask(onResponse response) {
        this.onResponse = response;
    }

    @Override
    protected String doInBackground(String... strings) {
        String base64 = EncodUtils.getBase64(strings[0]);
        return base64;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        onResponse.onResult(s);
    }

    public interface onResponse{
        void onResult(String base64);

    }
}
