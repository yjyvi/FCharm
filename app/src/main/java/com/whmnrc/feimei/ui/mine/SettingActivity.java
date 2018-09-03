package com.whmnrc.feimei.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.whmnrc.feimei.CommonConstant;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.pop.PopHintInfo;
import com.whmnrc.feimei.presener.LoginOutPresenter;
import com.whmnrc.feimei.presener.UpdateImgFilePresenter;
import com.whmnrc.feimei.presener.UpdateUserInfoPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.login.FindPwdActivity;
import com.whmnrc.feimei.utils.SPUtils;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.feimei.utils.evntBusBean.UserInfoEvent;
import com.whmnrc.mylibrary.utils.GlideUtils;
import com.whmnrc.mylibrary.utils.ImgVideoPickerUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/5/21.
 */

public class SettingActivity extends BaseActivity implements LoginOutPresenter.LoginListener, UpdateImgFilePresenter.UpdateHeadImgListener, UpdateUserInfoPresenter.UpdateUserInfoListener {

    @BindView(R.id.iv_user_img)
    ImageView mIvUserImg;
    @BindView(R.id.tv_vip_time)
    TextView mTvVipTime;
    @BindView(R.id.et_nickname)
    EditText mEtNickname;
    private PopHintInfo mPopHintInfo;
    public LoginOutPresenter mLoginOutPresenter;
    public UpdateImgFilePresenter mUpdateImgFilePresenter;
    public UpdateUserInfoPresenter mUpdateUserInfoPresenter;
    private String mResultImgUrl;
    private String mSearchContent;

    @Override
    protected void initViewData() {
        EventBus.getDefault().register(this);

        mUpdateUserInfoPresenter = new UpdateUserInfoPresenter(this);
        mUpdateImgFilePresenter = new UpdateImgFilePresenter(this);
        mLoginOutPresenter = new LoginOutPresenter(this);
        setTitle("设置");
        if (UserManager.getUser() != null && UserManager.getUser().getHeadImg() != null && !TextUtils.isEmpty(UserManager.getUser().getHeadImg())) {
            GlideUtils.LoadImage(this, UserManager.getUser().getHeadImg(), mIvUserImg);
        }
        if (UserManager.getUser() != null && UserManager.getUser().getVIP() != null && !TextUtils.isEmpty(UserManager.getUser().getVIP()) && UserManager.getUserIsVip()) {
            long milSecond = Long.parseLong(UserManager.getUser().getVIP());
            mTvVipTime.setText(String.format("会员至：%s", TimeUtils.getDateToString(milSecond)));
        }


        mEtNickname.setOnEditorActionListener((view, keyCode, event) -> {
            if (keyCode == EditorInfo.IME_ACTION_DONE) {
                // 先隐藏键盘
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(getCurrentFocus()
                                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                mSearchContent = view.getText().toString().trim();

                if (!TextUtils.isEmpty(mSearchContent)) {
                    if (UserManager.getUser() != null && UserManager.getUser().getHeadImg() != null && !TextUtils.isEmpty(UserManager.getUser().getHeadImg())) {
                        mResultImgUrl = UserManager.getUser().getHeadImg();
                    }
                    mUpdateUserInfoPresenter.updateUserInfo(mResultImgUrl, mSearchContent);
                    return true;
                }
            }
            return false;
        });


    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_setting;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, SettingActivity.class);
        context.startActivity(starter);
    }


    /**
     * //图片选择 拍照回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File headFile;
        if (resultCode == RESULT_OK) {
            List<LocalMedia> mediaList = PictureSelector.obtainMultipleResult(data);
            if (!mediaList.isEmpty())
                headFile = new File(mediaList.get(0).getCutPath());
            else {
                return;
            }

            GlideUtils.LoadImage(SettingActivity.this, headFile, mIvUserImg);
            mediaList.get(0).setCompressPath(mediaList.get(0).getCutPath());
            isShowDialog(true);
            mUpdateImgFilePresenter.uploadImg(mediaList, 0);
        }
    }


    @OnClick({R.id.tv_edit_header_img, R.id.ll_notification_pwd, R.id.ll_edit_nickname, R.id.ll_un_register, R.id.ll_about_us})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_edit_header_img:
                ImgVideoPickerUtils.openSinglePhoto(SettingActivity.this);
                break;
            case R.id.ll_notification_pwd:
                FindPwdActivity.start(view.getContext(), "修改密码");
                break;
            case R.id.ll_edit_nickname:
                break;
            case R.id.ll_about_us:
                AboutUsActivity.start(view.getContext());
                break;
            case R.id.ll_un_register:
                if (mPopHintInfo == null) {
                    mPopHintInfo = new PopHintInfo(SettingActivity.this, "亲，确定注销账号吗？");
                }
                mPopHintInfo.show();
                mPopHintInfo.setPopHintListener(() -> {
                    isShowDialog(true);
                    mLoginOutPresenter.onLoginOut();
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void loginOutSuccess() {
        isShowDialog(false);
        UserManager.clearUser();
        SPUtils.put(SettingActivity.this, CommonConstant.Common.LAST_LOGIN_ID, "");
        EventBus.getDefault().post(new UserInfoEvent().setEventType(UserInfoEvent.LOGIN_OUT));
        finish();
    }

    @Override
    public void loginOutField() {
        isShowDialog(false);
    }

    @Subscribe
    public void userChangeEvent(UserInfoEvent userInfoEvent) {

    }

    @Override
    public void loadSuccess(String resultImgUrl, List<LocalMedia> datas, int position) {
        this.mResultImgUrl = resultImgUrl;
        isShowDialog(false);
        String nickName = mEtNickname.getText().toString().trim();
        if (UserManager.getUser() != null && UserManager.getUser().getNickName() != null && TextUtils.isEmpty(nickName)) {
            nickName = UserManager.getUser().getNickName();
        }
        mUpdateUserInfoPresenter.updateUserInfo(resultImgUrl, nickName);
    }

    @Override
    public void uploadUserInfoSuccess(String resultMsg) {
        UserManager.refresh();
    }
}
