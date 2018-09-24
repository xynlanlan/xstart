package com.example.start.module.service;

import com.example.start.module.entity.ArticleInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.text.SimpleDateFormat;

public class PaserHelper {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 解析
    public static ArticleInfo paser(Long articleId, String url){
        //String title = null;
        Document doc = null;
        ArticleInfo info = new ArticleInfo();
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setTimeout(100000);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.addRequestHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3192.0 Safari/537.36");
        webClient.waitForBackgroundJavaScript(20000);

        try {
            HtmlPage page = webClient.getPage(url);
            doc = Jsoup.parse(page.asXml());
            /*Elements article_title = doc.getElementsByClass("article-title");
            if(article_title.size() != 0){
                title = article_title.get(0).text();
            }*/
            Elements article_sub = doc.getElementsByClass("article-sub");

            if(article_sub.size() != 0){
                info.setWriter(article_sub.get(0).text().substring(0, article_sub.get(0).text().indexOf(" ")));
                String publishTime = article_sub.get(0).text().substring(article_sub.get(0).text().indexOf(" ") + 1);
                info.setPublishTime(sdf.parse(publishTime));
            }

            Elements article_content = doc.getElementsByClass("article-content");
            if(article_content.size() != 0){
                info.setBody(article_content.get(0).text());
            }
            info.setArticleId(articleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

}
