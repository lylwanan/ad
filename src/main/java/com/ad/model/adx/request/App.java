package com.ad.model.adx.request;

import lombok.Data;

import java.util.List;

@Data
public class App {
    private String id;
    private String name;
    private String bundle;
    private String ver;
    private int paid;
    private String keywords;
    private List<String> cat;
}
