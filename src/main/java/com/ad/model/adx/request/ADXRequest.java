package com.ad.model.adx.request;

import lombok.Data;

import java.util.List;

@Data
public class ADXRequest {
    private String id;
    private String version;
    private List<Imp> imp;
    private Site sit;
    private App app;
    private Device device;
    private User user;
    private int at;
    private int test;
    private int tmax;
}

