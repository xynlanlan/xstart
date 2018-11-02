package com.example.start.module.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "t_system_log")
public class SystemLog {
    private Long id;
    private String mothed;
    private String ip;
    private String params;
    private String result;

    public SystemLog() {
    }

    public SystemLog(Long id, String mothed, String ip, String params, String result) {
        this.id = id;
        this.mothed = mothed;
        this.ip = ip;
        this.params = params;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMothed() {
        return mothed;
    }

    public void setMothed(String mothed) {
        this.mothed = mothed;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
