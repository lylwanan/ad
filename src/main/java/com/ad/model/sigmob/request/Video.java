package com.ad.model.sigmob.request;

import lombok.Data;

import java.util.List;

@Data
class Video {
    /**
     * 允许的视频格式。例如：[“video/mp4”]
     */
    private List<String> mimes;
    /**
     * 视频最短时长，单位：秒；0 表示不限制；
     */
    private int minuration;
    /**
     * 视频最长时长，单位：秒；0 表示不限制；
     */
    private int maxduration;
    /**
     * 视频宽度；
     */
    private int w;
    /**
     * 视频高度；
     */
    private int h;
}
