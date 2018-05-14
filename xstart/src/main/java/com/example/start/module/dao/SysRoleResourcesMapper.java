package com.example.start.module.dao;

import com.example.start.module.entity.SysRoleResources;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleResourcesMapper {
    /**
     * sys_role_resources
     */
    int deleteByPK(Long id);

    /**
     * sys_role_resources
     */
    int batchDelete(@Param("ids") List ids);

    /**
     * sys_role_resources
     */
    int insert(SysRoleResources record);

    /**
     * sys_role_resources
     */
    int insertSelective(SysRoleResources record);

    /**
     * sys_role_resources
     */
    SysRoleResources findByPK(Long id);

    /**
     * sys_role_resources
     */
    List<SysRoleResources> find(SysRoleResources record);

    /**
     * sys_role_resources
     */
    int updateByPkSelective(SysRoleResources record);

    /**
     * sys_role_resources
     */
    int updateByPK(SysRoleResources record);
}