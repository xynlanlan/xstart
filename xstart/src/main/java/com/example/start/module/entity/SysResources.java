package com.example.start.module.entity;

import com.example.start.common.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class SysResources extends BaseEntity {
    /**
     * 
     * sys_resources.id
     */
    private Long id;

    /**
     * 菜单名
     * sys_resources.menu_name
     */
    private String menuName;

    /**
     * 菜单路径
     * sys_resources.menu_path
     */
    private String menuPath;

    /**
     * 图标class
     * sys_resources.icon
     */
    private String icon;

    /**
     * 父id
     * sys_resources.parent_id
     */
    private Long parentId;

    /**
     * 排序
     * sys_resources.sort
     */
    private Integer sort;

    /**
     * 是否禁用(0:启用 1:禁用)
     * sys_resources.disabled
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
     * 菜单名
     * @return the value of java.lang.String
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 菜单名
     * @param menuName * menuName
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 菜单路径
     * @return the value of java.lang.String
     */
    public String getMenuPath() {
        return menuPath;
    }

    /**
     * 菜单路径
     * @param menuPath * menuPath
     */
    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath == null ? null : menuPath.trim();
    }

    /**
     * 图标class
     * @return the value of java.lang.String
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 图标class
     * @param icon * icon
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 父id
     * @return the value of java.lang.Long
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 父id
     * @param parentId * parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 排序
     * @return the value of java.lang.Integer
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序
     * @param sort * sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}