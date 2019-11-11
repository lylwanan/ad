package com.ad.service;

import com.ad.model.sigmob.request.SigmobRequest;
import com.ad.model.sigmob.response.SigmobResponse;
import com.ad.service.impl.AdService;
import com.ad.service.impl.UpStreamService;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 广告代理service
 */
@Service
public class AdServiceImpl implements AdService {

    /**
     * 下游 sigmob
     * @param sigmobRequest 下游请求数据
     * @param upStreamService 下游统一代理sigmob渠道，controller中传入具体实现，如果阅客则实例化ADXUpStreamService
     * @return
     * @throws IOException
     */
    @Override
    public SigmobResponse sigmob(SigmobRequest sigmobRequest, UpStreamService upStreamService) throws IOException {
        return upStreamService.sigmobAd(sigmobRequest);
    }

}
