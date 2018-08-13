package com.whmnrc.feimei;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.android.otherPush.StubAppUtils;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.whmnrc.feimei.utils.CrashHandler;

/**
 * @author yjyvi
 */
public class MyApplication extends MultiDexApplication {

    /**
     * 全局context
     */
    public static Context applicationContext;


    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色
            ClassicsHeader classicsHeader = new ClassicsHeader(context);
            return classicsHeader;
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            ClassicsFooter classicsFooter = new ClassicsFooter(context);
            return classicsFooter;
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //-----------初始化applicationContext----------------
        applicationContext = getApplicationContext();


        //-----------内存泄漏检查工具初始化----------------
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        LeakCanary.install(this);

        //-----------全局捕获异常日志----------------
        if (!BuildConfig.DEBUG) {
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(getApplicationContext());
        }

        initXGPush();
    }


    /**
     * 信鸽推送
     */
    private void initXGPush() {
        XGPushConfig.enableDebug(this,true);
        StubAppUtils.attachBaseContext(this);

        XGPushConfig.enableOtherPush(getApplicationContext(), true);
        XGPushConfig.setHuaweiDebug(true);
        XGPushConfig.setMiPushAppId(getApplicationContext(), "APPID");
        XGPushConfig.setMiPushAppKey(getApplicationContext(), "APPKEY");
        XGPushConfig.setMzPushAppId(this, "APPID");
        XGPushConfig.setMzPushAppKey(this, "APPKEY");

        XGPushManager.registerPush(this, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                //token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：" + data);
            }
            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
    }


}
