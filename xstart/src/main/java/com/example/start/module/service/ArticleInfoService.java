package com.example.start.module.service;

import com.example.start.common.base.Pager;
import com.example.start.module.entity.ArticleInfo;
import com.example.start.common.exception.ServiceException;

public interface ArticleInfoService {
	

    Pager<ArticleInfo> findByPager(Pager<ArticleInfo> pager) throws ServiceException;
    
    
    ArticleInfo findOne(Long id) throws ServiceException;
    

    int add(ArticleInfo entity) throws ServiceException;
    
  
    int update(ArticleInfo entity) throws ServiceException;  

 
    int delete(Long id) throws ServiceException;

}
