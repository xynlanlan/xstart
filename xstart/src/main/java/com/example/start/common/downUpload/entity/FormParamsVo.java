package com.example.start.common.downUpload.entity;

import com.example.start.module.entity.SysUser;

import java.io.Serializable;

/**
 * excle下载参数
 */
public class FormParamsVo implements Serializable {
    private SysUser sysUser;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
