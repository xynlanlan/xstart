package com.example.start.module.dao;

import com.example.start.module.entity.SysResources;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysResourcesMapper {
    /**
     * sys_resources
     */
    int deleteByPK(Long id);

    /**
     * sys_resources
     */
    int batchDelete(@Param("ids") List ids);

    /**
     * sys_resources
     */
    int insert(SysResources record);

    /**
     * sys_resources
     */
    int insertSelective(SysResources record);

    /**
     * sys_resources
     */
    SysResources findByPK(Long id);

    /**
     * sys_resources
     */
    List<SysResources> find(SysResources record);

    /**
     * sys_resources
     */
    int updateByPkSelective(SysResources record);

    /**
     * sys_resources
     */
    int updateByPK(SysResources record);
}