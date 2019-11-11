package com.ad.model.sigmob.request;

import lombok.Data;

@Data
class Pmp {
    /**
     * 标识请求的合作模式
     * 0-公开交易
     * 1- 私有交易
     * 当前仅会出现1-私有交易
     */
    private int _private;
    /**
     * 私有交易的具体信息
     */
    private Deals deals;

    public int getPrivate() {
        return _private;
    }

    public void setPrivate(int _private) {
        this._private = _private;
    }
}
