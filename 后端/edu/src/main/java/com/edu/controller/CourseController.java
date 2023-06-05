package com.edu.controller;

import com.edu.commer.R;
import com.edu.dao.Courser;
import com.edu.dao.User;
import com.edu.service.CourseService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/12 18:08
 */
@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @RequestMapping("list")
    public R list(@RequestParam( value = "name" ,required = false) String name
            , @RequestParam( value = "currentPage",required = false,defaultValue = "1") String currentPage
            , @RequestParam( value = "pageSize",required = false,defaultValue = "10") String pageSize){
        System.out.println("页面长度"+pageSize);
        System.out.println("当前页"+currentPage);
        PageInfo<Courser> courserPageInfo = courseService.QueryCourseByName(Integer.parseInt(currentPage),Integer.parseInt(pageSize),name);
        return R.ok().message("分页查询").data("page",courserPageInfo);
    }


    @RequestMapping("getAll")
    public R GetAll(){
        List<Courser> coursers = courseService.searchAll();
        return R.ok().data("list",coursers);
    }


    @RequestMapping("{id}")
    public R GetCourseById(@PathVariable("id") int id){
        Courser courser = courseService.selectByPrimaryKey((long) id);
        return R.ok().data("item",courser);
    }


    @RequestMapping("add")
    public R add(@RequestBody Courser courser){
        System.out.println("添加的课程:"+courser);
        boolean b= courseService.insertSelective(courser);
        return R.ok().data("id",courser.getId());
    }


    @RequestMapping("update")
    public R update(@RequestBody Courser courser){
        System.out.println("更新的课程:"+courser);
        boolean b= courseService.updateByPrimaryKeySelective(courser);
        return R.ok();
    }


    @DeleteMapping("del/{id}")
    public R delCourse(@PathVariable("id") int id){
        courseService.deleteCourse(id);
        return R.ok();
    }
}
