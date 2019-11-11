package com.ad.model.sigmob.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Bid {
    /**
     * 竞拍者生成的竞价ID，用于记录日志或行为追踪
     */
    private String id;
    /**
     * -------@request-------
     * 对应广告请求数据里imp 对象的id
     */
    private String impid;
    /**
     * -------@request-------
     * 出价价格(单位分)
     */
    private int price;
    /**
     * -------@request-------
     * 广告id，广告的唯一标识
     */
    private String adid;
    /**
     * 竞价失败原因回调，暂不支持
     */
    private String lurl;
    /**
     * 获胜通知url 列表，暂不支持
     */
    private List<String> wurl;
    /**
     * 广告主域名列表
     */
    private List<String> adomain;
    /**
     * Android，应用包名OS，传iTUnes ID，针对应用下载类广告
     */
    private String bundle;
    /**
     * Campaign ID
     */
    private String cid;
    /**
     * 创意ID
     */
    private String crid;
    /**
     * 广告分类
     */
    private String cat;
    /**
     * -------@request-------
     * 创意类型
     * 3=纯静态图片广告，一般由单张
     * image_src 构成;
     * 4=video+endcard（素材，背景图）;
     * 5=video+ endcard（html source）
     * 6=only video;
     */
    private int attr;
    /**
     * 使用ISO-639-1-Alpha-2 的语言对应表的值，例如zh
     */
    private String language;
    /**
     * 直接交易下，取自Bid Request 中的deal.id 值
     */
    private String dealid;
    /**
     * 广告在返回 到 展示之间的秒数限制，默认7200 秒
     */
    private int exp;
    /**
     * -------@request-------
     * 广告交互类型。1=使用浏览器打开；2=下载应用
     */
    private int interactiontype;
    /**
     * -------@request-------
     * 广告目标地址；如果为唤起类广告，当Deeplink 链接无法打开时，则使用此链接响应用户行为
     */
    private String landingpage;
    /**
     * deeplink 链接
     */
    private String deeplinkurl;
    /**
     * -------@request-------
     * 物料信息
     */
    private MaterialMeta material;
    /**
     * -------@request-------
     * 广告事件追踪对象数组
     */
    private List<Ttracking> trackings;
    /**
     * 扩展字段
     */
    Map<String, Object> ext;
}
