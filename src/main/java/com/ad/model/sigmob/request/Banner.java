package com.ad.model.sigmob.request;

import lombok.Data;

import java.util.List;

@Data
class Banner {
    /**
     * -------@request-------
     * 广告图片宽
     */
    private int w;
    /**
     * -------@request-------
     * 广告图片高
     */
    private int h;
    /**
     * 屏蔽类型，1-html， 500-视频，501-图片。例如：如该值为1，表示不支持html 的素材
     */
    private List<Integer> btype;
    /**
     * 允许的图片格式，例如：[“image/jpg”, “image/gif”]
     */
    private List<String> mimes;
    /**
     * 列出支持的框架，例：VPAID，MRAID；为空，则表示不支持；
     */
    private List<Integer> api;
}
