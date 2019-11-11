package com.ad.model.adx.request;

import lombok.Data;

@Data
public class Assets {
    private int id;
    private int required;
    private Title title;
    private IData data;
    private Banner img;
    private Video video;
}
