package com.example.start.module.dao;

import com.example.start.module.entity.Article;

import java.util.List;

public interface ArticleMapper {
    /**
     * t_article
     */
    int deleteByPK(Long id);
    /**
     * t_article
     */
    int insert(Article record);

    /**
     * t_article
     */
    int insertSelective(Article record);

    /**
     * t_article
     */
    List<Article> find(Article record);

    /**
     * t_article
     */
    Article findByPK(Long id);

    /**
     * t_article
     */
    int updateByPkSelective(Article record);

    /**
     * t_article
     */
    int updateByPK(Article record);
}