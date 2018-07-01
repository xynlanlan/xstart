package com.example.start.common.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {
    /**
     * 是否删除(0: 未删除 1:已删除)
     * sys_resources.is_del
     */
    private Boolean isDel;

    /**
     *
     * sys_resources.create_by
     */
    private Long createBy;

    /**
     *
     * sys_resources.create_time
     */
    private Date createTime;

    /**
     *
     * sys_resources.update_by
     */
    private Long updateBy;

    /**
     *
     * sys_resources.update_time
     */
    private Date updateTime;


    /**
     * 是否删除(0: 未删除 1:已删除)
     * @return
     */
    public Boolean getDel() {
        return isDel;
    }

    /**
     * 是否删除(0: 未删除 1:已删除)
     * @param del
     */
    public void setDel(Boolean del) {
        isDel = del;
    }

    /**
     * 创建人
     * @return the value of java.lang.Long
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * 创建人
     * @param createBy * createBy
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * 创建时间
     * @return the value of java.util.Date
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime * createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人
     * @return the value of java.lang.Long
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * 修改人
     * @param updateBy * updateBy
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 修改时间
     * @return the value of java.util.Date
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     * @param updateTime * updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
