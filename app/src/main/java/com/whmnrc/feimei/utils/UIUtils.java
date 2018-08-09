package com.whmnrc.feimei.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.whmnrc.mylibrary.utils.GlideUtils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 控件常用操作的工具类
 * Created by yonghao zeng on 2017/5/18.
 */

public class UIUtils {

    //判断非空和length在赋值 避免hint被覆盖
    public static void setText(TextView textView, @Nullable String text) {
        if (!TextUtils.isEmpty(text) && TextUtils.getTrimmedLength(text) > 0) {
            textView.setText(text);
        }
    }

    public static void setText(TextView textView, @Nullable Spanned text) {
        if (!TextUtils.isEmpty(text) && TextUtils.getTrimmedLength(text) > 0) {
            textView.setText(text);
        }
    }

    //设置价格 加￥符号
    public static void setTextPrice(TextView textView, @Nullable String text) {
        if (!TextUtils.isEmpty(text) && TextUtils.getTrimmedLength(text) > 0) {
            char symbol=165;
            textView.setText(String.valueOf(symbol) + text);
        }

    }
    public static void loadIntoUseFitWidth(Context context, final String imageUrl, final ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(imageUrl)
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {


                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (imageView == null) {
                            return false;
                        }
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                        float scale = (float) vw / (float) resource.getIntrinsicWidth();
                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                        params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                        imageView.setLayoutParams(params);

                        return false;
                    }
                }).into(imageView);
    }


    /**
     * 过滤输入框 回车符
     */
    public static String stringFilter(String str) {
        String regEx = "[/\\:*?<>|\"\n\t]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }

    public static void loadCommentImg(ImageView view, File file) {
        GlideUtils.LoadImage(view.getContext(),file.getPath(),view);
    }
    public static void loadCommentImg(ImageView view, String url) {
        if (TextUtils.isEmpty(url)) return;
        GlideUtils.LoadImage(view.getContext(),url,view);
    }
    public static void loadCircleImg(ImageView view, File path) {
        GlideUtils.LoadCircleImage(view.getContext(),path,view);
    }

    public static void loadCircleImg(ImageView view, String url) {
        if (TextUtils.isEmpty(url)) return;
        GlideUtils.LoadCircleImage(view.getContext(),url,view);
    }

    public static void  loadRound(ImageView view,String url){
        if (TextUtils.isEmpty(url)) return;
        GlideUtils.LoadRoundImage(view.getContext(),url,view);
    }

    public static void  loadRound(ImageView view,Drawable drawable){
        GlideUtils.LoadRoundImage(view.getContext(),drawable,view);
    }
}

