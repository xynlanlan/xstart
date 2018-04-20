package com.example.start.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xueyn on 2018/4/20.
 */
@Data
public class User implements Serializable {
    private Long id;
    private String userName;
    private String nickName;
    private String password;
}
