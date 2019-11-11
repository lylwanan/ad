package com.ad.model.sigmob.request;

import lombok.Data;

import java.util.List;

@Data
public class App {
    /**
     * -------@request-------
     * 应用ID
     */
    private String id;
    /**
     * -------@request-------
     * 应用名称
     */
    private String name;
    /**
     * Android，应用包名IOS，传iTUnes ID
     */
    private String bundle;
    /**
     * 应用类型，暂不支持
     */
    private List<String> cat;
    /**
     * 应用版本号，暂不支持
     */
    private String ver;
}
