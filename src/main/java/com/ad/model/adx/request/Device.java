package com.ad.model.adx.request;

import lombok.Data;

@Data
public class Device {
    private String ua;
    private Geo geo;
    private String ip;
    private String ipv6;
    private int devicetype;
    private String make;
    private String model;
    private String os;
    private String osv;
    private String hwv;
    private int w;
    private int h;
    private int ppi;
    private float pxratio;
    private String ifa;
    private String ifamd5;
    private String ifasha1;
    private String ifv;
    private String ifvmd5;
    private String ifvsha1;
    private String udid;
    private String udidmd5;
    private String udidsha1;
    private String did;
    private String didmd5;
    private String didsha1;
    private String dpid;
    private String dpidmd5;
    private String dpidsha1;
    private String aaId;
    private String aaIdMd5;
    private String aaIdSha1;
    private String oaId;
    private String oaIdmd5;
    private String oaIdsha1;
    private String mac;
    private String macmd5;
    private String macsha1;
    private String carrier;
    private int connectiontype;
    private String imsi;
    private int orientation;
    private DeviceExt extension;
}
