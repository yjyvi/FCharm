package com.whmnrc.feimei.ui.login;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.whmnrc.feimei.CommonConstant;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.UserBean;
import com.whmnrc.feimei.presener.GetCodePresenter;
import com.whmnrc.feimei.presener.IsWeChatLoginPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.utils.CodeTimeUtils;
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

public class BindTelActivity extends BaseActivity implements GetCodePresenter.GetCodeListener, IsWeChatLoginPresenter.IsWeChatLoginListener {

    public String mOpenid;
    @BindView(R.id.et_phone_number)
    EditText mEtPhoneNumber;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.bt_get_code)
    TextView mBtGetCode;
    private GetCodePresenter mGetCodePresenter;
    public IsWeChatLoginPresenter mIsWeChatLoginPresenter;

    @Override
    protected void initViewData() {
        setTitle("绑定手机号");
        mGetCodePresenter = new GetCodePresenter(this);
        mIsWeChatLoginPresenter = new IsWeChatLoginPresenter(this);
        mOpenid = getIntent().getStringExtra("openid");
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_bind_tel;
    }


    public static void start(Context context, String openid) {
        Intent starter = new Intent(context, BindTelActivity.class);
        starter.putExtra("openid", openid);
        context.startActivity(starter);
    }


    @OnClick({R.id.bt_get_code, R.id.ll_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get_code:

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
                    isShowDialog(true);
                    mIsWeChatLoginPresenter.weChatLogin(mOpenid, mEtCode.getText().toString().trim(), mEtPhoneNumber.getText().toString().trim());
                }
                break;
            default:
                break;
        }
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

        if (TextUtils.isEmpty(mEtCode.getText().toString().trim())) {
            ToastUtils.showToast("请输入验证码");
            return false;
        }


        return true;
    }

    @Override
    public void getCodeSuccess() {
        CodeTimeUtils.countDown(mBtGetCode);
        isShowDialog(false);
    }

    @Override
    public void getCodeField() {
        isShowDialog(false);
    }

    @Override
    public void isWeChat(UserBean.ResultdataBean userBean) {
        SPUtils.put(this, CommonConstant.Common.LAST_LOGIN_ID, userBean.getMobile());
        UserManager.saveUser(userBean);
        isShowDialog(false);
        finish();
        EventBus.getDefault().post(new UserInfoEvent().setEventType(UserInfoEvent.UPDATE_USER_INFO));
    }

}
