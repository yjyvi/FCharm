package com.whmnrc.feimei.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.TreeMap;

/**
 * @author yjyvi
 * @data 2018/6/29.
 */

public class IpUtils {

    /**
     * 获取IP
     *
     * @param context
     * @return
     */
    public static String getIp(final Context context) {
        String ip = "0.0.0";
        ConnectivityManager conMan = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        // mobile 3G Data Network
        android.net.NetworkInfo.State mobile = conMan.getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE).getState();
        // wifi
        android.net.NetworkInfo.State wifi = conMan.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).getState();

        // 如果3G网络和wifi网络都未连接，且不是处于正在连接状态 则进入Network Setting界面 由用户配置网络连接
        if (mobile == android.net.NetworkInfo.State.CONNECTED
                || mobile == android.net.NetworkInfo.State.CONNECTING) {
            ip = getLocalIpAddress();
        }
        if (wifi == android.net.NetworkInfo.State.CONNECTED
                || wifi == android.net.NetworkInfo.State.CONNECTING) {
            //获取wifi服务
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            //判断wifi是否开启
            if (wifiManager != null && !wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                int ipAddress = wifiInfo.getIpAddress();
                ip = (ipAddress & 0xFF) + "." +
                        ((ipAddress >> 8) & 0xFF) + "." +
                        ((ipAddress >> 16) & 0xFF) + "." +
                        (ipAddress >> 24 & 0xFF);
            }
        }
        return ip;

    }

    /**
     * @return 手机GPRS网络的IP
     */
    private static String getLocalIpAddress() {
        try {
            //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {//获取IPv4的IP地址
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }


        return null;
    }


    /**
     * 获取外网的IP(必须放到子线程里处理)
     */
    public static void getNetIp(final IPListener ipListener) {
        //{ip:'59.172.109.120',address:'湖北省武汉市 电信'}
        OKHttpManager.get("http://ip.chinaz.com/getip.aspx", new TreeMap<String, String>(), new CommonCallBack<String>() {
            @Override
            public void onSuccess(String data) {
                if (!TextUtils.isEmpty(data)) {
                    String[] split = data.split(",");
                    String ip = split[0];
                    if (!TextUtils.isEmpty(ip)) {
                        String[] ips = ip.split(":");
                        if (!TextUtils.isEmpty(ips[1])) {
                            ipListener.getIPSuccess(ips[1]);
                        }
                    }
                }

            }
        });

    }

    public interface IPListener {
        void getIPSuccess(String ip);
    }
}
