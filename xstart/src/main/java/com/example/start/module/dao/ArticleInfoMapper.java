package com.example.start.module.dao;

import com.example.start.module.entity.ArticleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleInfoMapper {
    /**
     * t_article_info
     */
    int deleteByPK(Long id);

    /**
     * t_article_info
     */
    int batchDelete(@Param("ids") List ids);

    /**
     * t_article_info
     */
    int insert(ArticleInfo record);

    /**
     * t_article_info
     */
    int insertSelective(ArticleInfo record);

    /**
     * t_article_info
     */
    ArticleInfo findByPK(Long id);

    /**
     * t_article_info
     */
    List<ArticleInfo> find(ArticleInfo record);

    /**
     * t_article_info
     */
    int updateByPkSelective(ArticleInfo record);

    /**
     * t_article_info
     */
    int updateByPK(ArticleInfo record);
}