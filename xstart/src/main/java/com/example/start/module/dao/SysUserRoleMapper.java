package com.example.start.module.dao;

import com.example.start.module.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRoleMapper {
    /**
     * sys_user_role
     */
    int deleteByPK(Long id);

    /**
     * sys_user_role
     */
    int batchDelete(@Param("ids") List ids);

    /**
     * sys_user_role
     */
    int insert(SysUserRole record);

    /**
     * sys_user_role
     */
    int insertSelective(SysUserRole record);

    /**
     * sys_user_role
     */
    SysUserRole findByPK(Long id);

    /**
     * sys_user_role
     */
    List<SysUserRole> find(SysUserRole record);

    /**
     * sys_user_role
     */
    int updateByPkSelective(SysUserRole record);

    /**
     * sys_user_role
     */
    int updateByPK(SysUserRole record);
}