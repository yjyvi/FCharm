package com.whmnrc.feimei.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.BaseFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class MyCollectionActivity extends BaseActivity {
    @BindView(R.id.tab)
    MagicIndicator tabLayout;
    @BindView(R.id.vp)
    ViewPager viewPager;

    private String[] titles = new String[]{"商品", "行业资源"};
    private List<BaseFragment> mFragments = new ArrayList<>();
    @Override
    protected void initViewData() {
        setTitle("我的收藏");

        initFragment();
        initTab();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_my_collection;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MyCollectionActivity.class);
        context.startActivity(starter);
    }



    private void initFragment() {
        for (int i = 0; i < titles.length; i++) {
            switch (i) {
                case 0:
                    mFragments.add(MyCollectionProductFragment.newInstance(1));
                    break;
                case 1:
                    mFragments.add(MyCollectionProductFragment.newInstance(2));
                    break;
                default:
                    break;
            }
        }
    }


    public class TableViewPagerAdapter extends FragmentPagerAdapter {
        private List<BaseFragment> mFragments;

        public TableViewPagerAdapter(FragmentManager supportFragmentManager, List<BaseFragment> fragments) {
            super(supportFragmentManager);
            this.mFragments = fragments;
        }

        @Override
        public BaseFragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }


    private void initTab() {
        viewPager.setAdapter(new TableViewPagerAdapter(getSupportFragmentManager(), mFragments));
        viewPager.setOffscreenPageLimit(3);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(final Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.BLACK);
                colorTransitionPagerTitleView.setSelectedColor(ContextCompat.getColor(context, R.color.normal_blue_text_color));
                colorTransitionPagerTitleView.setTextSize(0, getResources().getDimensionPixelSize(R.dimen.dm_14));
                colorTransitionPagerTitleView.setText(titles[index]);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setCurrentItem(index);

                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setLineHeight(4);
                indicator.setColors(ContextCompat.getColor(context, R.color.normal_blue_text_color));
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });

        tabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(tabLayout, viewPager);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
