package com.example.start.module.entity;

import com.example.start.common.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SysRoleResources extends BaseEntity {
    /**
     * 
     * sys_role_resources.id
     */
    private Long id;

    /**
     * 
     * sys_role_resources.resources_id
     */
    private Long resourcesId;

    /**
     * 
     * sys_role_resources.role_id
     */
    private Long roleId;

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
    public Long getResourcesId() {
        return resourcesId;
    }

    /**
     * 
     * @param resourcesId * resourcesId
     */
    public void setResourcesId(Long resourcesId) {
        this.resourcesId = resourcesId;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}