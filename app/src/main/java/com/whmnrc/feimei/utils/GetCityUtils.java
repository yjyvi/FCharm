package com.whmnrc.feimei.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.beans.JsonBean;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author yjyvi
 * @data 2018/8/2.
 */

public class GetCityUtils {

    //可见列表项的数量
    private static int visibleCount = 0;
    //上次点击的位置
    private static int lastPosition = 0;
    private static int ce = 0;
    //实际列表是否超出屏幕
    private static boolean isOut = true;
    private static View lastLineView;

    //获取.json文件转为string
    public static String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    //String转List
    public static ArrayList<JsonBean> parseData(String result) {
        //Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }


    /**
     * 将选择的目录滑动到中间
     *
     * @param position
     * @param view
     */
    public static void scroll(int position, CommonAdapter adapter1, View view, RecyclerView recyclerView) {
        if (adapter1.getDatas() == null) {
            return;
        }
        if (view == null) {
            return;
        }

        //改变选中状态
        if (!view.isSelected()) {
            //去除上一次控件的状态
            if (lastLineView != null) {
                lastLineView.setSelected(false);
            }
            lastLineView = view;

            view.setSelected(true);
        }


        if (visibleCount == 0) {
            visibleCount = recyclerView.getChildCount();
            if (visibleCount == adapter1.getDatas().size()) {
                isOut = false;
            } else {
                ce = visibleCount / 2;
            }
        }

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
            //上移
            if (position <= (linearManager.findFirstVisibleItemPosition() + ce)) {
                recyclerView.smoothScrollToPosition(position - ce);
            } else {
                //下移
                if ((linearManager.findLastVisibleItemPosition() + ce + 1) <= adapter1.getItemCount()) {
                    recyclerView.smoothScrollToPosition(position + ce);
                } else {
                    recyclerView.smoothScrollToPosition(adapter1.getItemCount() - 1);
                }
            }
            lastPosition = position;
        }

    }


}
