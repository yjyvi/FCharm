package com.whmnrc.mylibrary.widget.photobigview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.photoview.OnOutsidePhotoTapListener;
import com.luck.picture.lib.photoview.OnPhotoTapListener;
import com.luck.picture.lib.photoview.PhotoView;
import com.luck.picture.lib.photoview.PhotoViewAttacher;
import com.whmnrc.mylibrary.R;
import com.whmnrc.mylibrary.utils.GlideUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * @author zhuwenhai
 * @date 2017/12/28
 * 显示图片
 */

public class PhotoViewActivity extends AppCompatActivity {

    private HackyViewPager mViewPager;
    private List<String> mPicses;
    private int mPosition;
    private LinearLayout mDotContainer;
    private PhotoViewAttacher mAttacher;
    public static final String PHOTOVIEW_POSITION = "photoView_position";
    private PhotoView mPhotoView;
    private int photoPosition;
    private MyAdapter mAdapter;
    public static final String PHOTO_POSITION = "photo_position";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        initViewsAndEvents();
    }

    public static void start(Context context, List<String> pics, int position) {
        Intent starter = new Intent(context, PhotoViewActivity.class);
        starter.putStringArrayListExtra("pics", (ArrayList<String>) pics);
        starter.putExtra(PHOTO_POSITION, position);
        context.startActivity(starter);
    }

    public void initViewsAndEvents() {


        Intent intent = getIntent();
        mPosition = intent.getIntExtra(PHOTO_POSITION, 0);
        mPicses = intent.getStringArrayListExtra("pics");

        mDotContainer = ((LinearLayout) findViewById(R.id.photo_view_dot_container));
        addDots();

        mViewPager = ((HackyViewPager) findViewById(R.id.hackyViewPager));
        mAdapter = new MyAdapter();
        mViewPager.setAdapter(mAdapter);
        //设置页面改变监听
        mViewPager.addOnPageChangeListener(onPageChageListener);
        mViewPager.setCurrentItem(mPosition);
    }

    private ViewPager.OnPageChangeListener onPageChageListener = new ViewPager
            .SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < mAdapter.getCount(); i++) {
                mDotContainer.getChildAt(i).setEnabled(i != position);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mDotContainer.getChildAt(mPosition).setEnabled(false);
    }

    private void addDots() {
        mDotContainer.removeAllViews();
        if (mPicses == null || mPicses.isEmpty()) {
            return;
        }
        int dotWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
                getResources().getDisplayMetrics());
        for (String picUrl : mPicses) {
            View dot = new View(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dotWidth, dotWidth);
            lp.setMargins(0, 0, dotWidth, 0);
            dot.setLayoutParams(lp);
            dot.setBackgroundResource(R.drawable.arl_dot_selector);
            mDotContainer.addView(dot);
        }
    }

    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPicses == null ? 0 : mPicses.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, final int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo_view, null);
            mPhotoView = (PhotoView) view.findViewById(R.id.photo);
            GlideUtils.LoadImage(container.getContext(), mPicses.get(position), mPhotoView);
            //设置图片显示为居中填满
            mPhotoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            photoPosition = position;
            //给图片增加点击事件
            mPhotoView.setOnOutsidePhotoTapListener(new OnOutsidePhotoTapListener() {
                @Override
                public void onOutsidePhotoTap(ImageView imageView) {
                    finish();
                }
            });
            mPhotoView.setOnPhotoTapListener(new OnPhotoTapListener() {
                @Override
                public void onPhotoTap(ImageView imageView, float v, float v1) {
                    finish();
                }
            });

            mPhotoView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
//                    showDownloadPop(PhotoViewActivity.this, mPhotoView, mPicses.get(position));
                    return true;
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }


    /**
     * 显示下载弹窗
     *
     * @param context
     * @param contentView
     * @param url
     */
    public static void showDownloadPop(final Context context, View contentView, final String url) {
        final Activity activity = (Activity) context;
        View popView = LayoutInflater.from(activity).inflate(R.layout.download_pop, null);
        final PopupWindow popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams
                .MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popView.setPadding(30, 30, 30, 30);
        //设置SelectPicPopupWindow弹出窗体可点击
        showPoPNormal((Activity) context, contentView, popupWindow, Gravity.BOTTOM);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(activity, 1f);
            }
        });

        TextView tv_pop_download = (TextView) popView.findViewById(R.id.tv_pop_download);
        TextView tv_pop_cancel = (TextView) popView.findViewById(R.id.tv_pop_cancel);
        tv_pop_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String filePath = Environment.getExternalStorageDirectory() + "/flymallloadImg/";
                String fileName = "yuyou".concat(String.valueOf(System.currentTimeMillis())).concat(".jpg");
                if (!url.contains("http")) {
                    copyFile(url, filePath);
                    Toast.makeText(activity, "save success", Toast.LENGTH_SHORT).show();
                } else {
                    OkHttpUtils.get().url(url).build().execute(new FileCallBack(filePath, fileName) {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(activity, "save field", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(File response, int id) {
                            Toast.makeText(activity, "save success", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                popupWindow.dismiss();
            }
        });
        tv_pop_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 获取活动网络信息
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return NetworkInfo
     */


    /**
     * //进行复制的函数
     *
     * @param oldPath
     * @param newPath
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            //文件不存在时
            if (!oldfile.exists()) {
                //读入原文件
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    //字节数 文件大小
                    bytesum += byteread;
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }


    /**
     * 公共参数
     *
     * @param context
     * @param contentView
     * @param popupWindow
     */
    private static void showPoPNormal(final Activity context, View contentView, PopupWindow popupWindow, int location) {
        //设置SelectPicPopupWindow弹出窗体可点击
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置SelectPicPopupWindow弹出窗体动画效果
        setBackgroundAlpha(context, 0.5F);
        popupWindow.showAtLocation(contentView, location, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(context, 1F);
            }
        });
    }

    /**
     * 设置页面的透明度
     *
     * @param bgAlpha 1表示不透明
     */
    public static void setBackgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1) {
            //不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } else {
            //此行代码主要是解决在华为手机上半透明效果无效的bug
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        }
        activity.getWindow().setAttributes(lp);
    }
}
