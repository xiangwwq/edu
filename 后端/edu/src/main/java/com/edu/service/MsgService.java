package com.edu.service;

import com.edu.dao.Msg;
import com.edu.mapper.MsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/14 10:20
 */
@Service
public class MsgService {
    @Autowired
    private MsgMapper msgMapper;

   public Msg selectByCourseId(int id){
       Msg msg = msgMapper.selectByCourseId(id);
       return msg;
   }

}
