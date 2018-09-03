package com.whmnrc.feimei.ui.organization;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.OrganizationDetailsBean;
import com.whmnrc.feimei.ui.BaseActivity;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class AboutInfoActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_tel)
    TextView mTvTel;
    @BindView(R.id.tv_qq)
    TextView mTvQq;
    @BindView(R.id.tv_mail)
    TextView mTvMail;
    @BindView(R.id.tv_address)
    TextView mTvAddress;

    @Override
    protected void initViewData() {
        setTitle("联系我们");
        OrganizationDetailsBean.ResultdataBean.EnterpriseBean enterprise = getIntent().getParcelableExtra("enterprise");
        if (enterprise != null) {
            mTvName.setText(enterprise.getName());
            mTvTel.setText(String.format("电话：%s",enterprise.getPhone()));
            mTvQq.setText(String.format("官网：%s",enterprise.getQQ()));
            mTvMail.setText(String.format("邮箱：%s",enterprise.getMail()));
            mTvAddress.setText(enterprise.getAddress());
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_about_info;
    }

    public static void start(Context context, OrganizationDetailsBean.ResultdataBean.EnterpriseBean enterprise) {
        Intent starter = new Intent(context, AboutInfoActivity.class);
        starter.putExtra("enterprise",  enterprise);
        context.startActivity(starter);
    }


}
