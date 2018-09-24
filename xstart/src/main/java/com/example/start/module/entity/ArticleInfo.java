package com.example.start.module.entity;

import com.example.start.common.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class ArticleInfo extends BaseEntity {
    /**
     * 
     * t_article_info.id
     */
    private Long id;

    /**
     * 文章id
     * t_article_info.article_id
     */
    private Long articleId;

    /**
     * 作者
     * t_article_info.writer
     */
    private String writer;

    /**
     * 发布日期
     * t_article_info.publish_time
     */
    private Date publishTime;

    /**
     * 是否禁用(0:启用 1:禁用)
     * t_article_info.disabled
     */
    private Boolean disabled;

    /**
     * 文章内容
     * t_article_info.body
     */
    private String body;

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
     * 文章id
     * @return the value of java.lang.Long
     */
    public Long getArticleId() {
        return articleId;
    }

    /**
     * 文章id
     * @param articleId * articleId
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    /**
     * 作者
     * @return the value of java.lang.String
     */
    public String getWriter() {
        return writer;
    }

    /**
     * 作者
     * @param writer * writer
     */
    public void setWriter(String writer) {
        this.writer = writer == null ? null : writer.trim();
    }

    /**
     * 发布日期
     * @return the value of java.util.Date
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * 发布日期
     * @param publishTime * publishTime
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
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

    /**
     * 文章内容
     * @return the value of java.lang.String
     */
    public String getBody() {
        return body;
    }

    /**
     * 文章内容
     * @param body * body
     */
    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}