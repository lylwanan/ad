package com.ad.model.sigmob.response;

import lombok.Data;

/**
 * Video 视频对象
 */
@Data
public class Video {
    /**
     * 视频地址
     */
    private String url;
    /**
     * 视频时长，单位：秒；
     */
    private int dur;
    /**
     * 视频大小，单位：字节；
     */
    private int size;
    /**
     * 背景图，当Bid.attr 为4 时必填，用于渲染endcard
     */
    private Img img;
    /**
     * Html 代码段，当Bid.attr 为5时必填，用于渲染endcard
     */
    private String htmlsrc;
}
