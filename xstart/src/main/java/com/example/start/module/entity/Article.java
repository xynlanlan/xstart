package com.example.start.module.entity;

import com.example.start.common.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class Article extends BaseEntity {
    /**
     * 
     * t_article.id
     */
    private Long id;

    /**
     * 标题
     * t_article.title
     */
    private String title;

    /**
     * 链接
     * t_article.jsoup
     */
    private String url;
    /**
     * 作者
     * t_article.writer
     */
    private String writer;

    /**
     * 发布日期
     * t_article.publish_time
     */
    private Date publishTime;
    /**
     * 文章内容(文本)
     * t_article.body_text
     */
    private String bodyText;
    /**
     * 文章内容(html)
     * t_article.body_html
     */
    private String bodyHtml;
    /**
     * 是否爬取(0:未爬取 1:已爬取)
     * t_article.is_reptile
     */
    private Boolean isReptile;

    /**
     * 是否禁用(0:启用 1:禁用)
     * t_article.disabled
     */
    private Boolean disabled;

    /**
     * 
     * @return the value of java.lang.Long
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id * id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 标题
     * @return the value of java.lang.String
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     * @param title * title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 链接
     * @return the value of java.lang.String
     */
    public String getUrl() {
        return url;
    }

    /**
     * 链接
     * @param url * jsoup
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 是否爬取(0:未爬取 1:已爬取)
     * @return
     */
    public Boolean getReptile() {
        return isReptile;
    }

    /**
     * 是否爬取(0:未爬取 1:已爬取)
     * @param reptile
     */
    public void setReptile(Boolean reptile) {
        isReptile = reptile;
    }

    /**
     * 是否禁用(0:启用 1:禁用)
     * @return the value of java.lang.Boolean
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /**
     * 是否禁用(0:启用 1:禁用)
     * @param disabled * disabled
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public String getBodyHtml() {
        return bodyHtml;
    }

    public void setBodyHtml(String bodyHtml) {
        this.bodyHtml = bodyHtml;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}