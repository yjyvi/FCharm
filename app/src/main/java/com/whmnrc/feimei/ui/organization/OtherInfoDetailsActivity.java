package com.whmnrc.feimei.ui.organization;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.ui.BaseActivity;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class OtherInfoDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_content)
    TextView mTvContent;

    @Override
    protected void initViewData() {
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        setTitle(title);
        mTvContent.setText(content);

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_other_details;
    }


    public static void start(Context context, String title, String content) {
        Intent starter = new Intent(context, OtherInfoDetailsActivity.class);
        starter.putExtra("title", title);
        starter.putExtra("content", content);
        context.startActivity(starter);
    }
}
