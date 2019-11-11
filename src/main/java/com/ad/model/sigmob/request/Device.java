package com.ad.model.sigmob.request;

import lombok.Data;

import java.util.Map;

@Data
public class Device {
    /**
     * 浏览器UA 值
     */
    private String ua;
    /**
     * 地理位置信息
     */
    private Geo geo;
    /**
     * 客户端IP 值
     */
    private String ip;
    /**
     * 客户端IPv6 值
     */
    private String ipv6;
    /**
     * 0-unknown；1-手机；2-平板；
     */
    private int devicetype;
    /**
     * 制造商
     */
    private String make;
    /**
     * 设备 型号
     */
    private String model;
    /**
     * 操作系统，1：ios，2:安卓
     */
    private int os;
    /**
     * 操作系统 版本号；
     */
    private String osv;
    /**
     * 屏幕高；
     */
    private int h;
    /**
     * 屏幕宽；
     */
    private int w;
    /**
     * 使用ISO-639-1-Alpha-2 的语言应表的值
     */
    private String language;
    /**
     * 运营商，仅支持中国运营商，例如：China mobile
     */
    private String carrier;
    /**
     * 移动运营商作为连接的MCC-MNC代码，例如：46001
     */
    private String mccmnc;
    /**
     * 联网情况，0=无法探测当前网络状态;
     * 1=蜂窝数据接入，未知网络类型;
     * 2=2G;
     * 3=3G;
     * 4=4G;
     * 5=5G;
     * 100=Wi-Fi 网络接入;
     * 101=以太网接入。
     */
    private int connectiontype;
    /**
     * -------@request-------
     * iOS 的iDFA 值，ios 必传
     */
    private String ifa;
    /**
     * -------@request-------
     * Android 的imei 值 ，安卓必传
     */
    private String imei;
    /**
     * Google advertising id
     */
    private String gaid;
    /**
     * -------@request-------
     * 安卓id
     */
    private String androidid;
    /**
     * 扩展字段
     */
    Map<String, Object> ext;
}
