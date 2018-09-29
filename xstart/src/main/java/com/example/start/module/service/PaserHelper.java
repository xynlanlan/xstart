package com.example.start.module.service;

import com.example.start.module.entity.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.text.SimpleDateFormat;

public class PaserHelper {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 解析
    public static Article paser(Article info){
        //String title = null;
        Document doc = null;
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setTimeout(100000);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.addRequestHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3192.0 Safari/537.36");
        webClient.waitForBackgroundJavaScript(20000);

        try {
            HtmlPage page = webClient.getPage(info.getUrl());
            doc = Jsoup.parse(page.asXml());
            Elements box = doc.getElementsByClass("article-box");
            if(box.size() == 0){
                return info;
            }
            Element element = box.get(0);
            Elements article_sub = element.getElementsByClass("article-sub");
            if(article_sub.size() != 0){
                info.setWriter(article_sub.get(0).text().substring(0, article_sub.get(0).text().indexOf(" ")));
                String publishTime = article_sub.get(0).text().substring(article_sub.get(0).text().indexOf(" ") + 1);
                info.setPublishTime(sdf.parse(publishTime));
            }

            element.getElementsByClass("article-tag").remove();
            info.setBodyText(element.text());
            info.setBodyHtml(element.html());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

}
