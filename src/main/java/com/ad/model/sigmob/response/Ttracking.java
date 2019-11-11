package com.ad.model.sigmob.response;

import lombok.Data;

import java.util.List;

@Data
public class Ttracking {
    /**
     * -------@request-------
     * 追踪事件类型，例如：show
     */
    private String etype;
    /**
     * 追踪事件地址数组
     */
    private List<String> eurl;
}

enum ETYPE {
    imp,
    click,
    start,
    play_quarter,
    play_two_quarters,
    play_three_quarters,
    complete,
    skip
}
