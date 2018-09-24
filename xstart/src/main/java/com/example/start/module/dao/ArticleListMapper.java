package com.example.start.module.dao;

import com.example.start.module.entity.ArticleList;

import java.util.List;

public interface ArticleListMapper {
    /**
     * t_article_list
     */
    int deleteByPK(Long id);
    /**
     * t_article_list
     */
    int insert(ArticleList record);

    /**
     * t_article_list
     */
    int insertSelective(ArticleList record);

    /**
     * t_article_list
     */
    List<ArticleList> find(ArticleList record);

    /**
     * t_article_list
     */
    ArticleList findByPK(Long id);

    /**
     * t_article_list
     */
    int updateByPkSelective(ArticleList record);

    /**
     * t_article_list
     */
    int updateByPK(ArticleList record);
}