package com.example.start.jsoup;
import java.io.IOException;


public class JsoupTest {


    public static void main(String[] args) throws IOException {

        //String toutiaoBaseUrl = "https://www.toutiao.com/search_content/";

        //Number 标题数
        //分类：1 综合 , 3 图集;  2是视频, 本次不爬
        //String cur_tab = "3";
        //String from="gallery";
        //ToutiaoImgCrawler.toutiaoImgCrawler(toutiaoBaseUrl, "美女", 120, cur_tab, from);

        //String baiduBaseUrl = "https://image.baidu.com/search/acjson";
        //Number 即为图片张数
        //BaiduImgCrawler.baiduImgCrawler(baiduBaseUrl, "美女", 320);
        String favouriteUrl = "https://www.toutiao.com/c/user/favourite/?page_type=2&user_id=3278685708&max_behot_time=0&count=20&as=A1D54B5ACF743FB&cp=5BAF1413AF3B3E1&_signature=XN.TThAYB3EL6JsVXQhS6Fzf01&max_repin_time=1537691223";
        String cookie = "__tasessionId=sg3a1xo3g1538212583920; csrftoken=03941c5836b94510e500083c5c9f9baf; tt_webid=6606572764320187918; uuid=\"w:5f52e23d2f6e4da497c6d8bad545fe9b\"; UM_distinctid=166249d5b7f104-0b6bf4304ad8a1-323b5b03-13c680-166249d5b821bd; CNZZDATA1259612802=1163668721-1538212342-%7C1538212342; ccid=7cf8658f3edaa34732bd88bcd11a546a; login_flag=cdef6b151a8d76b5875be3d4b85e518a; sessionid=29176d2766e561256a78bad8ffb5d3ea; uid_tt=1e7270a246222e329abb24d851c0d91f; sid_tt=29176d2766e561256a78bad8ffb5d3ea; sid_guard=\"29176d2766e561256a78bad8ffb5d3ea|1538212609|15552000|Thu\\054 28-Mar-2019 09:16:49 GMT\"; tt_webid=6606572764320187918; WEATHER_CITY=%E5%8C%97%E4%BA%AC";

        FavouriteCrawler.favouriteCrawler(favouriteUrl,cookie);

    }
}