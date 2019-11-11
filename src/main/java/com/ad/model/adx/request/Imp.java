package com.ad.model.adx.request;

import lombok.Data;

@Data
public class Imp {
    private String id;
    private String tagid;
    private double bidfloor;
    private Banner banner;
    private Video video;
    private Native _native;
    private Pmp pmp;

    public Imp() {

    }

    public Imp(String id, String tagid, double bidfloor) {
        this.id = id;
        this.tagid = tagid;
        this.bidfloor = bidfloor;
    }

    public void setNative(Native _native) {
        this._native = _native;
    }

    public Native getNative() { return _native; }
}
