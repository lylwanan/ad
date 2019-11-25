package com.ad.controller;

import com.ad.exception.ErrorCodeMsg;
import com.ad.exception.LogicException;
import com.ad.model.ApiResult;
import com.ad.model.sigmob.request.SigmobRequest;
import com.ad.model.sigmob.response.SigmobResponse;
import com.ad.service.impl.AdService;
import com.ad.service.impl.UpStreamService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ad")
public class AdController {
    @Autowired
    private AdService adService;

    @Autowired
    private UpStreamService upStreamService;

    @ResponseBody
    @PostMapping("/sigmob")
    public ApiResult sigmob(@RequestBody SigmobRequest sigmobRequest) {
        try {
            SigmobResponse sigmobResponse = this.adService.sigmob(sigmobRequest, upStreamService);
            if (sigmobResponse == null) {
                return ApiResult.ERROR(ErrorCodeMsg.NOT_FOUND_AD);
            }
            return ApiResult.SUCCESS(sigmobResponse);
        } catch (LogicException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new LogicException("100", ex.getMessage());
        }
    }
}
