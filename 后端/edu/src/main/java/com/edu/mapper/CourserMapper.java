package com.edu.mapper;

import com.edu.dao.Courser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author aaa
* @description 针对表【courser】的数据库操作Mapper
* @createDate 2023-05-12 18:16:17
* @Entity com.edu.dao.Courser
*/
@Mapper
public interface CourserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Courser record);

    int insertSelective(Courser record);

    Courser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Courser record);

    int updateByPrimaryKey(Courser record);

     List<Courser> QueryCourseByName(@Param("name") String name);

     List<Courser> searchAll();

}
