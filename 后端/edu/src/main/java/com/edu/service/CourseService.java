package com.edu.service;

import com.edu.dao.Courser;
import com.edu.dao.Msg;
import com.edu.dao.User;
import com.edu.dao.Video;
import com.edu.mapper.ChapterMapper;
import com.edu.mapper.CourserMapper;
import com.edu.mapper.MsgMapper;
import com.edu.mapper.VideoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/12 18:10
 */
@Service
public class CourseService {
    @Autowired
     private CourserMapper courserMapper;

    @Autowired
    private MsgMapper msgMapper;


    @Autowired
    private ChapterMapper chapterMapper;


    @Autowired
    private ChapterService chapterService;


    @Autowired
    private VideoServiceTT videoServiceTT;


    @Autowired
    private VideoMapper videoMapper;

           public static void main(String[] args) {
               String path="http://localhost:3000/images//2023-05-133/0dcc9d6b-30bf-46e5-a266-511c99996925.png";
               String filePath= "D:"+path.substring(21);;
//        System.out.println(filePath);
               File file=new File(filePath);
               if(file.exists()){
                   System.out.println("已删除视频");
                   file.delete();
               }else {
                   System.out.println("不存在视频");
               }
          }


        public  void delImg(String path){
            String filePath= "D:"+path.substring(21);;
            File file=new File(filePath);
            if(file.exists()){
                System.out.println("已删除该照片");
                file.delete();
            }else {
                System.out.println("不存在该照片");
            }
        }


       public  void deleteCourse(int id){
           //删除封面地址
           Courser courser = courserMapper.selectByPrimaryKey((long) id);
           String cover = courser.getCover();
           delImg(cover);
           //删除课程表中的课程
             courserMapper.deleteByPrimaryKey((long) id);
           //删除课程详细表
           msgMapper.delById(id);
           //删除对应章节
           chapterMapper.delByCourserId(id);
           //删除云端视频
           List<Video> videos = videoMapper.searchAllByCourseId(id);

           List<String> videoIds=new ArrayList<>();
           for(Video v:videos){
               videoIds.add(v.getVideoSourcePath());
           }
           //批量删除该章节视频
           System.out.println("批量删除该课程视频");
           videoServiceTT.removeMoreAlyVideo(videoIds);
           //删除课程的所有小节
           videoMapper.deleteByCourseId(id);
       }

      public PageInfo<Courser> QueryCourseByName(Integer a, Integer b, String name){
          PageHelper.startPage(a, b);
          List<Courser> coursers = courserMapper.QueryCourseByName(name);
          PageInfo<Courser> pageInfo = new PageInfo<>(coursers);
          return pageInfo;
      }

    public Courser selectByPrimaryKey(Long id){
        Courser courser = courserMapper.selectByPrimaryKey(id);
        Msg msg = msgMapper.selectByCourseId(Math.toIntExact(id));
        courser.setMsg(msg.getMessage());
        return courser;
    }

    public boolean insertSelective(Courser record){
        String msg = record.getMsg();
        int i = courserMapper.insertSelective(record);
        Msg msg1=new Msg();
        msg1.setId(record.getId());
        msg1.setMessage(msg);
         msgMapper.insert(msg1);
        return i > 0;
    }

    public boolean updateByPrimaryKeySelective(Courser record){

             Msg msg1=new Msg();
             msg1.setMessage(record.getMsg());
             msg1.setId(record.getId());

          int i = courserMapper.updateByPrimaryKeySelective(record);
          msgMapper.updateById(msg1);
          return i>0;
    }

     public List<Courser> searchAll(){
        List<Courser> coursers = courserMapper.searchAll();
        return coursers;
    }

}
