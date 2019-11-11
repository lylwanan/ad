package com.ad.service;

import com.ad.model.adx.request.ADXRequest;
import com.ad.model.adx.response.ADXResponse;
import com.ad.model.sigmob.request.SigmobRequest;
import com.ad.model.sigmob.request.Imp;
import com.ad.model.sigmob.response.SigmobResponse;
import com.ad.service.impl.UpStreamService;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * 智友广告(阅客) ADX，具体的上游实现
 */
@Service
public class ADXUpStreamServiceImp implements UpStreamService {

    private static final MediaType ContentType = MediaType.parse("application/json; charset=utf-8");

    private ADXRequest sigmobRequestToADXRequest(SigmobRequest sigmobRequest) {
        com.ad.model.adx.request.App app = new com.ad.model.adx.request.App();
        app.setId(sigmobRequest.getApp().getId());
        app.setName(sigmobRequest.getApp().getName());
        app.setBundle(sigmobRequest.getApp().getBundle());
        app.setVer(sigmobRequest.getApp().getVer());

        com.ad.model.adx.request.Device device = new com.ad.model.adx.request.Device();
        device.setUa(sigmobRequest.getDevice().getUa());
//        device.setUa(bidRequest.getDevice().getGeo());
        device.setIp(sigmobRequest.getDevice().getIp());
        device.setIpv6(sigmobRequest.getDevice().getIpv6());
        device.setDevicetype(sigmobRequest.getDevice().getDevicetype());
        device.setMake(sigmobRequest.getDevice().getMake());
        device.setModel(sigmobRequest.getDevice().getModel());
        device.setOs(sigmobRequest.getDevice().getOs() == 1 ? "ios" : "android");
        device.setOsv(sigmobRequest.getDevice().getOsv());
        device.setH(sigmobRequest.getDevice().getH());
        device.setW(sigmobRequest.getDevice().getW());
        device.setPpi(2);
        device.setPxratio(6.5f);

        device.setIfa(sigmobRequest.getDevice().getIfa());
        device.setIfamd5(DigestUtils.md5Hex(sigmobRequest.getDevice().getIfa()));
        device.setIfasha1(DigestUtils.sha1Hex(sigmobRequest.getDevice().getIfa()));

        device.setIfv(sigmobRequest.getDevice().getIfa());
        device.setIfvmd5(DigestUtils.md5Hex(sigmobRequest.getDevice().getIfa()));
        device.setIfvsha1(DigestUtils.sha1Hex(sigmobRequest.getDevice().getIfa()));

        device.setUdid(sigmobRequest.getDevice().getIfa());
        device.setUdidmd5(DigestUtils.md5Hex(sigmobRequest.getDevice().getIfa()));
        device.setUdidsha1(DigestUtils.sha1Hex(sigmobRequest.getDevice().getIfa()));

        device.setMac("8c:85:90:57:34:c3");
        device.setMacmd5(DigestUtils.md5Hex("8c:85:90:57:34:c3"));
        device.setMacsha1(DigestUtils.sha1Hex("8c:85:90:57:34:c3"));

        device.setCarrier(sigmobRequest.getDevice().getCarrier());
        device.setConnectiontype(sigmobRequest.getDevice().getConnectiontype());
        device.setImsi("460984938923849");
        device.setOrientation(0);
        device.setGeo(new com.ad.model.adx.request.Geo(sigmobRequest.getDevice().getGeo().getLat(), sigmobRequest.getDevice().getGeo().getLon()));

        List<com.ad.model.adx.request.Imp> impList = new LinkedList();
        List bidImp = sigmobRequest.getImp();
        for (int i = 0; i < bidImp.size(); i++) {
            Imp item = (Imp) bidImp.get(i);
            impList.add(new com.ad.model.adx.request.Imp(item.getId(), item.getTagid(), item.getBidfloor()));
        }

        ADXRequest adxRequest = new ADXRequest();
        adxRequest.setId(DigestUtils.md5Hex(UUID.randomUUID().toString()));
        adxRequest.setVersion("1.1.0");
        adxRequest.setImp(impList);
        adxRequest.setApp(app);
        adxRequest.setDevice(device);
        adxRequest.setAt(1);
        adxRequest.setTest(0);
        adxRequest.setTmax(10 * 1000);

        return adxRequest;
    }

    private SigmobResponse ADXResponseToSigmobResponse(ADXResponse adxResponse) {
        return null;
    }

    @Override
    public SigmobResponse sigmobAd(SigmobRequest sigmobRequest) throws IOException {
        String url = "http://adx.8bcd9.com/bid/v6/rf9mfi5";
        ADXRequest params = sigmobRequestToADXRequest(sigmobRequest);
        System.out.println(JSON.toJSONString(sigmobRequest));

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept-Encoding", "gzip")
                .addHeader("X-Forwarded-For", sigmobRequest.getDevice().getIp())
                .addHeader("User-Agent", sigmobRequest.getDevice().getUa())
                .post(RequestBody.create(ContentType, JSON.toJSONString(params)))
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            System.out.println(response.body().string());
        }

        return null;
    }

    public static void main(String[] args) throws IOException {
        SigmobRequest sigmobRequest = JSON.parseObject("{\"app\":{\"bundle\":\"791532221\",\"id\":\"rgndjm7\",\"name\":\"kutoutiao\"},\"at\":1,\"cur\":\"CNY\",\"device\":{\"carrier\":\"China Mobile\",\"connectiontype\":4,\"devicetype\":1,\"geo\":{\"country\":\"CN\",\"lat\":23.886566,\"lon\":100.08697},\"h\":896,\"ifa\":\"EC90A86C-AF40-40FC-BA84-FCA69020AE55\",\"ip\":\"61.49.51.158\",\"ipv6\":\"fe80::cf8:33d2:3aff:1a52%en0\",\"language\":\"zh_CN\",\"make\":\"Apple\",\"mccmnc\":\"46002\",\"model\":\"iPhone11,8\",\"os\":1,\"osv\":\"11.4.1\",\"ua\":\"Mozilla/5.0 (iPhone; CPU iPhone OS 11_4_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15G77\",\"w\":414},\"id\":\"8404dde8-b188-11e8-87af-00163e0c1b32\",\"imp\":[{\"bidfloor\":0,\"bidfloorcur\":\"CNY\",\"exp\":7200,\"id\":\"1\",\"instl\":1,\"issupportdeeplink\":false,\"pmp\":{\"deals\":{\"at\":3,\"bidfloor\":12000,\"bidfloorcur\":\"CNY\",\"id\":\"384tu8rH\"},\"private\":1},\"tagid\":\"e335620397b\",\"video\":{\"h\":720,\"maxduration\":0,\"mimes\":[\"video/mp4\"],\"minuration\":0,\"w\":1280}}],\"test\":0,\"tmax\":2000,\"wlang\":[\"zh\"],\"wseat\":0}\n", SigmobRequest.class);
        new ADXUpStreamServiceImp().sigmobAd(sigmobRequest);
    }
}
