package com.ad.model.sigmob.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SigmobResponse {
    /**
     * -------@request-------
     * 请求id，对应bidrequest.id
     */
    private String id;
    /**
     * 不返回广告的原因，0 表示成功
     */
    private int nbr;
    /**
     * -------@request-------
     * 返回的广告数据数组
     */
    private List<com.ad.model.sigmob.response.Seatbid> seatbid;
    /**
     * 扩展字段
     */
    Map<String, Object> ext;
}
