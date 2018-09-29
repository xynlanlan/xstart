package com.example.start.jsoup;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CrawlerHelper {
    private static final Logger logger = LoggerFactory.getLogger(CrawlerHelper.class);

    public static String getUrl(String url, Map<String, String> params, String charset) {
        if (params != null) {
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, String> entry = iter.next();
                String key = entry.getKey();
                String value = entry.getValue();
                paramList.add(new BasicNameValuePair(key, value));
            }
            try {
                String paramStr = EntityUtils.toString(new UrlEncodedFormEntity(paramList, charset));
                StringBuffer sb = new StringBuffer();
                sb.append(url);
                if (url.indexOf("?") > 0) {
                    sb.append("&");
                } else {
                    sb.append("?");
                }
                sb.append(paramStr);
                url = sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return url;
    }

    public static void DonloadImg(String url, String imgName) throws IOException {

        //String base = "/home/liyonghao/Pictures/";
        String base = "D://downP/";
        Connection.Response response = null;
        try {
            response = connect(url);
        } catch (Exception e) {
            logger.error("=========DonloadImg异常:" + url + "  " + imgName,e.getMessage());
        }
        if(response == null){
            DonloadImg(url,imgName);
        }

        String returnType = response.contentType().substring(response.contentType().lastIndexOf("/")+1);
        byte[] img = response.bodyAsBytes();
        String filename = base + imgName + "." + returnType;
        System.out.println(url+"   "+filename);

        File file = new File(filename);
        if (file.exists()) {
            return;
        }
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos =  new BufferedOutputStream(fos);
        bos.write(img);
        bos.flush();
        bos.close();
        fos.close();
    }

    public static Connection.Response connect(String url) throws IOException {
        return Jsoup.connect(url)
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                .timeout(10000).ignoreContentType(true)
                .execute();
    }
}