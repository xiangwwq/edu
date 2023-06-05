package com.edu.service;

import com.edu.dao.User;
import com.edu.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/12 18:09
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User QueryUserByUid(int uid) {
        User user = userMapper.selectByPrimaryKey((long) uid);
        return user;
    }


    public User QueryUser(User user) {
        return   userMapper.QueryUser(user);
    }


    public PageInfo<User> QueryUserByName(int currentPage, int pageSize, String name) {
        PageHelper.startPage(currentPage, pageSize);
        List<User> users =  userMapper.QueryUserByName(name);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }


    public List<User> selectAll() {
        return  userMapper.selectAll();
    }


    public boolean insertSelective(User record) {
        int i = userMapper.insertSelective(record);
        return i>0?true:false;
    }


    public boolean deleteByPrimaryKey(Long id) {
        int  i=userMapper.deleteByPrimaryKey(id);
        return i>0?true:false;
    }
}
