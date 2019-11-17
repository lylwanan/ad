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
        device.setCarrier(sigmobRequest.getDevice().getMccmnc());
        device.setConnectiontype(sigmobRequest.getDevice().getConnectiontype());
        device.setDevicetype(sigmobRequest.getDevice().getDevicetype());
        // 如果是Android设备
        if (sigmobRequest.getDevice().getOs() == 2) {
            device.setDid(sigmobRequest.getDevice().getImei());
            device.setDidmd5(DigestUtils.md5Hex(sigmobRequest.getDevice().getImei()));
            device.setDpid(sigmobRequest.getDevice().getAndroidid());
            device.setDpidmd5(DigestUtils.md5Hex(sigmobRequest.getDevice().getAndroidid()));
            device.setOs("android");
        }
        device.setH(sigmobRequest.getDevice().getH());
        device.setW(sigmobRequest.getDevice().getW());
        device.setIp(sigmobRequest.getDevice().getIp());
        device.setMac(sigmobRequest.getDevice().getIpv6());
        device.setMacmd5(DigestUtils.md5Hex(sigmobRequest.getDevice().getIpv6()));
        device.setMake(sigmobRequest.getDevice().getMake());
        device.setModel(sigmobRequest.getDevice().getModel());
        device.setOrientation(1);
        device.setOsv(sigmobRequest.getDevice().getOsv());
        device.setUa(sigmobRequest.getDevice().getUa());
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
        String url = "http://stage-adx.8bcd9.com/bid/v6/rf9mfi5";
        String params = JSON.toJSONString(sigmobRequestToADXRequest(sigmobRequest));
        System.out.println(params);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
