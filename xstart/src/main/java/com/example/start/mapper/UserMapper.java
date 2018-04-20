package com.example.start.mapper;

import com.example.start.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xueyn on 2018/4/20.
 */
public interface UserMapper{

    User findByPK(Long id);

    List<User> find(User user);

    int deleteByPK(Long id);

    int batchDelete(@Param("ids") List ids);

    int insert(User user);

    int insertSelective(User user);

    int updateByPkSelective(User user);

    int updateByPK(User user);
}
