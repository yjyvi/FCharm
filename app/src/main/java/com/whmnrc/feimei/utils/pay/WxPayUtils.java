package com.whmnrc.feimei.utils.pay;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.whmnrc.feimei.CommonConstant;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;


/**
 * 作者:linzheng 日期:2017/2/9 功能:微信支付具体实现逻辑
 */

public class WxPayUtils {

    public static void wxPay(Context context,String partnerId,String prepayId,String packageValue,String nonceStr,String timeStamp,String sign){
        IWXAPI api = WXAPIFactory.createWXAPI(context, CommonConstant.Common.WX_APP_ID);
        api.registerApp(CommonConstant.Common.WX_APP_ID);
        PayReq req = new PayReq();
        req.appId = CommonConstant.Common.WX_APP_ID;
        req.partnerId = partnerId;
        req.prepayId = prepayId;
        req.packageValue = packageValue;
        req.nonceStr = nonceStr;
        req.timeStamp = timeStamp;
        req.sign = sign;
        api.sendReq(req);

    }

    /**
     * 判断当前手机是否安装微信
     * @param context
     * @return
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }


}
