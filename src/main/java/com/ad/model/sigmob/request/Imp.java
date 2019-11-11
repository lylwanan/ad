package com.ad.model.sigmob.request;

import lombok.Data;

import java.util.Map;

@Data
public class Imp {
    /**
     * -------@request-------
     * 唯一的广告展示iD，（通常从1 开始并以此递增）
     */
    private String id;
    /**
     * -------@request-------
     * 广告形式，1-激励视频，2-开屏；
     */
    private int instl;
    /**
     * -------@request-------
     * 广告单元ID
     */
    private String tagid;
    /**
     * -------@request-------
     * 广告，图片相关需求
     */
    private Banner banner;
    /**
     * -------@request-------
     * 广告，视频相关需求
     */
    private Video video;
    /**
     * 直接交易时使用该字段；
     */
    private Pmp pmp;
    /**
     * 广告单元，千次展示底价；为0，表示无底价；单位为币种的最小货币单位，目前是（分）
     */
    private int bidfloor;
    /**
     * 广告单元，千次展示底价，货币单位；默认值：CNY；
     */
    private String bidfloorcur;
    /**
     * 表示该广告请求是否支持deeplink 类型的广告，默认是true
     */
    private boolean issupportdeeplink;
    /**
     * 缓存广告时间时长，单位：秒；
     */
    private int exp;
    /**
     * 扩展字段
     */
    private Map<String, Object> ext;
}

