package com.example.start.module.service;

import com.example.start.common.base.Pager;
import com.example.start.module.entity.Article;
import com.example.start.common.exception.ServiceException;

public interface ArticleService {
	

    Pager<Article> findByPager(Pager<Article> pager) throws ServiceException;
    
    
    Article findOne(Long id) throws ServiceException;
    

    int add(Article entity) throws ServiceException;
    
  
    int update(Article entity) throws ServiceException;  

 
    int delete(Long id) throws ServiceException;

    int reptile(Long id) throws ServiceException;

    int reptileAll() throws ServiceException;
}
