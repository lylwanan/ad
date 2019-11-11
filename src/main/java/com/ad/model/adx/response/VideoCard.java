package com.ad.model.adx.response;

import lombok.Data;

@Data
public class VideoCard {
    private int type;
    private String url;
    private String html;
    private String charset;
    private String icon;
    private String title;
    private String content;
    private int comments;
    private int endRatting;
    private VideoButton button;
}
