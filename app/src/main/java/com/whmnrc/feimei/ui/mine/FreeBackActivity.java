package com.whmnrc.feimei.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.presener.FreeBackPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/25.
 */

public class FreeBackActivity extends BaseActivity implements FreeBackPresenter.FreeBackListener {
    @BindView(R.id.et_content)
    EditText mEtContent;
    public FreeBackPresenter mFreeBackPresenter;

    @Override
    protected void initViewData() {
        setTitle("建议反馈");

        mFreeBackPresenter = new FreeBackPresenter(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_free_back;
    }


    public static void start(Context context) {
        Intent starter = new Intent(context, FreeBackActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void freeBackSuccess() {
        finish();
    }


    @OnClick(R.id.ll_commit)
    public void onClick() {
        String content = mEtContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.showToast("请输入反馈内容");
            return;
        }
        mFreeBackPresenter.getMsgList(content);
    }
}
