package com.edu.service;

import com.edu.dao.Video;
import com.edu.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/12 18:11
 */
@Service
public class VideoService {

    @Autowired
    private VideoServiceTT videoService;
    @Autowired
    private VideoMapper videoMapper;


    public boolean insertSelective(Video record){
        int i = videoMapper.insertSelective(record);
        return i>0;
    }


    public boolean deleteVideo(int id){
        Video video = videoMapper.selectByPrimaryKey((long) id);
        //根据小节视频的id删除阿里云存储视频
        String videoSourcePath = video.getVideoSourcePath();
        videoService.removeVideo(videoSourcePath);

        int i = videoMapper.deleteByPrimaryKey((long) id);
       return i>0;
    }


    public   void   delVideo(String fileSavePath){
        String substring = fileSavePath.substring(21);
        String filePath= "D:"+substring;
//        System.out.println(filePath);
        File file=new File(filePath);
        if(file.exists()){
            System.out.println("已删除视频");
            file.delete();
        }else {
            System.out.println("不存在视频");
        }
    }
}
