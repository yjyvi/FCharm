package com.whmnrc.feimei.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.utils.EmptyListUtils;
import com.whmnrc.feimei.utils.StatusBarUtils;
import com.whmnrc.feimei.utils.ToastUtils;
import com.whmnrc.feimei.views.LoadingDialog;

import butterknife.ButterKnife;

/**
 * 描述：自定义Activity
 *
 * @author wangjian
 */
public abstract class BaseActivity extends AppCompatActivity {

    private long exitTime = 0;

    /**
     * 描述：返回
     */
    protected void back() {
        finish();
    }


    /**
     * 描述：准备数据
     */
    protected abstract void initViewData();

    /**
     * 描述：创建
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //禁止截屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(setLayoutId());
        setTranslucentStatus();
        ButterKnife.bind(this);
        View back = findViewById(R.id.common_title_back);
        if (back != null) {
            back.setOnClickListener(v -> back());
        }
        initViewData();


    }


    protected abstract int setLayoutId();


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 设置透明状态栏
     */
    protected void setTranslucentStatus() {

        StatusBarUtils.transparencyBar(this);

        StatusBarUtils.MIUISetStatusBarLightMode(this, true);
        StatusBarUtils.FlymeSetStatusBarLightMode(getWindow(), true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            getWindow().setStatusBarColor(Color.TRANSPARENT);


            //改变状态栏文字为黑色
            View decor = getWindow().getDecorView();
            int ui = decor.getSystemUiVisibility();
            if (true) {
                ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decor.setSystemUiVisibility(ui);
        }
    }

    /**
     * 描述：退出程序
     */
    protected void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtils.showToast("再按一次将退出 ".concat(getResources().getString(R.string.app_name)));
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    /**
     * 描述：设置标题
     *
     * @param text 标题
     */
    protected void setTitle(String text) {
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        if (tv_title != null) {
            tv_title.setVisibility(View.VISIBLE);
            tv_title.setText(text);
        }
    }


    /**
     * 描述:隐藏返回按钮
     */
    protected void backGone() {
        RelativeLayout back = (RelativeLayout) findViewById(R.id.common_title_back);
        if (back != null) {
            back.setVisibility(View.GONE);
        }
    }


    /**
     * 描述:显示右菜单全部
     */
    protected void rightVisible(String title, int drawable) {
        rightVisible(title);
        rightVisible(drawable);
    }

    /**
     * 描述:显示左菜单图片
     */
    protected void leftVisible(int drawable) {
        RelativeLayout ll_left = (RelativeLayout) findViewById(R.id.common_title_back);
        if (ll_left != null) {
            ll_left.setVisibility(View.VISIBLE);
        }
        ImageView iv_left = (ImageView) findViewById(R.id.iv_back);
        if (iv_left != null) {
            iv_left.setVisibility(View.VISIBLE);
            iv_left.setImageResource(drawable);
        }
    }

    /**
     * 描述:显示右菜单文字
     */
    protected void rightVisible(String title) {
        RelativeLayout rl_right = (RelativeLayout) findViewById(R.id.rl_right_title);
        if (rl_right != null) {
            rl_right.setVisibility(View.VISIBLE);
        }
        TextView tv_right = (TextView) findViewById(R.id.iv_right_title);
        if (tv_right != null) {
            tv_right.setVisibility(View.VISIBLE);
            tv_right.setText(title);
        }
    }

    /**
     * 描述:显示右菜单图片
     */
    protected void rightVisible(int drawable) {
        RelativeLayout rl_right = (RelativeLayout) findViewById(R.id.rl_right);
        if (rl_right != null) {
            rl_right.setVisibility(View.VISIBLE);
        }
        ImageView iv_right = (ImageView) findViewById(R.id.iv_right);
        if (iv_right != null) {
            iv_right.setVisibility(View.VISIBLE);
            iv_right.setImageResource(drawable);
        }
    }


    public void showEmpty(CommonAdapter adapter, ViewStub mVsEmpty) {
        if (adapter != null && adapter.getDatas().size() == 0) {
            EmptyListUtils.loadEmpty(true, mVsEmpty);
        } else {
            if (mVsEmpty != null) {
                mVsEmpty.setVisibility(View.GONE);
            }
        }
    }

    public void showEmpty(boolean isShowEmpty, ViewStub mVsEmpty) {
        if (isShowEmpty) {
            EmptyListUtils.loadEmpty(true, mVsEmpty);
        } else {
            if (mVsEmpty != null) {
                mVsEmpty.setVisibility(View.GONE);
            }
        }
    }

    private LoadingDialog loadingDialog;

    public void isShowDialog(boolean isShow) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        if (isShow) {
            loadingDialog.show();
        } else {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_QQ_SHARE || resultCode == Constants.REQUEST_QZONE_SHARE || resultCode == Constants.REQUEST_OLD_SHARE) {
                Tencent.handleResultData(data, mIUiListener);
            }
        }
    }

    private MyQQShareListener mIUiListener;


    public static class MyQQShareListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            ToastUtils.showToast("分享成功");
        }

        @Override
        public void onError(UiError uiError) {
            ToastUtils.showToast("分享失败" + uiError.errorMessage);
        }

        @Override
        public void onCancel() {
            ToastUtils.showToast("取消分享");
        }
    }
}
