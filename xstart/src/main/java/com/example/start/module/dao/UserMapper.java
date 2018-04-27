package com.example.start.module.dao;

import com.example.start.module.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
