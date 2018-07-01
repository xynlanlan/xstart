package com.example.start.module.dao;

import com.example.start.module.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper {
    /**
     * sys_role
     */
    int deleteByPK(Long id);

    /**
     * sys_role
     */
    int batchDelete(@Param("ids") List ids);

    /**
     * sys_role
     */
    int insert(SysRole record);

    /**
     * sys_role
     */
    int insertSelective(SysRole record);

    /**
     * sys_role
     */
    SysRole findByPK(Long id);

    /**
     * sys_role
     */
    List<SysRole> find(SysRole record);

    /**
     * sys_role
     */
    int updateByPkSelective(SysRole record);

    /**
     * sys_role
     */
    int updateByPK(SysRole record);

    int findRoleNameCount(@Param("id") Long id,@Param("roleName")  String roleName);
}