package com.example.start.module.service.impl;

import com.example.start.common.base.Pager;
import com.example.start.common.exception.ServiceException;
import com.example.start.module.dao.ArticleInfoMapper;
import com.example.start.module.entity.ArticleInfo;
import com.example.start.module.service.ArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public class ArticleInfoServiceImpl implements ArticleInfoService {
	
	@Autowired
    private ArticleInfoMapper articleInfoMapper;

    @Override
    public Pager<ArticleInfo> findByPager(Pager<ArticleInfo> pager) throws ServiceException {

        return pager.pagingQuery(new Pager.Callback<ArticleInfo>() {
            @Override
            public List<ArticleInfo> query(ArticleInfo entity) throws ServiceException {
                return articleInfoMapper.find(entity);
            }
        });
    }
    
    @Override
    public ArticleInfo findOne(Long id) throws ServiceException {
    	try{
    		 return articleInfoMapper.findByPK(id);
    	}catch(Exception e){
    		throw new ServiceException("[Query has error]", e);
    	}       
    }

    @Override
    public int add(ArticleInfo entity) throws ServiceException {
    	try{
    		return articleInfoMapper.insertSelective(entity);
    	}catch(Exception e){
    		throw new ServiceException("[Save has error]", e);
    	}       
    }

 	@Override
    public int update(ArticleInfo entity) throws ServiceException {
    	try{
    		return articleInfoMapper.updateByPkSelective(entity);
	    }catch(Exception e){
			throw new ServiceException("[Update has error]", e);
		}       
    }   

    @Override
    public int delete(Long id) throws ServiceException {
        return articleInfoMapper.deleteByPK(id);
    }
}
