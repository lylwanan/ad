package com.ad.model.adx.request;

import lombok.Data;

@Data
public class User {
    private String id;
    private String keywords;
    private Geo geo;
    private IData data;
}
