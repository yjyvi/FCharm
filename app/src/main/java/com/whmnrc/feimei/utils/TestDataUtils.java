package com.whmnrc.feimei.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yjyvi
 * @data 2018/7/23.
 */

public class TestDataUtils {

    @NonNull
    public static List<Object> initTestData(int size) {
        List<Object> mData = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            mData.add(new Object());
        }
        return mData;
    }

}
