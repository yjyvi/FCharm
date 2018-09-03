package com.whmnrc.feimei.ui.login;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.whmnrc.feimei.CommonConstant;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.UserBean;
import com.whmnrc.feimei.presener.GetCodePresenter;
import com.whmnrc.feimei.presener.LoginPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.utils.CodeTimeUtils;
import com.whmnrc.feimei.utils.IsShowPwdUtils;
import com.whmnrc.feimei.utils.SPUtils;
import com.whmnrc.feimei.utils.ToastUtils;
import com.whmnrc.feimei.utils.evntBusBean.UserInfoEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/01/31
 *     desc   : 邮箱登录
 *     version: 1.0
 * </pre>
 *
 * @author yjyvi
 */
public class LoginActivity extends BaseActivity implements LoginPresenter.LoginListener, GetCodePresenter.GetCodeListener {


    @BindView(R.id.et_phone_number)
    EditText mEtPhoneNumber;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;

    @BindView(R.id.v_line1)
    View mVLine1;
    @BindView(R.id.v_line2)
    View mVLine2;
    @BindView(R.id.ll_account_login)
    LinearLayout mLlAccountLogin;
    @BindView(R.id.ll_quick_login)
    LinearLayout mLlQuickLogin;
    @BindView(R.id.iv_hint_pwd)
    ImageView mIvHintPwd;
    @BindView(R.id.tv_get_code)
    TextView mTvGetCode;
    @BindView(R.id.tv_quick_login_hint)
    TextView mTvQuickLoginHint;
    @BindView(R.id.ll_account_login_more)
    LinearLayout mLlAccountLoginMore;

    private boolean mIsShow;
    public LoginPresenter mEmailLoginPresenter;
    public boolean mIsExit;
    private GetCodePresenter mGetCodePresenter;
    public LoginPresenter mLoginPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initViewData() {
        mGetCodePresenter = new GetCodePresenter(this);
        mLoginPresenter = new LoginPresenter(this);
        EventBus.getDefault().register(this);

        setTitle("登录");
        mIsExit = getIntent().getBooleanExtra("isExit", false);
        mEmailLoginPresenter = new LoginPresenter(this);

//        mEtPhoneNumber.setText("13554542559");
//        mEtPwd.setText("123456");

        isAccountLogin(true);
    }

    /**
     * 输入提示
     *
     * @return
     */
    private boolean inputVerification() {

        if (TextUtils.isEmpty(mEtPhoneNumber.getText().toString().trim())) {
            ToastUtils.showToast(getResources().getString(R.string.input_tel));
            return false;
        }

        if (mLlAccountLogin.isSelected() && TextUtils.isEmpty(mEtPwd.getText().toString().trim())) {
            ToastUtils.showToast(getResources().getString(R.string.input_password));
            return false;
        }

        if (mLlQuickLogin.isSelected() && TextUtils.isEmpty(mEtPwd.getText().toString().trim())) {
            ToastUtils.showToast("请输入验证码");
            return false;
        }


        return true;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    public static void start(Context context, boolean isExit) {
        Intent starter = new Intent(context, LoginActivity.class);
        starter.putExtra("isExit", isExit);
        context.startActivity(starter);
    }

    @Override
    public void loginSuccess(UserBean.ResultdataBean userBean) {
        SPUtils.put(this, CommonConstant.Common.LAST_LOGIN_ID, userBean.getMobile());
        UserManager.saveUser(userBean);
        isShowDialog(false);
        finish();
        EventBus.getDefault().post(new UserInfoEvent().setEventType(UserInfoEvent.UPDATE_USER_INFO));
    }

    @Override
    public void loginField() {
        isShowDialog(false);
    }

    @Subscribe
    public void userUpdateEvent(UserInfoEvent userInfoEvent) {
        if (userInfoEvent.getEventType() == UserInfoEvent.UPDATE_USER_INFO) {
            finish();
        }
    }


    @OnClick({R.id.ll_account_login, R.id.ll_quick_login, R.id.iv_hint_pwd, R.id.tv_get_code, R.id.ll_commit, R.id.tv_register, R.id.tv_find_pwd, R.id.iv_wx_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_account_login:
                isAccountLogin(true);
                IsShowPwdUtils.showPwd(false, mEtPwd);
                mIvHintPwd.setSelected(false);
                break;
            case R.id.ll_quick_login:
                IsShowPwdUtils.showPwd(true, mEtPwd);
                isAccountLogin(false);
                break;
            case R.id.iv_hint_pwd:
                IsShowPwdUtils.showPwd(!mIvHintPwd.isSelected(), mEtPwd);
                if (!mIvHintPwd.isSelected()) {
                    mIvHintPwd.setSelected(true);
                } else {
                    mIvHintPwd.setSelected(false);
                }
                break;
            case R.id.tv_get_code:
                String tel = mEtPhoneNumber.getText().toString().trim();
                if (TextUtils.isEmpty(tel)) {
                    ToastUtils.showToast("请输入手机号码");
                    return;
                }
                isShowDialog(true);
                mGetCodePresenter.getCode(CommonConstant.Common.GET_CODE_TYPE, tel);
                break;
            case R.id.ll_commit:
                if (inputVerification()) {
                    if (mLlAccountLogin.isSelected()) {
                        mLoginPresenter.login(mEtPwd.getText().toString().trim(), "", mEtPhoneNumber.getText().toString().trim());
                    } else {
                        mLoginPresenter.login("", mEtPwd.getText().toString().trim(), mEtPhoneNumber.getText().toString().trim());
                    }
                    isShowDialog(true);
                }
                break;
            case R.id.tv_register:
                RegisterActivity.start(view.getContext());
                break;
            case R.id.tv_find_pwd:
                FindPwdActivity.start(view.getContext(), "");
                break;
            case R.id.iv_wx_login:
                setWXLogin();
                break;
            default:
                break;
        }
    }

    private void isAccountLogin(boolean isAccountLogin) {
        mVLine1.setBackgroundResource(isAccountLogin ? R.color.normal_blue_text_color : 0);
        mLlAccountLogin.setSelected(isAccountLogin);
        mTvGetCode.setVisibility(isAccountLogin ? View.GONE : View.VISIBLE);
        mIvHintPwd.setVisibility(!isAccountLogin ? View.GONE : View.VISIBLE);

        mVLine2.setBackgroundResource(!isAccountLogin ? R.color.normal_blue_text_color : 0);
        mLlQuickLogin.setSelected(!isAccountLogin);
        mTvQuickLoginHint.setVisibility(isAccountLogin ? View.GONE : View.VISIBLE);
        mLlAccountLoginMore.setVisibility(!isAccountLogin ? View.GONE : View.VISIBLE);

    }

    @Override
    public void getCodeSuccess() {
        CodeTimeUtils.countDown(mTvGetCode);
        isShowDialog(false);
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
