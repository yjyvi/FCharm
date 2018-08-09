package com.whmnrc.feimei.ui.login;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.whmnrc.feimei.CommonConstant;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.UserBean;
import com.whmnrc.feimei.presener.GetCodePresenter;
import com.whmnrc.feimei.presener.RegisterPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.utils.CodeTimeUtils;
import com.whmnrc.feimei.utils.KeyboardUtils;
import com.whmnrc.feimei.utils.PhoneUtils;
import com.whmnrc.feimei.utils.SPUtils;
import com.whmnrc.feimei.utils.ToastUtils;
import com.whmnrc.feimei.utils.evntBusBean.UserInfoEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class RegisterActivity extends BaseActivity implements RegisterPresenter.RegisterListener, GetCodePresenter.GetCodeListener {
    @BindView(R.id.et_phone_number)
    EditText mEtPhoneNumber;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.tv_get_code)
    TextView mTvGetCode;
    public RegisterPresenter mRegisterPresenter;
    public GetCodePresenter mGetCodePresenter;

    @Override
    protected void initViewData() {

        mGetCodePresenter = new GetCodePresenter(this);
        mRegisterPresenter = new RegisterPresenter(this);
        setTitle("注册");
    }

    @Override
    protected int setLayoutId() {

        return R.layout.activity_register;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, RegisterActivity.class);
        context.startActivity(starter);
    }

    /**
     * 输入提示
     *
     * @return
     */
    private boolean inputVerification() {

        if (TextUtils.isEmpty(mEtPhoneNumber.getText().toString().trim())) {
            ToastUtils.showToast("请输入手机号");
            return false;
        }

        if (!PhoneUtils.isMobileNO(mEtPhoneNumber.getText().toString().trim())) {
            ToastUtils.showToast("请输入手机号");
            return false;
        }
        if (TextUtils.isEmpty(mEtCode.getText().toString().trim())) {
            ToastUtils.showToast("请输入验证码");
            return false;
        }

        if (TextUtils.isEmpty(mEtPwd.getText().toString().trim())) {
            ToastUtils.showToast("请输入密码");
            return false;
        }


        return true;
    }


    @Override
    public void registerSuccess(UserBean.ResultdataBean userBean) {

        SPUtils.put(this, CommonConstant.Common.LAST_LOGIN_ID, userBean.getMobile());
        UserManager.saveUser(userBean);
        isShowDialog(false);
        finish();
        EventBus.getDefault().post(new UserInfoEvent().setEventType(UserInfoEvent.UPDATE_USER_INFO));
    }

    @Override
    public void registerField() {
        isShowDialog(false);
    }


    @OnClick({R.id.tv_get_code, R.id.ll_commit, R.id.iv_wx_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_code:
                if (TextUtils.isEmpty(mEtPhoneNumber.getText().toString().trim())) {
                    ToastUtils.showToast("请输入手机号码");
                    return;
                }
                KeyboardUtils.hideKeyBoard(view.getContext(), view);
                isShowDialog(true);
                mGetCodePresenter.getCode(CommonConstant.Common.GET_CODE_TYPE, mEtPhoneNumber.getText().toString().trim());
                break;
            case R.id.ll_commit:
                if (inputVerification()) {
                    isShowDialog(true);
                    KeyboardUtils.hideKeyBoard(view.getContext(), view);
                    mRegisterPresenter.register(mEtPwd.getText().toString().trim(), mEtCode.getText().toString().trim(), "", mEtPhoneNumber.getText().toString().trim());
                }
                break;
            case R.id.iv_wx_login:
                setWXLogin();
                break;
            default:
                break;
        }
    }

    @Override
    public void getCodeSuccess() {
        isShowDialog(false);
        CodeTimeUtils.countDown(mTvGetCode);
    }

    @Override
    public void getCodeField() {
        isShowDialog(false);
    }

    /**
     * 微信登录
     */
    private void setWXLogin() {
        IWXAPI api = WXAPIFactory.createWXAPI(this, CommonConstant.Common.WX_APP_ID, true);
        api.registerApp(CommonConstant.Common.WX_APP_ID);
        if (api.isWXAppInstalled()) {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);
        } else {
            ToastUtils.showToast("您尚未安装微信");
        }
    }
}
