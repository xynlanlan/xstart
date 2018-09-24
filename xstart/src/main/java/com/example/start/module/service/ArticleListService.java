package com.example.start.module.service;

import com.example.start.common.base.Pager;
import com.example.start.module.entity.ArticleList;
import com.example.start.common.exception.ServiceException;

public interface ArticleListService {
	

    Pager<ArticleList> findByPager(Pager<ArticleList> pager) throws ServiceException;
    
    
    ArticleList findOne(Long id) throws ServiceException;
    

    int add(ArticleList entity) throws ServiceException;
    
  
    int update(ArticleList entity) throws ServiceException;  

 
    int delete(Long id) throws ServiceException;

    int reptile(Long id) throws ServiceException;

    int reptileAll() throws ServiceException;
}
