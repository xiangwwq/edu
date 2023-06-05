package com.edu.mapper;

import com.edu.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author aaa
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-05-12 18:16:13
* @Entity com.edu.dao.User
*/
@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    User QueryUser(User user);

    List<User> selectAll();

    List<User> QueryUserByName(@Param("name") String name);

}
