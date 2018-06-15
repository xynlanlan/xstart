package com.example.start.common.utils;


import com.example.start.common.entity.BaseEntity;

import java.util.Date;

public class EntityUtils {
    public static void createEntity(BaseEntity entity){
        entity.setDel(false);
        entity.setCreateBy(1L);
        entity.setCreateTime(new Date());
        entity.setUpdateBy(1L);
        entity.setUpdateTime(new Date());
    }
    public static void updateEntity(BaseEntity entity){
        entity.setUpdateBy(1L);
        entity.setUpdateTime(new Date());
    }
}
