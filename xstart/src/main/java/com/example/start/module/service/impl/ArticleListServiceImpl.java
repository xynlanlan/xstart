package com.example.start.module.service.impl;

import com.example.start.common.base.Pager;
import com.example.start.common.exception.ExceptionCode;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.utils.EntityUtils;
import com.example.start.module.dao.ArticleInfoMapper;
import com.example.start.module.dao.ArticleListMapper;
import com.example.start.module.entity.ArticleInfo;
import com.example.start.module.entity.ArticleList;
import com.example.start.module.service.ArticleListService;
import com.example.start.module.service.PaserHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Component
public class ArticleListServiceImpl implements ArticleListService {
	
	@Autowired
    private ArticleListMapper articleListMapper;
    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    @Override
    public Pager<ArticleList> findByPager(Pager<ArticleList> pager) throws ServiceException {

        return pager.pagingQuery(new Pager.Callback<ArticleList>() {
            @Override
            public List<ArticleList> query(ArticleList entity) throws ServiceException {
                return articleListMapper.find(entity);
            }
        });
    }
    
    @Override
    public ArticleList findOne(Long id) throws ServiceException {
    	try{
    		 return articleListMapper.findByPK(id);
    	}catch(Exception e){
    		throw new ServiceException("[Query has error]", e);
    	}       
    }
    @Transactional
    @Override
    public int add(ArticleList entity) throws ServiceException {
    	try{
            entity.setIsReptile(false);
            EntityUtils.createEntity(entity);
    		return articleListMapper.insertSelective(entity);
    	}catch(Exception e){
    		throw new ServiceException("[Save has error]", e);
    	}       
    }
    @Transactional
 	@Override
    public int update(ArticleList entity) throws ServiceException {
    	try{
            EntityUtils.updateEntity(entity);
    		return articleListMapper.updateByPkSelective(entity);
	    }catch(Exception e){
			throw new ServiceException("[Update has error]", e);
		}       
    }
    @Transactional
    @Override
    public int delete(Long id) throws ServiceException {
        return articleListMapper.deleteByPK(id);
    }
    @Transactional
    @Override
    public int reptile(Long id) throws ServiceException {
        ArticleList articleList = articleListMapper.findByPK(id);
        if(articleList == null){
            throw new ServiceException(ExceptionCode.NOT_DATA);
        }
        if(articleList.getIsReptile()){
            throw new ServiceException(ExceptionCode.INVALID_OPT.getCode(), "数据已抓取,无需重复抓取！");
        }
        try{
            ArticleInfo info = PaserHelper.paser(articleList.getId(), articleList.getUrl());
            if(StringUtils.isBlank(info.getBody())){
                throw new ServiceException(ExceptionCode.UNKNOW_ERROR.getCode(), "未读取到数据！");
            }
            EntityUtils.createEntity(info);
            articleInfoMapper.insertSelective(info);
            articleList.setIsReptile(true);
            return articleListMapper.updateByPkSelective(articleList);
        }catch(Exception e){
            throw new ServiceException("[reptile has error]", e);
        }
    }

    @Override
    public int reptileAll() throws ServiceException {
        ArticleList queryArticleList = new ArticleList();
        queryArticleList.setIsReptile(false);
        List<ArticleList> list = articleListMapper.find(queryArticleList);
        int count = 0;
        try{
            for(ArticleList articleList : list){
                ArticleInfo info = PaserHelper.paser(articleList.getId(), articleList.getUrl());
                if(StringUtils.isBlank(info.getBody())){
                    continue;
                }
                EntityUtils.createEntity(info);
                articleInfoMapper.insertSelective(info);
                articleList.setIsReptile(true);
                count += articleListMapper.updateByPkSelective(articleList);
            }
            return count;
        }catch(Exception e){
            throw new ServiceException("[reptile has error]", e);
        }
    }
}
