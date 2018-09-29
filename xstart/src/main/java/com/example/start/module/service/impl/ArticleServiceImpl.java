package com.example.start.module.service.impl;

import com.example.start.common.base.Pager;
import com.example.start.common.exception.ExceptionCode;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.utils.EntityUtils;
import com.example.start.module.dao.ArticleMapper;
import com.example.start.module.entity.Article;
import com.example.start.module.service.ArticleService;
import com.example.start.module.service.PaserHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
    private ArticleMapper articleMapper;

    @Override
    public Pager<Article> findByPager(Pager<Article> pager) throws ServiceException {

        return pager.pagingQuery(new Pager.Callback<Article>() {
            @Override
            public List<Article> query(Article entity) throws ServiceException {
                return articleMapper.find(entity);
            }
        });
    }
    
    @Override
    public Article findOne(Long id) throws ServiceException {
    	try{
    		 return articleMapper.findByPK(id);
    	}catch(Exception e){
    		throw new ServiceException("[Query has error]", e);
    	}       
    }

    @Transactional
    @Override
    public int add(Article entity) throws ServiceException {
    	try{
            EntityUtils.createEntity(entity);
    		return articleMapper.insertSelective(entity);
    	}catch(Exception e){
    		throw new ServiceException("[Save has error]", e);
    	}       
    }
    @Transactional
 	@Override
    public int update(Article entity) throws ServiceException {
    	try{
            EntityUtils.updateEntity(entity);
    		return articleMapper.updateByPkSelective(entity);
	    }catch(Exception e){
			throw new ServiceException("[Update has error]", e);
		}       
    }

    @Transactional
    @Override
    public int delete(Long id) throws ServiceException {
        return articleMapper.deleteByPK(id);
    }

    @Transactional
    @Override
    public int reptile(Long id) throws ServiceException {
        Article article = articleMapper.findByPK(id);
        if(article == null){
            throw new ServiceException(ExceptionCode.NOT_DATA);
        }
        if(article.getReptile()){
            throw new ServiceException(ExceptionCode.INVALID_OPT.getCode(), "数据已抓取,无需重复抓取！");
        }
        try{
            Article info = PaserHelper.paser(article);
            if(StringUtils.isBlank(info.getBodyText())){
                throw new ServiceException(ExceptionCode.UNKNOW_ERROR.getCode(), "未读取到数据！");
            }
            info.setReptile(true);
            EntityUtils.updateEntity(info);
            return articleMapper.updateByPkSelective(info);
        }catch(Exception e){
            throw new ServiceException("[reptile has error]", e);
        }
    }

    @Transactional
    @Override
    public int reptileAll() throws ServiceException {
        Article queryArticle = new Article();
        queryArticle.setReptile(false);
        List<Article> list = articleMapper.find(queryArticle);
        int count = 0;
        try{
            for(Article article : list){
                Article info = PaserHelper.paser(article);
                if(StringUtils.isBlank(info.getBodyText())){
                    continue;
                }
                EntityUtils.updateEntity(info);
                article.setReptile(true);
                count += articleMapper.updateByPkSelective(info);
            }
            return count;
        }catch(Exception e){
            throw new ServiceException("[reptileAll has error]", e);
        }
    }
}
