package com.ad.model.sigmob.request;

import lombok.Data;

@Data
class Deals {
    /**
     * 由Sigmob 定义，标识唯一的直接交易标识；
     */
    private String id;
    /**
     * 广告单元，千次展示底价，单位为分；为0，表示无底价；
     */
    private int bidfloor;
    /**
     * 广告单元，千次展示底价，货币单位；默认值：CNY；
     */
    private String bidfloorcur;
    /**
     * 此结算模式，会覆盖外层的结算模式
     * 1-最高价；2-次高价；3-底价成交(固定ecpm 成交)；
     */
    private int at;
}
