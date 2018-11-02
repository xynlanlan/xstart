package com.example.start.module.controller;


import com.example.start.common.base.BaseController;
import com.example.start.common.base.Pager;
import com.example.start.common.exception.ServiceException;
import com.example.start.common.interceptor.RequiredPermission;
import com.example.start.module.entity.SystemLog;
import com.mongodb.WriteResult;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RestController
@RequestMapping("api/systemlog")
public class SystemLogController extends BaseController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Map<String, Object> list(@RequestBody Pager<SystemLog> pager) throws ServiceException {
        Query query = new Query();
        query.skip((pager.getPageIndex()- 1) * pager.getPageSize());
        query.limit(pager.getPageSize());
        List<SystemLog> list = mongoTemplate.find(query, SystemLog.class);
        return success(list);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> add(@RequestBody SystemLog entity) throws ServiceException {
        mongoTemplate.insert(entity);
        return success();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Map<String, Object> update(@RequestBody SystemLog entity) throws ServiceException {
        Criteria criteria=where("id").in(entity.getId());
        Update update=new Update();
        update.set("mothed",entity.getMothed())
                .set("ip",entity.getIp())
                .set("params",entity.getParams())
                .set("result",entity.getResult());
        UpdateResult result=mongoTemplate.updateFirst(query(criteria),update,SystemLog.class);
        return success(result.getModifiedCount());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@PathVariable("id") Long id) throws ServiceException {
        Criteria criteria=where("id").is(id);
        DeleteResult result=mongoTemplate.remove(query(criteria),SystemLog.class);
        return success(result.getDeletedCount());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Map<String, Object> get(@PathVariable("id") Long id) throws ServiceException {
        return success(mongoTemplate.findOne(new Query(where("id").is(id)), SystemLog.class));
    }
}
