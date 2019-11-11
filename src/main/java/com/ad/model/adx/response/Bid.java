package com.ad.model.adx.response;

import lombok.Data;

import java.util.List;

@Data
public class Bid {
    private String id;
    private String impid;
    private String dealid;
    private String crid;
    private String cid;
    private String adid;
    private List<String> adomain;
    private List<String> cat;
    private double price;
    private String nurl;
    private String burl;
    private String lurl;
    private String target;
    private String deepLink;
    private String actionType;
    private String demand;
    private String bundle;
    private String adm;
    private ResApp app;
    private Branner branner;
    private int skip;
    private int skipMinTime;
    private int preloadTtl;
    private VideoCard card;
    private List<ResAssets> assets;
}
