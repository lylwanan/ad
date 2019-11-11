package com.ad.model.sigmob.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Seatbid {
    /**
     * -------@request-------
     * 至少一个Bid 对象的数组，每个对象关联一个展示。
     */
    private List<Bid> bid;
    /**
     * 出价者席位名称
     */
    private String seat;
    /**
     * 扩展字段
     */
    private Map<String, Object> ext;
}
