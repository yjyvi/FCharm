package com.whmnrc.feimei.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import com.allenliu.versionchecklib.callback.APKDownloadListener;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.whmnrc.feimei.MyApplication;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.VersionBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.pop.PopUtils;

import java.io.File;
import java.util.TreeMap;


/**
 * @author yjyvi
 * @data 2018/7/16.
 */

public class VersionUtils {


    public static String getVersionName(Activity activity) {
        PackageManager packageManager = activity.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(activity.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    /**
     * 显示下载弹窗
     *
     * @param activity
     */
    public static void showDownloadPop(final Activity activity) {

        String url = MyApplication.applicationContext.getResources().getString(R.string.service_host_address) + MyApplication.applicationContext.getResources().getString(R.string.GetVersion);
        OKHttpManager.postString(url, new TreeMap<>(), new CommonCallBack<VersionBean>() {
            @Override
            protected void onSuccess(VersionBean data) {
                final VersionBean.ResultdataBean resultdata = data.getResultdata();
                if (resultdata == null) {
                    return;
                }
                if (TextUtils.equals(getVersionName(activity), resultdata.getAndroidVersion())) {
                    return;
                }
                PopUtils.showVersionNotify(activity, resultdata.getExplain(), "确定", new PopUtils.NormalNotifyPopListener() {
                    @Override
                    public void commitClick() {
                        //下载文件
                        String filePath = Environment.getExternalStoragePublicDirectory(Environment.
                                DIRECTORY_DOWNLOADS) + "/FeiMeiDownload/";
                        String url = resultdata.getURL();


                        if (!url.contains("http")) {
                            ToastUtils.showToast("下载链接错误");
                        } else {
                            AllenVersionChecker
                                    .getInstance()
                                    .downloadOnly(
                                            UIData.create().setDownloadUrl(url).setTitle("版本更新！").setContent(resultdata.getExplain())
                                    ).setDownloadAPKPath(filePath).setApkDownloadListener(new APKDownloadListener() {
                                @Override
                                public void onDownloading(int progress) {
//
                                }

                                @Override
                                public void onDownloadSuccess(File file) {
                                    openFile(file, activity);
                                }

                                @Override
                                public void onDownloadFail() {
                                    ToastUtils.showToast("下载错误");
                                }
                            }).excuteMission(activity);
                        }
                    }

                    @Override
                    public void dissmiss() {
                        AllenVersionChecker .getInstance().cancelAllMission(activity);
                    }
                });
            }
        });
    }


    //打开APK程序代码

    private static void openFile(File file, Context var1) {



        Intent var2 = new Intent();
        var2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        var2.setAction(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri uriForFile = FileProvider.getUriForFile(var1.getApplicationContext(), MyApplication.applicationContext.getPackageName() + ".provider", file);
            var2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            var2.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        } else {
            var2.setDataAndType(Uri.fromFile(file), getMIMEType(file));
        }
        try {
            var1.startActivity(var2);
        } catch (Exception var5) {
            var5.printStackTrace();
            ToastUtils.showToast("没有找到打开此类文件的程序");
        }
    }

    /**
     * Determine the type of file
     *
     * @param f
     * @return
     */
    public static String getMIMEType(File f) {
        String type = "";
        String fName = f.getName();

        String end = fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toLowerCase();
        type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(end);
        return type;
    }
}
