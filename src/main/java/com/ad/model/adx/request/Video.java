package com.ad.model.adx.request;

import lombok.Data;

import java.util.List;

@Data
public class Video {
    private int w;
    private int h;
    private int type;
    private int minduration;
    private int maxduration;
    private int startdelay;
    private int linearity;
    private int pos;
    private List<String> mimes;
}
