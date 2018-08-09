package com.whmnrc.feimei.ui.mine;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.publish.PublishImgArrayAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.beans.MediaBean;
import com.whmnrc.feimei.presener.SendCommentPresenter;
import com.whmnrc.feimei.presener.UpdateImgFilePresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.utils.ToastUtils;
import com.whmnrc.mylibrary.utils.ImgVideoPickerUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * @author yjyvi
 * @data 2018/5/19.
 */

public class CommentActivity extends BaseActivity implements SendCommentPresenter.SendCommentListener, UpdateImgFilePresenter.UpdateHeadImgListener {

    @BindView(R.id.rv_photo)
    RecyclerView rvPhoto;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.ll_product)
    LinearLayout mLlProduct;
    @BindView(R.id.ll_commit)
    LinearLayout mLlCommit;

    private Map<LocalMedia, MediaBean> mediaMap = new HashMap<>();
    private List<LocalMedia> localMediaDatas = new ArrayList<>();
    private MultiItemTypeAdapter<LocalMedia> localMediaMultiItemTypeAdapter;
    private static final int MAX_PICTURE = 9;
    public SendCommentPresenter mAddEvaluatePresenter;
    private String videosUrl = "";
    public String mProductId;
    private String videoThumbUrl;
    public UpdateImgFilePresenter mUpdateImgFilePresenter;
    private static CommentListener mCommentListener;

    public static void setCommentListener(CommentListener commentListener) {
        mCommentListener = commentListener;
    }

    @Override
    protected void initViewData() {
        setTitle("发表评论");
        isShowButton();
        mProductId = getIntent().getStringExtra("productId");
        mAddEvaluatePresenter = new SendCommentPresenter(this);
        mUpdateImgFilePresenter = new UpdateImgFilePresenter(this);

        if (TextUtils.isEmpty(mProductId)) {
            mLlProduct.setVisibility(View.VISIBLE);
        } else {
            mLlProduct.setVisibility(View.GONE);
        }

        initRv();

        mEtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isShowButton();
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_organization_comment;
    }

    public static void start(Context context, String goodsId) {
        Intent starter = new Intent(context, CommentActivity.class);
        starter.putExtra("productId", goodsId);
        context.startActivity(starter);
    }


    private void initRv() {

        selectPremissions();
        rvPhoto.setLayoutManager(new GridLayoutManager(this, 3));
        localMediaMultiItemTypeAdapter = new MultiItemTypeAdapter<>(this);
        localMediaMultiItemTypeAdapter.addItemViewDelegate(new PublishImgArrayAdapter(new PublishImgArrayAdapter.OnClick() {
            @Override
            public void onCancelClick(View view, int position) {
                LocalMedia remove = localMediaDatas.remove(position);
                try {
                    mediaMap.remove(remove);
                } catch (Exception e) {

                }
                localMediaMultiItemTypeAdapter.notifyDataSetChanged();
            }
        }));


        localMediaMultiItemTypeAdapter.setDataArray(localMediaDatas);

        rvPhoto.setAdapter(localMediaMultiItemTypeAdapter);

    }

    private void selectPremissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        ).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {

                }
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            List<LocalMedia> mediaList = PictureSelector.obtainMultipleResult(data);
            if (mediaList.size() > 0) {

                if (mediaList.get(0).getMimeType() == PictureConfig.TYPE_VIDEO) {
                    localMediaDatas.addAll(0, mediaList);
                } else {
                    localMediaDatas.addAll(localMediaDatas.size(), mediaList);
                }


                localMediaMultiItemTypeAdapter.notifyDataSetChanged();
                if (mediaList.get(0).getMimeType() == PictureConfig.TYPE_VIDEO) {
//                    upLoadVideo(mediaList);
                } else {
                    isShowDialog(true);
                    uploadImg(mediaList, 0);
                }
            }
        }

    }


    public void uploadImg(final List<LocalMedia> datas, final int position) {
        mUpdateImgFilePresenter.uploadImg(datas, position);
    }


    @OnClick({R.id.rl_select_photo, R.id.ll_commit})
    public void onClick(View view) {
        if (localMediaDatas.size() > MAX_PICTURE) {
            ToastUtils.showToast("照片不能大于9张");
            return;
        }
        switch (view.getId()) {
            case R.id.rl_select_photo:
                ImgVideoPickerUtils.openPhoto(CommentActivity.this, MAX_PICTURE - localMediaDatas.size());
                break;

            case R.id.ll_commit:
                String content = mEtContent.getText().toString().trim();

                if (TextUtils.isEmpty(content)) {
                    ToastUtils.showToast("请输入评论内容");
                    return;
                }

                if (content.length() > 80) {
                    ToastUtils.showToast("请输入评论内容不少于80个字");
                    return;
                }


                ArrayList<String> imgs = new ArrayList<>();
                if (mediaMap.size() > 0) {
                    for (LocalMedia localMedia : mediaMap.keySet()) {
                        MediaBean mediaBean = mediaMap.get(localMedia);
                        imgs.add(mediaBean.getNetimgPath());
                    }
                }


                isShowDialog(true);
                mAddEvaluatePresenter.sendComment(mEtContent.getText().toString().trim(), imgs.toArray(), mProductId);

                break;
            default:
                break;
        }
    }

    @Override
    public void sendEvaluateSuccess() {
        if (mCommentListener != null) {
            mCommentListener.commentSuccess();
        }
        isShowDialog(false);
        finish();
    }

    @Override
    public void sendEvaluateField() {
        isShowDialog(false);
    }

    @Override
    public void loadSuccess(String resultImgUrl, List<LocalMedia> datas, int position) {
        MediaBean mediaBean = new MediaBean();
        mediaBean.setType(PictureConfig.TYPE_IMAGE);
        mediaBean.setNetimgPath(resultImgUrl);
        mediaBean.setSort(position);
        mediaMap.put(datas.get(position), mediaBean);
        uploadImg(datas, position + 1);
        if (datas.size() == position + 1) {
            isShowDialog(false);
        }
    }

    public interface CommentListener {
        void commentSuccess();
    }


    public void isShowButton() {
        if (mediaMap.size() > 0 && !TextUtils.isEmpty(mEtContent.getText().toString().trim())) {
            mLlCommit.setEnabled(true);
            mLlCommit.setBackgroundColor(ContextCompat.getColor(this,R.color.normal_blue_text_color));
        } else {
            mLlCommit.setEnabled(false);
            mLlCommit.setBackgroundColor(ContextCompat.getColor(this,R.color.normal_gray));
        }
    }

}
