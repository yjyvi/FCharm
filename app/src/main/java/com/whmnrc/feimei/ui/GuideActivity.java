package com.whmnrc.feimei.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.whmnrc.feimei.CommonConstant;
import com.whmnrc.feimei.MyApplication;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.ui.login.LoginActivity;
import com.whmnrc.feimei.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author wen
 * @date 2016/11/24
 * 向导界面
 */
public class GuideActivity extends BaseActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener, View.OnTouchListener {


    @BindView(R.id.viewpager)
     ViewPager mViewpager;

    @BindView(R.id.rl)
     RelativeLayout mRl;

    private GuideAdapter vpAdapter;
    private List<View> views;

    //引导图片资源
    private static final int[] pics = {
            R.mipmap.gride01,
            R.mipmap.gride02};
    //底部小店图片
//    private ImageView[] dots ;

    //记录当前选中位置
    private int currentIndex;

    private int arg2tem;
    //最小距离
    private static final int FLING_MIN_DISTANCE = 50;

    //最小速度
    private static final int FLING_MIN_VELOCITY = 0;
    private GestureDetector mGestureDetector;

    /**
     * 跳转Activity过来的接引方法
     *
     * @param context
     */
    public static void startGuideActivity(Context context) {
        Intent starter = new Intent(context, GuideActivity.class);
        context.startActivity(starter);
    }


    @Override
    protected void back() {

    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initViewData() {

        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mRl = (RelativeLayout) findViewById(R.id.rl);

        //权限初始化
        SPUtils.putTokend(MyApplication.applicationContext, CommonConstant.Common.FIRST_LAUNCHER, true);
        mGestureDetector = new GestureDetector(this, myGestureListener);
        //将主容器的监听交给本activity，本activity再交给mGestureDetector
        mViewpager.setOnTouchListener(this);
        //必需设置这为true 否则也监听不到手势
        mViewpager.setLongClickable(true);

        views = new ArrayList<>();

        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        //初始化引导图片列表
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setLayoutParams(mParams);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource(pics[i]);
            views.add(iv);
            if (i == pics.length - 1) {
                iv.setOnClickListener(view -> {
                    LoginActivity.start(GuideActivity.this);
                    finish();
                });
            }
        }
        //初始化Adapter
        vpAdapter = new GuideAdapter(views);
        mViewpager.setAdapter(vpAdapter);
        //绑定回调
        mViewpager.addOnPageChangeListener(this);


    }




    GestureDetector.SimpleOnGestureListener myGestureListener = new GestureDetector.SimpleOnGestureListener() {
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float x = e1.getX() - e2.getX();
            float x2 = e2.getX() - e1.getX();
            if (x > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                if (arg2tem == 2) {
                    LoginActivity.start(GuideActivity.this);
                    finish();
                }
            } else if (x2 > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            }

            return false;
        }


    };

    /**
     * 设置当前的引导页
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        mViewpager.setCurrentItem(position);
    }

    /**
     * 这只当前引导小点的选中
     */
    private void setCurDot(int position) {
        if (position < 0 || position > pics.length - 1 || currentIndex == position) {
            return;
        }
        currentIndex = position;
    }

    /**
     * //当滑动状态改变时调用
     *
     * @param arg0
     */
    @Override
    public void onPageScrollStateChanged(int arg0) {
        Log.i("arg0", "onPageScrollStateChanged: " + arg0);
    }

    /**
     * //当当前页面被滑动时调用
     *
     * @param arg0
     * @param arg1
     * @param arg2
     */
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub
        arg2tem = arg0;

    }

    /**
     * 当新的页面被选中时调用
     *
     * @param arg0
     */
    @Override
    public void onPageSelected(int arg0) {
        Log.i("arg1", "onPageScrollStateChanged: " + arg0);
        //设置底部小点选中状态
        setCurDot(arg0);
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        setCurView(position);
        setCurDot(position);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }




    /**
     * 引导页的适配器
     */
    public class GuideAdapter extends PagerAdapter {

        private List<View> views;

        public GuideAdapter(List<View> views) {
            this.views = views;
        }

        //销毁arg1位置的界面
        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(views.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        //获得当前界面数
        @Override
        public int getCount() {
            if (views != null) {
                return views.size();
            }

            return 0;
        }


        //初始化arg1位置的界面
        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(views.get(arg1), 0);
            return views.get(arg1);
        }

        //判断是否由对象生成界面
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return (arg0 == arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }
    }


}
