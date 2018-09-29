package com.example.start.jsoup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public class ToutiaoImgCrawler {
    private static final Logger logger = LoggerFactory.getLogger(ToutiaoImgCrawler.class);

    private static String getToutiaoUrl(String baseUrl, int offset, String keyword, String cur_tab, String from) throws IOException {

        Map<String, String> map = new HashMap<String, String>();
        map.put("offset", String.valueOf(offset));
        map.put("format","json");
        map.put("keyword", keyword);
        map.put("autoload","true");
        map.put("count", "20");
        map.put("cur_tab", cur_tab);
        map.put("from", from);
        return CrawlerHelper.getUrl(baseUrl, map, "UTF-8");
    }


    /**
     *
     * @param baseUrl
     * @param keyword 关键词
     * @param Number 标题数,一个标题有多张图片
     * @param cur_tab 分类
     * @throws IOException
     */
    public static void toutiaoImgCrawler(String baseUrl, String keyword, int Number, String cur_tab,String from){
        int offset = 0;
        while (Number > 0) {
            try {
                String url = getToutiaoUrl(baseUrl, offset, keyword, cur_tab, from);

                Connection.Response response = CrawlerHelper.connect(url);

                JSONObject jsonObject = JSONObject.parseObject(response.body());
                JSONArray jsonArray = jsonObject.getJSONArray("data");

                for (int i=0; i < jsonArray.size(); i++) {

                    JSONObject object1 = jsonArray.getJSONObject(i);
                    JSONArray jsonArray1 = object1.getJSONArray("image_list");
                    String imgName = object1.getString("media_name");
                    if(jsonArray1 == null){
                        continue;
                    }
                    for (int j=0; j < jsonArray1.size(); j++) {

                        JSONObject object2 = jsonArray1.getJSONObject(j);
                        String imgUrl = object2.getString("jsoup");
                        if(imgUrl.indexOf("http:") < 0){
                            imgUrl = "http:"+imgUrl;
                        }
                        CrawlerHelper.DonloadImg(imgUrl, imgName+j);
                    }
                }
            } catch (Exception e) {
                logger.error("==========toutiaoImgCrawler异常",e.getMessage());
            }
            offset += 20;
            Number -= 20;
        }
    }

}