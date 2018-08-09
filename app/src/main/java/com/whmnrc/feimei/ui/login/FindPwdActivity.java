package com.whmnrc.feimei.ui.login;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.whmnrc.feimei.CommonConstant;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.presener.FindPwdPresenter;
import com.whmnrc.feimei.presener.GetCodePresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.utils.CodeTimeUtils;
import com.whmnrc.feimei.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class FindPwdActivity extends BaseActivity implements GetCodePresenter.GetCodeListener, FindPwdPresenter.FindPwdListener {
    @BindView(R.id.et_phone_number)
    EditText mEtPhoneNumber;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.bt_get_code)
    TextView mBtGetCode;
    private GetCodePresenter mGetCodePresenter;
    public FindPwdPresenter mFindPwdPresenter;

    @Override
    protected void initViewData() {
        String title = getIntent().getStringExtra("title");
        if (title != null) {
            setTitle(title);
        } else {
            setTitle("找回密码");
        }

        mGetCodePresenter = new GetCodePresenter(this);
        mFindPwdPresenter = new FindPwdPresenter(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_find_pwd;
    }

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, FindPwdActivity.class);
        starter.putExtra("title", title);
        context.startActivity(starter);
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

        if (TextUtils.isEmpty(mEtPwd.getText().toString().trim())) {
            ToastUtils.showToast("请输入新密码");
            return false;
        }

        if (TextUtils.isEmpty(mEtCode.getText().toString().trim())) {
            ToastUtils.showToast("请输入验证码");
            return false;
        }


        return true;
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
                    mFindPwdPresenter.findPwd(mEtPwd.getText().toString().trim(), mEtCode.getText().toString().trim(), mEtPhoneNumber.getText().toString().trim());
                }
                break;
            default:
                break;
        }
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
    public void findPwdSuccess() {
        isShowDialog(false);
        finish();
    }

    @Override
    public void findPwdField() {
        isShowDialog(false);
    }
}
