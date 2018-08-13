package com.whmnrc.feimei.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.whmnrc.feimei.CommonConstant;
import com.whmnrc.feimei.presener.IsWeChatLoginPresenter;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.login.BindTelActivity;
import com.whmnrc.feimei.utils.SPUtils;
import com.whmnrc.feimei.utils.ToastUtils;
import com.whmnrc.feimei.utils.evntBusBean.UserInfoEvent;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;

/**
 * @author yjyvi
 * @data 2018/3/13.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IWXAPI api = WXAPIFactory.createWXAPI(this, CommonConstant.Common.WX_APP_ID, true);
        api.handleIntent(getIntent(), this);
        finish();
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    SendAuth.Resp resp = (SendAuth.Resp) baseResp;
                    String code = resp.code;
                    getAccessToken(code);
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    break;
                default:
                    break;
            }
        } else {
            String result;
            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    result = "分享成功";
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    result = null;
                    break;
                default:
                    result = "分享失败";
                    break;
            }
            if (result != null) {
                ToastUtils.showToast(result);
            }
        }
    }


    /**
     * 获取Token
     *
     * @param code
     */
    private void getAccessToken(String code) {
        String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                .concat(CommonConstant.Common.WX_APP_ID)
                .concat("&secret=")
                .concat(CommonConstant.Common.WX_SECRET)
                .concat("&code=")
                .concat(code)
                .concat("&grant_type=authorization_code");

        OkHttpUtils.get().url(accessTokenUrl).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("WXEntryActivity", "getAccessToken=" + response);
                JSONObject jsonObject = JSON.parseObject(String.valueOf(response));
                String openid = jsonObject.getString("openid");
                String accessToken = jsonObject.getString("access_token");

                //验证是否微信登录过
                getIsWeChatBind(openid);
            }
        });


    }


    private void getIsWeChatBind(final String openId) {
        IsWeChatLoginPresenter isWeChatLoginPresenter = new IsWeChatLoginPresenter(userBean -> {
            if (userBean == null) {
                BindTelActivity.start(WXEntryActivity.this, openId);
            } else {
                SPUtils.put(WXEntryActivity.this, CommonConstant.Common.LAST_LOGIN_ID, userBean.getMobile());
                UserManager.saveUser(userBean);
                EventBus.getDefault().post(new UserInfoEvent().setEventType(UserInfoEvent.UPDATE_USER_INFO));
                finish();
            }
        });
        isWeChatLoginPresenter.isWeChatLogin(openId);
    }


}
