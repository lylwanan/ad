package com.ad.service.impl;

import com.ad.model.sigmob.request.SigmobRequest;

import java.io.IOException;

/**
 * 提供具体的下游接口
 */
public interface UpStreamService {
    /**
     * sigmob下游接口
     * @param sigmobRequest
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> T sigmobAd(SigmobRequest sigmobRequest) throws IOException;
}
