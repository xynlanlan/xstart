package com.example.start.api.v100.dto;


import com.example.start.common.entity.BaseEntity;

public class DecodeUserInfo extends BaseEntity {
    /**
     * 明文,加密数据
     */
    private String encryptedData;
    /**
     * 加密算法的初始向量
     */
    private String iv;
    /**
     * 用户允许登录后，回调内容会带上 code（有效期五分钟），
     * 开发者需要将 code 发送到开发者服务器后台，
     * 使用code 换取 session_key api，将 code 换成 openid 和 session_key
     */
    private String code;

    public String getEncryptedData() {
        return encryptedData;
    }
    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }
    public String getIv() {
        return iv;
    }
    public void setIv(String iv) {
        this.iv = iv;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    @Override
    public String toString() {
        return "DecodeUserInfo================== [encryptedData=" + encryptedData + ", iv=" + iv + ", code=" + code + "]";
    }



}