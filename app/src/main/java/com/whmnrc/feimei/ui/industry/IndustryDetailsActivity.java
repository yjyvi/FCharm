package com.whmnrc.feimei.ui.industry;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.mine.PayActivity;
import com.whmnrc.feimei.ui.mine.PayVipActivity;
import com.whmnrc.feimei.pop.PopAppreciate;
import com.whmnrc.feimei.pop.PopShare;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/8/2.
 */

public class IndustryDetailsActivity extends BaseActivity {
    @BindView(R.id.web_content)
    WebView mWebContent;
    public PopShare mPopShare;
    public PopAppreciate mPopAppreciate;

    @Override
    protected void initViewData() {
        setTitle("行业资源");
        rightVisible(R.mipmap.icon_share);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_industry_details;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, IndustryDetailsActivity.class);
        context.startActivity(starter);
    }


    @OnClick({R.id.rl_right, R.id.iv_zan, R.id.tv_join_vip})
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.rl_right:
                if (mPopShare == null) {
                    mPopShare = new PopShare(IndustryDetailsActivity.this, "1", "1", "1", "1");
                }
                mPopShare.show();
                break;
            case R.id.iv_zan:
                if (mPopAppreciate == null) {
                    mPopAppreciate = new PopAppreciate(IndustryDetailsActivity.this);
                }
                mPopAppreciate.show();
                mPopAppreciate.setPopHintListener(() -> PayActivity.start(view.getContext(),PayActivity.ONE_PAY,"20"));
                break;
            case R.id.tv_join_vip:
                PayVipActivity.start(view.getContext());
                break;
            default:
                break;
        }
    }
}
