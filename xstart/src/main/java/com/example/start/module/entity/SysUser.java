package com.example.start.module.entity;

import com.example.start.common.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

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
     * 角色
     */
    private SysRole role;
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

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}