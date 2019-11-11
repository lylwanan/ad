package com.ad.model.adx.request;

import lombok.Data;

@Data
public class Geo {
    private float lat;
    private float lon;
    private int coordinate;
    private long timestamp;
    private int accu;

    public Geo() {

    }

    public Geo(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
