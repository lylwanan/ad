package com.ad.model.adx.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Events {
    private List<String> els;
    private List<String> cls;
    private List<String> sdls;
    private List<String> edls;
    private List<String> sils;
    private List<String> eils;
    private List<String> ials;
    private List<String> spls;
    private List<String> ppls;
    private List<String> gpls;
    private List<String> epls;
    private List<String> dcls;
    private List<String> cpls;
    private List<String> mpls;
    private List<String> skls;
    private Map<String, List<String>> fpls;
}
