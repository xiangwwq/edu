package com.edu.mapper;

import com.edu.dao.Video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author aaa
* @description 针对表【video】的数据库操作Mapper
* @createDate 2023-05-12 18:16:07
* @Entity com.edu.dao.Video
*/
@Mapper
public interface VideoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

     int  deleteByChapterId(int id);

      int  deleteByCourseId(int id);
      List<Video> searchAllByChapterId(int id);


      List<Video> searchAllByCourseId(int id);





}
