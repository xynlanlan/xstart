package com.example.start.module.entity;

import com.example.start.common.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ArticleList extends BaseEntity {
    /**
     * 
     * t_article_list.id
     */
    private Long id;

    /**
     * 标题
     * t_article_list.title
     */
    private String title;

    /**
     * 链接
     * t_article_list.url
     */
    private String url;

    /**
     * 是否爬取(0:未爬取 1:已爬取)
     * t_article_list.is_reptile
     */
    private Boolean isReptile;

    /**
     * 是否禁用(0:启用 1:禁用)
     * t_article_list.disabled
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
     * @param url * url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 是否爬取(0:未爬取 1:已爬取)
     * @return the value of java.lang.Boolean
     */
    public Boolean getIsReptile() {
        return isReptile;
    }

    /**
     * 是否爬取(0:未爬取 1:已爬取)
     * @param isReptile * isReptile
     */
    public void setIsReptile(Boolean isReptile) {
        this.isReptile = isReptile;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}