//                .addHeader("Accept-Encoding", "gzip")
                .addHeader("X-Forwarded-For", sigmobRequest.getDevice().getIp())
                .addHeader("User-Agent", sigmobRequest.getDevice().getUa())
                .post(RequestBody.create(ContentType, params))
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            System.out.println(response.body().string());
        }

        return null;
    }

    public static void main(String[] args) throws IOException {
        SigmobRequest sigmobRequest = JSON.parseObject("{\"app\":{\"bundle\":\"791532221\",\"id\":\"rf9ml8f\",\"name\":\"kutoutiao\",\"ver\":\"2.2.1\"},\"at\":1,\"cur\":\"CNY\",\"device\":{\"carrier\":\"China Mobile\",\"connectiontype\":4,\"devicetype\":1,\"geo\":{\"country\":\"CN\",\"lat\":23.886566,\"lon\":100.08697},\"h\":896,\"ifa\":\"EC90A86C-AF40-40FC-BA84-FCA69020AE55\",\"ip\":\"61.49.51.158\",\"ipv6\":\"9C:2E:A1:D0:43:B5\",\"language\":\"zh_CN\",\"make\":\"Xiaomi\",\"mccmnc\":\"46002\",\"model\":\"Redmi 5 Plus\",\"os\":2,\"osv\":\"11.4.1\",\"ua\":\"Mozilla/5.0 (iPhone; CPU iPhone OS 11_4_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15G77\",\"w\":414,\"imei\":\"868771037064745\",\"androidid\":\"22b19878f2cf459f\"},\"id\":\"8404dde8-b188-11e8-87af-00163e0c1b32\",\"imp\":[{\"bidfloor\":0,\"bidfloorcur\":\"CNY\",\"exp\":7200,\"id\":\"1\",\"instl\":1,\"issupportdeeplink\":false,\"pmp\":{\"deals\":{\"at\":3,\"bidfloor\":12000,\"bidfloorcur\":\"CNY\",\"id\":\"384tu8rH\"},\"private\":1},\"tagid\":\"rgnpqvh\",\"video\":{\"h\":720,\"maxduration\":0,\"mimes\":[\"video/mp4\"],\"minuration\":0,\"w\":1280}}],\"test\":0,\"tmax\":2000,\"wlang\":[\"zh\"],\"wseat\":0}", SigmobRequest.class);
        new ADXUpStreamServiceImp().sigmobAd(sigmobRequest);
        // {"bidid":"2c852c3a3ff565695b9623f11c7e910e","code":"10000","id":"2c852c3a3ff565695b9623f11c7e910e","info":"sucess","seatbid":[{"bid":[{"actionType":"1","adid":"rgnl1d2","adm":"","adomain":[],"app":{"icon":"","md5":"","name":"","pack":"","size":0,"vers":""},"banner":{"h":50,"iurl":"http://img7n.51gran.com/images/cdn/b43e3f7961ba0bfae9c0decb11fd4039","mimes":"","type":3,"w":320},"bundle":"","burl":"","cat":[],"cid":"rglyffl","crid":"rgnl1d2","dealid":"","deepLink":"","demand":"","events":{"cls":["http://stage-adx.8bcd9.com/monitor/common/1?tid=2c852c3a3ff565695b9623f11c7e910e&rsid=RS004&s=1&inf=72107717B779B37F4E3326B64F540B61F991D56D72988BA285785E5B2F2C5A408C114F5D23D3DBCFF67BB1B40FF0353901288506377C7736E4C344EB209F8F2F1FC31EC61740EC11BE691C2423FA345F2A731DCB237F4C2BAEB33ACCF96E069C43516261E4A1ABA8DF97322442762554D5BE4D56F38501627FB4BB519CFBA9075EC1B8101A93AF0909DBA8EF951201445CE6A490E120D703AE69A581CFC83479B3C5754D6995A5541656269033A920D60666900FAEA6BAB0D1DD54A8010CB098082AE474D2B640A75EA2476D6EFD201A9C07D2BD57B0D6D0C67846CBFE6ACEA52999BA13F015B20DFB368CC0808A1D880E9198F80E24D98CD001635EA215B604561CDFAEC102D063DFE021A71C3289251B23439144BD2FF32206A5A343FCCD4A064729C5AE712EC89B29E4E5768C607D19A4E981B711FA635602035981A789DC57F6AEE9AB8BEE7436AC984B9C94101D953233AEAC37FC114E06CC9C65B1235F8E0B1E3B1142B5CB861BCE6C607DBE3A0804D8C924FA0C81C6EF41AC75270CA6249476ACAC96322D0CB043AE178696156097E7EBA026ACDE0DF56271356D70EA468655CB62D7334EE5D495C904994B14D87CA9F1838B70BC7C5D3479B78FA69BF07EC1789B77A6DF1DA50A52CA8A4BA870A0D8BB4C2FBFE1A03B15F477742EBC0C60345D05A1268F10D0CD3F81DB908825619114E260777A670E21DB8A2F197B87CEAAA3A74FD7C3E42195D5FBD86CB47F8113DB2A8CFD087EB30BB0B4D52A39854E5588521F5061803DF19C3A7806B0A167E0AA6E983F334CA4D339A8771C8E389012AD2FAE5DBDE28622D6F790EAAFB76920BE75232AD9D33CA0E710FF3951F0F0F4D52BF44ADF9CA5B8E7FEDF8D04FBBAC33CCFBCBCDCE167F45F2ED8C7412BB61B713FCBC725266A4FA11F4F60ADAF1806D67ADCF9A66E029293F4F25D62EBDCAD4F4C9DF1087F6D63DCBA5B6F5D73429BCFA046FA87A2AB295732A2E2112F9841A0A271DB1EEA72CEDB39DC7B0B39459B2B202211C574F726D01C72F4C4F0CE2C7AE3B80596009EE2DA6E28D4B0B7DA59BAA4D6B69D6A4582071F652812&si=rf9ml8f&pi=rgnpqvh&di=8d8e7501fce14db17ef3d404f055c7f4&det=1&ts=__TS__&azcx=__AZCX__&azcy=__AZCY__&azmx=__AZMX__&azmy=__AZMY__"],"cpls":[],"dcls":[],"edls":[],"eils":[],"els":["http://stage-adx.8bcd9.com/monitor/common/1?tid=2c852c3a3ff565695b9623f11c7e910e&rsid=RS003&s=1&inf=72107717B779B37F4E3326B64F540B61F991D56D72988BA285785E5B2F2C5A408C114F5D23D3DBCFF67BB1B40FF0353901288506377C7736E4C344EB209F8F2F1FC31EC61740EC11BE691C2423FA345F2A731DCB237F4C2BAEB33ACCF96E069C43516261E4A1ABA8DF97322442762554D5BE4D56F38501627FB4BB519CFBA9075EC1B8101A93AF0909DBA8EF951201445CE6A490E120D703AE69A581CFC83479B3C5754D6995A5541656269033A920D60666900FAEA6BAB0D1DD54A8010CB098082AE474D2B640A75EA2476D6EFD201A9C07D2BD57B0D6D0C67846CBFE6ACEA52999BA13F015B20DFB368CC0808A1D880E9198F80E24D98CD001635EA215B604561CDFAEC102D063DFE021A71C3289251B23439144BD2FF32206A5A343FCCD4A064729C5AE712EC89B29E4E5768C607D19A4E981B711FA635602035981A789DC57F6AEE9AB8BEE7436AC984B9C94101D953233AEAC37FC114E06CC9C65B1235F8E0B1E3B1142B5CB861BCE6C607DBE3A0804D8C924FA0C81C6EF41AC75270CA6249476ACAC96322D0CB043AE178696156097E7EBA026ACDE0DF56271356D70EA468655CB62D7334EE5D495C904994B14D87CA9F1838B70BC7C5D3479B78FA69BF07EC1789B77A6DF1DA50A52CA8A4BA870A0D8BB4C2FBFE1A03B15F477742EBC0C60345D05A1268F10D0CD3F81DB908825619114E260777A670E21DB8A2F197B87CEAAA3A74FD7C3E42195D5FBD86CB47F8113DB2A8CFD087EB30BB0B4D52A39854E5588521F5061803DF19C3A7806B0A167E0AA6E983F334CA4D339A8771C8E389012AD2FAE5DBDE28622D6F790EAAFB76920BE75232AD9D33CA0E710FF3951F0F0F4D52BF44ADF9CA5B8E7FEDF8D04FBBAC33CCFBCBCDCE167F45F2ED8C7412BB61B713FCBC725266A4FA11F4F60ADAF1806D67ADCF9A66E029293F4F25D62EBDCAD4F4C9DF1087F6D63DCBA5B6F5D73429BCFA046FA87A2AB295732A2E2112F9841A0A271DB1EEA72CEDB39DC7B0B39459B2B202211C574F726D01C72F4C4F0CE2C7AE3B80596009EE2DA6E28D4B0B7DA59BAA4D6B69D6A4582071F652812&si=rf9ml8f&pi=rgnpqvh&di=8d8e7501fce14db17ef3d404f055c7f4&det=1&ts=__TS__"],"epls":[],"fpls":{},"gpls":[],"ials":[],"mpls":[],"ppls":[],"sdls":[],"sils":[],"skls":[],"spls":[]},"id":"2c852c3a3ff565695b9623f11c7e910e","impid":"2c852c3a3ff565695b9623f11c7e910e","lurl":"","native":null,"nurl":"","pluginInfo":null,"price":2000.0,"target":"https://android.myapp.com/myapp/detail.htm?apkName=com.caishi.cronus","video":null}],"group":0,"seat":"8d8e7501fce14db17ef3d404f055c7f4"}]}
    }
}
