package com.example.start.module.entity;

import com.example.start.common.entity.BaseEntity;
import java.util.Date;

public class SysUser extends BaseEntity {
    /**
     * 
     * sys_user.id
     */
    private Long id;

    /**
     * 登录账号
     * sys_user.login_account
     */
    private String loginAccount;

    /**
     * 用户名
     * sys_user.user_name
     */
    private String userName;

    /**
     * 密码
     * sys_user.password
     */
    private String password;

    /**
     * 性别(0:男 1:女)
     * sys_user.sex
     */
    private Boolean sex;

    /**
     * 手机号
     * sys_user.phone
     */
    private String phone;

    /**
     * 邮箱
     * sys_user.email
     */
    private String email;

    /**
     * 出生时间
     * sys_user.birth_date
     */
    private Date birthDate;

    /**
     * 是否禁用(0:启用 1:禁用)
     * sys_user.disable
     */
    private Boolean disable;

    /**
     * 是否删除(0: 未删除 1:已删除)
     * sys_user.is_del
     */
    private Boolean isDel;

    /**
     * 创建人
     * sys_user.create_by
     */
    private Long createBy;

    /**
     * 创建时间
     * sys_user.create_time
     */
    private Date createTime;

    /**
     * 修改人
     * sys_user.update_by
     */
    private Long updateBy;

    /**
     * 修改时间
     * sys_user.update_time
     */
    private Date updateTime;

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
     * 登录账号
     * @return the value of java.lang.String
     */
    public String getLoginAccount() {
        return loginAccount;
    }

    /**
     * 登录账号
     * @param loginAccount * loginAccount
     */
    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount == null ? null : loginAccount.trim();
    }

    /**
     * 用户名
     * @return the value of java.lang.String
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     * @param userName * userName
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 密码
     * @return the value of java.lang.String
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password * password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 性别(0:男 1:女)
     * @return the value of java.lang.Boolean
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * 性别(0:男 1:女)
     * @param sex * sex
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /**
     * 手机号
     * @return the value of java.lang.String
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号
     * @param phone * phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 邮箱
     * @return the value of java.lang.String
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @param email * email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 出生时间
     * @return the value of java.util.Date
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * 出生时间
     * @param birthDate * birthDate
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * 是否禁用(0:启用 1:禁用)
     * @return the value of java.lang.Boolean
     */
    public Boolean getDisable() {
        return disable;
    }

    /**
     * 是否禁用(0:启用 1:禁用)
     * @param disable * disable
     */
    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    /**
     * 是否删除(0: 未删除 1:已删除)
     * @return the value of java.lang.Boolean
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     * 是否删除(0: 未删除 1:已删除)
     * @param isDel * isDel
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
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