package com.edu.controller;

import com.edu.commer.R;
import com.edu.dao.Msg;
import com.edu.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/14 10:19
 */
@RestController
@RequestMapping("msg")
public class MsgController {

    @Autowired
    private MsgService msgService;


    @RequestMapping("{id}")
    public R getMsgByCourseID(@PathVariable("id") Integer id){
        Msg msg = msgService.selectByCourseId(id);
        return R.ok().data("item",msg);
    }

}
