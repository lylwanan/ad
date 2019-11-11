package com.ad.model.sigmob.response;

import lombok.Data;

@Data
public class Img {
    /**
     * -------@request-------
     * 图片地址
     */
    private String url;
    /**
     * 图片的宽度
     */
    private int w;
    /**
     * 图片的高度
     */
    private int h;
}
