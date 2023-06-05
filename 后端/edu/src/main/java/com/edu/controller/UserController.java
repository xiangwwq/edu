package com.edu.controller;

import com.edu.commer.R;
import com.edu.dao.User;
import com.edu.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/12 18:06
 */
@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public R login(@RequestBody User user){
        User user1= userService.QueryUser(user);
        if(StringUtils.isEmpty(user1)){
            return R.error().message("手机号或密码错误！！！");
        }else {
            return R.ok().message("登录成功").data("uid",user1.getUid());
        }
    }


    @RequestMapping("{uid}")
    public R test(@PathVariable int uid){
        User user = userService.QueryUserByUid(uid);
        return R.ok().data("user",user);
    }

    @PostMapping("add")
    public R add(@RequestBody User user){
        boolean b = userService.insertSelective(user);
        return b?R.ok():R.error();
    }

    @RequestMapping("del/{uid}")
    public R del(@PathVariable int uid){
        boolean b = userService.deleteByPrimaryKey((long) uid);
        return b? R.ok():R.error();
    }


    @PostMapping("list")
    public R list(@RequestParam( value = "name" ,required = false) String name
            ,@RequestParam( value = "currentPage",required = false,defaultValue = "1") String currentPage
            ,@RequestParam( value = "pageSize",required = false,defaultValue = "10") String pageSize){
        System.out.println("页面长度"+pageSize);
        System.out.println("当前页"+currentPage);
        PageInfo<User> users = userService.QueryUserByName(Integer.parseInt(currentPage),Integer.parseInt(pageSize),name);

        return R.ok().message("分页查询").data("page",users);
    }
}
