package com.edu.mapper;

import com.edu.dao.Chapter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author aaa
* @description 针对表【chapter】的数据库操作Mapper
* @createDate 2023-05-12 18:16:21
* @Entity com.edu.dao.Chapter
*/
@Mapper
public interface ChapterMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Chapter record);

    int insertSelective(Chapter record);

    Chapter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Chapter record);

    int updateByPrimaryKey(Chapter record);


    List<Chapter> searchAllByCourserId(int id);


     int  delByCourserId(int id);
}
