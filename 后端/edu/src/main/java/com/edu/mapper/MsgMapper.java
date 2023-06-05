package com.edu.mapper;

import com.edu.dao.Msg;
import org.apache.ibatis.annotations.Mapper;

/**
* @author aaa
* @description 针对表【msg】的数据库操作Mapper
* @createDate 2023-05-14 10:18:51
* @Entity com.edu.dao.Msg
*/
@Mapper
public interface MsgMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Msg record);

    int insertSelective(Msg record);

    Msg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Msg record);

    int updateByPrimaryKey(Msg record);

    Msg selectByCourseId(int id);

    int updateById(Msg record);

    int delById(int id);


}
