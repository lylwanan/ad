package com.ad.model.sigmob.response;

import lombok.Data;

import java.util.Map;

@Data
public class MaterialMeta {
    /**
     * -------@request-------
     * 广告标题,对于下载类建议填写app 名称，对于非下载类，建议填写品牌名称
     */
    private String title;
    /**
     * 广告描述
     */
    private String desc;
    /**
     * 创意中的icon 的url，下载类广告建议填写
     */
    private String icon;
    /**
     * 单张图片类广告，例如开屏广告的图片
     */
    private Img img;
    /**
     * 视频类广告物料
     */
    private Video video;
    /**
     * 扩展字段
     */
    Map<String, Object> ext;
}
