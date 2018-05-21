package com.example.start.module.dao;

import com.example.start.module.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper {
    /**
     * sys_user
     */
    int deleteByPK(Long id);

    /**
     * sys_user
     */
    int batchDelete(@Param("ids") List ids);

    /**
     * sys_user
     */
    int insert(SysUser record);

    /**
     * sys_user
     */
    int insertSelective(SysUser record);

    /**
     * sys_user
     */
    SysUser findByPK(Long id);

    /**
     * sys_user
     */
    List<SysUser> find(SysUser record);

    /**
     * sys_user
     */
    int updateByPkSelective(SysUser record);

    /**
     * sys_user
     */
    int updateByPK(SysUser record);

    SysUser findByName(@Param("name") String name);

    SysUser findByAccount(@Param("account") String Account);
}