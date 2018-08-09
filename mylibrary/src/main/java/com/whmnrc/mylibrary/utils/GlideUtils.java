package com.whmnrc.mylibrary.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.whmnrc.mylibrary.R;

import java.io.File;
import java.security.MessageDigest;

/**
 * @author zhuwenhai
 * @date 2017/12/2
 * Glide的加载工具类
 */

public class GlideUtils {
    /**
     * 加载网络图片
     *
     * @param mContext
     * @param path
     * @param imageview
     */
    public static void LoadImage(Context mContext, String path, final ImageView imageview) {
        if (TextUtils.isEmpty(path)) {
            return;
        }

//        if (!path.startsWith("http")) {
//            path = "http://api.optic-female.cn" + path;
//        }

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.color.normal_gray);
        requestOptions.error(R.color.normal_gray);
        requestOptions.fitCenter();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        int width = imageview.getWidth() / 2;
        int height = imageview.getHeight() / 2;
        requestOptions.override(width, height);
        Glide.with(mContext)
                .load(path)
                .apply(requestOptions)
                .into(imageview);
    }


    public static void LoadImage(Context mContext, String path,
                                 final ImageView imageview, int width, int height) {
        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.color.back_gray);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        requestOptions.override(width, height);
        requestOptions.fitCenter();
        requestOptions.placeholder(R.color.normal_gray);
        requestOptions.error(R.color.normal_gray);
        Glide.with(mContext)
                .load(path)
                .apply(requestOptions)
                .into(imageview);
    }

    public static void LoadImage(Context mContext, int path,
                                 final ImageView imageview) {
        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.color.back_gray);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        requestOptions.placeholder(R.color.normal_gray);
        requestOptions.fitCenter();
        requestOptions.error(R.color.normal_gray);
        Glide.with(mContext)
                .load(path)
                .apply(requestOptions)
                .into(imageview);
    }

    public static void LoadImage(Context mContext, File path,
                                 final ImageView imageview) {
        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.color.back_gray);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        requestOptions.placeholder(R.color.normal_gray);
        requestOptions.fitCenter();
        requestOptions.error(R.color.normal_gray);
        Glide.with(mContext)
                .load(path)
                .apply(requestOptions)
                .into(imageview);
    }

    public static void LoadRoundImage(Context mContext, String path,
                                      ImageView imageview) {
        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.color.back_gray);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.transform(new GlideRoundTransform(mContext));
        requestOptions.fitCenter();
        requestOptions.placeholder(R.color.normal_gray);
        requestOptions.error(R.color.normal_gray);
        Glide.with(mContext)
                .load(path)
                .apply(requestOptions)
                .into(imageview);
    }


    public static void LoadRoundImage(Context mContext, int path, ImageView imageview) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.transform(new GlideRoundTransform(mContext));
        requestOptions.placeholder(R.color.normal_gray);
        requestOptions.error(R.color.normal_gray);
        Glide.with(mContext)
                .load(path)
                .apply(requestOptions)
                .into(imageview);
    }


    public static void LoadRoundImage(Context mContext, Drawable path,
                                      ImageView imageview) {
        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.color.back_gray);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.transform(new GlideRoundTransform(mContext));
        requestOptions.fitCenter();
        requestOptions.placeholder(R.color.normal_gray);
        requestOptions.error(R.color.normal_gray);
        Glide.with(mContext)
                .load(path)
                .apply(requestOptions)
                .into(imageview);
    }


    /**
     * 圆形加载
     *
     * @param mContext
     * @param path
     * @param imageview
     */
    public static void LoadCircleImage(Context mContext, String path,
                                       ImageView imageview) {

        if (TextUtils.isEmpty(path)) {
            return;
        }

        if (!path.startsWith("http")) {
            path = "http://api.optic-female.cn" + path;
        }

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.transform(new GlideCircleTransform(mContext));
        requestOptions.placeholder(R.color.normal_gray);
        requestOptions.error(R.color.normal_gray);
        Glide.with(mContext).load(path).apply(requestOptions).into(imageview);

    }

    public static void LoadCircleImage(Context mContext, int path,
                                       ImageView imageview) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.transform(new GlideCircleTransform(mContext));
        Glide.with(mContext).load(path).apply(requestOptions).into(imageview);

    }

    public static void LoadCircleImage(Context mContext, File path,
                                       ImageView imageview) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.transform(new GlideCircleTransform(mContext));
        requestOptions.placeholder(R.color.normal_gray);
        requestOptions.error(R.color.normal_gray);
        Glide.with(mContext).load(path).apply(requestOptions).into(imageview);

    }

    public static class GlideRoundTransform extends BitmapTransformation {

        private static float radius = 0f;

        public GlideRoundTransform(Context context) {
            this(context, 5);
        }

        public GlideRoundTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }


        @Override
        public void updateDiskCacheKey(MessageDigest messageDigest) {

        }
    }

    //圆形图片
    public static class GlideCircleTransform extends BitmapTransformation {
        public GlideCircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int
                outHeight) {
            return circleCrop(pool, toTransform);
        }

        private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader
                    .TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }


        @Override
        public void updateDiskCacheKey(MessageDigest messageDigest) {

        }
    }

}
