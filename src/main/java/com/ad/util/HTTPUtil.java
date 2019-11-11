package com.ad.util;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class HTTPUtil {
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) throws IOException {
        String urlNameString = url + "?" + param;
        URL realUrl = new URL(urlNameString);
        // 打开和URL之间的连接
        java.net.URLConnection connection = realUrl.openConnection();
        // 设置通用的请求属性
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        // 建立实际的连接
        connection.connect();
        // 获取所有响应头字段
        Map<String, List<String>> map = connection.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : map.keySet()) {
            System.out.println(key + "--->" + map.get(key));
        }
        // 定义 BufferedReader输入流来读取URL的响应
        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String line, result = "";
        while ((line = in.readLine()) != null) {
            result += line;
        }

        if (in != null) {
            in.close();
        }

        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url    发送请求的 URL
     * @param params 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param isJson 请求形式，如果为true则表示json请求，如果为false表示form表单请求
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, Map<String, Object> params, boolean isJson) throws IOException {
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        java.net.URLConnection conn = realUrl.openConnection();

        // post参数
        StringBuilder postData;
        if (isJson) {
            postData = new StringBuilder(JSON.toJSONString(params));
        } else {
            postData = new StringBuilder();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0) {
                    postData.append("&");
                }

                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append("=");
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
        }

        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        // 设置通用的请求属性
        conn.setRequestProperty("Content-Type", isJson ?
                "application/json; charset=UTF-8" :
                "application/x-www-form-urlencoded; charset=UTF-8");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));

        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);

        // 获取URLConnection对象对应的输出流
        OutputStream out = conn.getOutputStream();

        // 发送请求参数
        out.write(postDataBytes);
        // flush输出流的缓冲
        out.flush();

        // 定义BufferedReader输入流来读取URL的响应
        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));

        String line, result = "";
        while ((line = in.readLine()) != null) {
            result += line;
        }

        if (out != null) {
            out.close();
        }

        if (in != null) {
            in.close();
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        /*Map params = new HashMap();
        params.put("clientIp", "192.168.31.112");
        params.put("mCount", 1);
        params.put("mStyle", "018");
        params.put("apid", "6qicmykru5iy");
        params.put("packageName", "com.boolbird.dailynews");
        params.put("appVer", "1.2.0");
        params.put("imei", "866174010024327");
        params.put("deviceId", "bf70e32d06a54fb3");
        params.put("mac", "5c%3A0e%3A8b%3Aeb%3A27%3Ab0");
        params.put("os", "Android");
        params.put("osVersion", "8.1.0");
        params.put("network", "4");
        params.put("vendor", "vivo");
        params.put("model", "vivoV3");
        params.put("operater", "46002");
        params.put("userAgent", "Mozilla%2F5.0+(Linux%3B+Android+8.1.0%3B+vivo+V3+Build%2FLMY47V)+AppleWebKit%2F537.36+(KHTML%2C+like+Gecko)+Version%2F4.0+Chrome%2F39.0.0.0+Mobile+Safari%2F537.36");
        params.put("screenWidth", "1800");
        params.put("screenHeight", "1920");
        params.put("position", "陕西省");
        params.put("platform", 1);
        params.put("mWidth", "360");
        params.put("mHeight", "360");
        params.put("isHttps", 0);

        String result = HTTPUtil.sendPost("http://62.234.201.244/export/TPXjOu.api", params);
        System.out.println(result);*/

        String params = "{\"id\":\"8404dde8-b188-11e8-87af-00163e0c1b32\",\"imp\":[{\"id\":\"1\",\"instl\":1,\"tagid\":\"e335620397b\",\"video\":{\"mimes\":[\"video/mp4\"],\"minduration\":0,\"maxduration\":0,\"w\":1280,\"h\":720},\"pmp\":{\"private\":1,\"deals\":{\"id\":\"384tu8rH\",\"bidfloor\":12000,\"bidfloorcur\":\"CNY\",\"at\":3}},\"bidfloor\":0,\"bidfloorcur\":\"CNY\",\"issupportdeeplink\":false,\"exp\":7200}],\"app\":{\"id\":\"1539\",\"name\":\"Sigmob iOSDemo\",\"bundle\":\"791532221\"},\"device\":{\"ua\":\"Mozilla/5.0 (iPhone; CPU iPhone OS 11_4_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15G77\",\"geo\":{\"lat\":23.886566,\"lon\":100.08697,\"country\":\"CN\"},\"ip\":\"61.49.51.158\",\"devicetype\":1,\"make\":\"Apple\",\"model\":\"iPhone11,8\",\"os\":1,\"osv\":\"11.4.1\",\"h\":896,\"w\":414,\"language\":\"zh_CN\",\"carrier\":\"China Mobile\",\"mccmnc\":\"46002\",\"connectiontype\":4,\"ifa\":\"EC90A86C-AF40-40FC-BA84-FCA69020AE55\"},\"at\":1,\"tmax\":2000,\"wseat\":0,\"cur\":\"CNY\",\"wlang\":[\"zh\"]}";
        String result = HTTPUtil.sendPost("http://127.0.0.1:8080/ad/sigmob", JSON.parseObject(params), true);
        System.out.println(result);
    }
}