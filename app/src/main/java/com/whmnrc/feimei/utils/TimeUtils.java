package com.whmnrc.feimei.utils;

import android.text.TextUtils;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/05/27
 *     desc   : 时间工具类
 *     version: 1.0
 * </pre>
 */
public class TimeUtils {

    /**
     * 秒 转 分钟:秒
     */
    public static String second2MMSS(int totalSecond) {
        int minute = totalSecond / 60;
        int second = totalSecond % 60;
        return String.format("%02d:%02d", minute, second);
    }

    /**
     * 自己看方法名
     */
    public static String YYYYMMDDHHMMSS2YYYYMMDDHHMM(String date) {
        if (TextUtils.isEmpty(date)) return "";
        return string2String(date, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm");
    }

    /**
     * 自己看方法名
     */
    public static String YYYYMMDDHHMMSS2YYYYMMDD(String date) {
        if (TextUtils.isEmpty(date)) return "";
        return string2String(date, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
    }


    /**
     * String 转 String
     *
     * @param str          str
     * @param format       format
     * @param secondFormat secondFormat
     * @return String
     */
    public static String string2String(String str, String format, String secondFormat) {
        try {
            return date2String(new SimpleDateFormat(format).parse(str).getTime(), secondFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date().toString();
    }

    /**
     * Date（long） 转换 String
     *
     * @param time   time
     * @param format format
     * @return String
     */
    public static String date2String(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sdf.format(time);
    }

    public static String unixTimeStamp2FormatString(long unixTimeStamp) {
        long currentTime = System.currentTimeMillis() / 1000L;
        long diffTime = Math.abs(currentTime - unixTimeStamp);

        if (diffTime == 0) {
            return "刚刚";
        } else if (diffTime < 60) {
            return String.format("%d秒前", diffTime);
        } else if (diffTime < 60 * 60) {
            return String.format("%d分钟前", diffTime / 60);
        } else if (diffTime < 60 * 60 * 24) {
            return String.format("%d小时前", diffTime / 60 / 60);
        }
        return date2String(unixTimeStamp * 1000L, "yyyy-MM-dd");
    }

    public static String getDateToString(long milSecond) {
        Date date = new Date(milSecond * 1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getDateToString(String milSecond, String pattern) {
        if (TextUtils.isEmpty(milSecond)) {
            return "";
        }
        long aLong = Long.parseLong(milSecond);
        Date date = new Date(aLong * 1000);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond * 1000);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }


    /**
     * 格式化时间去掉T
     *
     * @param textView
     * @param time
     * @return
     */
    public static void setTime(TextView textView, String time) {

        if (TextUtils.isEmpty(time)) {
            return;
        }

        if (null == textView) {
            return;
        }

        if (!time.contains("T")) {
            textView.setText(time);
            return;
        }

        String[] split = time.split("T");

        String yymmdd = split[0];

        String hms = split[1];
        String substringHms;

        if (hms.length() > 8) {
            substringHms = hms.substring(0, 8);
        } else {
            substringHms = hms;
        }

        time = yymmdd.concat(" ").concat(substringHms);

        textView.setText(time);
    }


    /**
     * 设置显示的长度
     *
     * @param textView
     * @param time
     * @param length
     */
    public static void setTime(TextView textView, String time, int length) {

        if (TextUtils.isEmpty(time)) {
            return;
        }

        if (null == textView) {
            return;
        }

        if (!time.contains("T")) {
            textView.setText(time);
            return;
        }

        String[] split = time.split("T");

        String yymmdd = split[0];

        String hms = split[1];
        String substringHms;

        if (hms.length() > length) {
            substringHms = hms.substring(0, length);
        } else {
            substringHms = hms;
        }

        time = yymmdd.concat(" ").concat(substringHms);

        textView.setText(time);
    }

    /**
     * 设置显示的长度
     *
     * @param textView
     * @param time
     * @param isShowyymmdd
     */
    public static void setTime(TextView textView, String time, boolean isShowyymmdd) {

        if (TextUtils.isEmpty(time)) {
            return;
        }

        if (null == textView) {
            return;
        }

        if (!time.contains("T")) {
            textView.setText(time);
            return;
        }

        String[] split = time.split("T");

        String yymmdd = split[0];

        String hms = split[1];

        if (isShowyymmdd) {
            time = yymmdd;
        } else {
            time = hms;
        }

        textView.setText(time);
    }


    public static String getDateTimeFromMillisecond(Long millisecond) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(millisecond);
    }


    public static String getTodayOrYesterday(long date, String time) {//date 是存储的时间戳
        String[] times = time.split("T");
        String timeSub = times[1].substring(0, 5);


        //所在时区时8，系统初始时间是1970-01-01 80:00:00，注意是从八点开始，计算的时候要加回去
        int offSet = Calendar.getInstance().getTimeZone().getRawOffset();
        long today = (System.currentTimeMillis() + offSet) / 86400000;
        long start = (date + offSet) / 86400000;
        long intervalTime = start - today;
        //-2:前天,-1：昨天,0：今天,1：明天,2：后天
        String strDes;
        if (intervalTime == 0) {
            strDes = String.format("今天%s", timeSub);
        } else if (intervalTime == 1) {
            strDes = String.format("明天%s", timeSub);
        } else if (intervalTime == 2) {
            strDes = String.format("后天%s", timeSub);
        } else {
            String timeYue = time.split("T")[0].substring(5, 10);
            strDes = timeYue.replace("-", "月").concat("日");
        }
        return strDes;
    }


    /**
     * 转换时间
     *
     * @param time
     * @return
     */
    public static long changeTimeFormat(String time) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(changeTime(time)));
            return calendar.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 去T为空格
     *
     * @param time
     * @return
     */
    public static String changeTime(String time) {
        if (time == null) {
            return "0";
        }
        return time.replace("T", " ");
    }


}
