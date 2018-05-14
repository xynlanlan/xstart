package com.example.start.module.entity;

import com.example.start.common.entity.BaseEntity;

public class SysUserRole extends BaseEntity {
    /**
     * 
     * sys_user_role.id
     */
    private Long id;

    /**
     * 
     * sys_user_role.role_id
     */
    private Long roleId;

    /**
     * 
     * sys_user_role.user_id
     */
    private Long userId;

    /**
     * 
     * @return the value of java.lang.Long
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id * id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return the value of java.lang.Long
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 
     * @param roleId * roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 
     * @return the value of java.lang.Long
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId * userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}