package com.ad.model.sigmob.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SigmobRequest {
    /**
     * -------@request-------
     * 请求的唯一标识，由sigmob 生成
     */
    private String id;
    /**
     * -------@request-------
     * 广告请求信息
     */
    private List<Imp> imp;
    /**
     * -------@request-------
     * 应用信息
     */
    private App app;
    /**
     * -------@request-------
     * 设备信息
     */
    private Device device;
    /**
     * 1、正式，2、测试模式，暂未启用
     */
    private int test;
    /**
     * -------@request-------
     * 结算模式，1-最高价；2-次高价；默认是1
     */
    private int at;
    /**
     * 等待广告返回最长时间，单位:ms;
     */
    private int tmax;
    /**
     * 0- 则seat 代表广告主/代理商，黑名单
     * 1- 则seat 代表广告主/代理商，白名单
     * 默认为1
     */
    private int wseat;
    /**
     * 广告主/代理商的名单
     */
    private List<String> seat;
    /**
     * 使用ISO-4217 字母代码在此投标请求上投标的允许货币数组，目前固定：CNY
     */
    private List<String> cur;
    /**
     * 使用ISO-639-1-Alpha-2 的创意语言白名单，[zh]
     */
    private List<String> wlang;
    /**
     * 广告类型的黑名单
     */
    private List<String> bcat;
    /**
     * 广告主域名的黑名单
     */
    private List<String> badv;
    /**
     * 广告应用的黑名单，iOS为iTunes ID，Android为包名建议支持
     */
    private List<String> bapp;
    /**
     * 扩展字段
     */
    Map<String, Object> ext;
}
