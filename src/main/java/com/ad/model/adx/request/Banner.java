package com.ad.model.adx.request;

import lombok.Data;

import java.util.List;

@Data
public class Banner {
    private String type;
    private int w;
    private int h;
    private int pos;
    private List<String> mimes;
}
