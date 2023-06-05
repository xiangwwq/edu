package com.edu.service;

import com.edu.dao.Chapter;
import com.edu.dao.Video;
import com.edu.mapper.ChapterMapper;
import com.edu.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/12 18:11
 */
@Service
@Transactional
public class ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private VideoServiceTT videoService;
    @Autowired
    private VideoMapper videoMapper;

     public List<Chapter> searchAllByCourserId(int id){
         List<Chapter> chapters = chapterMapper.searchAllByCourserId(id);
         return chapters;
     }


    public boolean insertSelective(Chapter record){
        int i=  chapterMapper.insertSelective(record);
        return i>0;
    }


     public  List<Chapter>  GetChapterByCouresId(int couresID){
         List<Chapter> chapters = chapterMapper.searchAllByCourserId(couresID);
         for (Chapter c:chapters){
             List<Video> videos = videoMapper.searchAllByChapterId(c.getId());
             c.setVideoList(videos);
         }
        return chapters;
     }

   public Chapter selectByPrimaryKey(Long id){
          return chapterMapper.selectByPrimaryKey(id);
    }

    public boolean updateByPrimaryKeySelective(Chapter record){
         int i=chapterMapper.updateByPrimaryKeySelective(record);
         return i>0;
    }

    public void deleteByPrimaryKeyAndVideo(int id){

        List<Video> videos = videoMapper.searchAllByChapterId(id);

        List<String> videoIds=new ArrayList<>();
        for(Video v:videos){
          videoIds.add(v.getVideoSourcePath());
        }
        //批量删除该章节视频
        System.out.println("批量删除该章节视频");
        videoService.removeMoreAlyVideo(videoIds);
        //先删除章节下面的小节
        int i = videoMapper.deleteByChapterId(id);
        // TODO: 2023/5/14
        //还得批量删除小节视频
        //再删除章节
        int i1 = chapterMapper.deleteByPrimaryKey((long) id);
    }

}
