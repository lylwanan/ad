package com.ad.model.adx.response;

import lombok.Data;

import java.util.List;

@Data
public class ResNative {
    private String version;
    private List<ResAssets> assets;
}
