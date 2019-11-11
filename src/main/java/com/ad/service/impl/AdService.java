package com.ad.service.impl;

import com.ad.model.sigmob.request.SigmobRequest;
import com.ad.model.sigmob.response.SigmobResponse;

import java.io.IOException;

public interface AdService {
    /**
     * 获取广告传给sigmob
     * @param sigmobRequest
     * @return
     */
    SigmobResponse sigmob(SigmobRequest sigmobRequest, UpStreamService upStreamService) throws IOException;
}
