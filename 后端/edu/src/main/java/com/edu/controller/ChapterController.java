package com.edu.controller;

import com.edu.commer.R;
import com.edu.dao.Chapter;
import com.edu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/12 18:07
 */
@RestController
@RequestMapping("chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;


    //根据章节id查询单个章节
    @RequestMapping("getone/{id}")
    public R GetOne(@PathVariable("id") int id){
        Chapter chapter = chapterService.selectByPrimaryKey((long) id);
        return R.ok().data("chapter",chapter);
    }

    @RequestMapping("{id}")
    public R list(@PathVariable("id") int id){
        List<Chapter> chapters = chapterService.searchAllByCourserId(id);

        return R.ok().data("list",chapters);
    }

    //添加章节
    @RequestMapping("add")
     public R add(@RequestBody Chapter chapter){
        boolean b = chapterService.insertSelective(chapter);
        return b?R.ok():R.error();
    }

    //根据课程id查询对应章节及小节
    @RequestMapping("getall/{id}")
    public R GetChapterByCouresId(@PathVariable("id") Integer id){
        List<Chapter> chapters = chapterService.GetChapterByCouresId(id);
        return R.ok().data("allChapterVideo",chapters);
    }

    //更新保存章节
    @RequestMapping("update")
    public R update(@RequestBody Chapter chapter){
        boolean b = chapterService.updateByPrimaryKeySelective(chapter);
        return b?R.ok():R.error();
    }


    //删除章节

    @RequestMapping("del/{id}")
    public  R del(@PathVariable("id") Integer id){
       chapterService.deleteByPrimaryKeyAndVideo(id);
       return R.ok();
    }
}
