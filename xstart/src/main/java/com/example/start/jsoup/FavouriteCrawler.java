package com.example.start.jsoup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FavouriteCrawler {
    private static final Logger logger = LoggerFactory.getLogger(FavouriteCrawler.class);

    public static void favouriteCrawler(String url,String cookie){
        try {
            Connection.Response response = connect(url,cookie);

            JSONObject jsonObject = JSONObject.parseObject(response.body());
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            if(jsonArray == null || jsonArray.size()==0){
                return;
            }
            for (int i=0; i < jsonArray.size(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                //标题
                String title = json.getString("title");
                //文章链接
                String source_url = json.getString("source_url");
                //文章类型（文章/视频）
                String article_genre = json.getString("article_genre");
                //作者
                String source = json.getString("source");
                //作者链接
                String media_url = json.getString("media_url");

                System.out.println(title + "   " + source_url);
            }
        } catch (Exception e) {
            logger.error("==========favouriteCrawler异常",e.getMessage());
        }
    }
    public static Connection.Response connect(String url,String cookie) throws IOException {
        return Jsoup.connect(url)
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                .header("cookie", cookie)
                .timeout(10000).ignoreContentType(true)
                .execute();
    }
}