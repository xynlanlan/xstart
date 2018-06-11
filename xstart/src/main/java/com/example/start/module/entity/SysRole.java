package com.example.start.module.entity;

import com.example.start.common.entity.BaseEntity;
import java.util.Date;

public class SysRole extends BaseEntity {
    /**
     * 
     * sys_role.id
     */
    private Long id;

    /**
     * 角色名
     * sys_role.role_name
     */
    private String roleName;

    /**
     * 别名
     * sys_role.alias
     */
    private String alias;

    /**
     * 简介
     * sys_role.description
     */
    private String description;

    /**
     * 是否禁用(0:启用 1:禁用)
     * sys_role.disabled
     */
    private Boolean disabled;

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
     * 角色名
     * @return the value of java.lang.String
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 角色名
     * @param roleName * roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 别名
     * @return the value of java.lang.String
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 别名
     * @param alias * alias
     */
    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    /**
     * 简介
     * @return the value of java.lang.String
     */
    public String getDescription() {
        return description;
    }

    /**
     * 简介
     * @param description * description
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 是否禁用(0:启用 1:禁用)
     * @return the value of java.lang.Boolean
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /**
     * 是否禁用(0:启用 1:禁用)
     * @param disabled * disabled
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

}