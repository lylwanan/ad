package com.ad.model.sigmob.request;

import lombok.Data;

@Data
public class Geo {
    /**
     * 维度
     */
    private float lat;
    /**
     * 经度
     */
    private float lon;
    /**
     * 国家代码，遵循ISO-3166-1-alpha-2，例如：CN
     */
    private String country;
}
