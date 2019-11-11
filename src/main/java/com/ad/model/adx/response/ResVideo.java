package com.ad.model.adx.response;

import lombok.Data;

import java.util.List;

@Data
public class ResVideo {
    private int w;
    private int h;
    private int type;
    private int size;
    private String mimes;
    private String iurl;
    private int duration;
    private Branner cover;
    private int skip;
    private int skipMinTime;
    private int preloadTtl;
    private VideoCard card;
    private List<ResAssets> assets;
}
