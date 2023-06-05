package com.edu.controller;

import com.edu.commer.R;
import com.edu.dao.Video;
import com.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/12 18:09
 */
@RestController
@RequestMapping("video")
public class VideoController {



     @Autowired
     private VideoService videoService;
    @Value("${file-save-path}")
    private String fileSavePath;


     //上传图片
    @PostMapping("/updateImg")
    public R UploadImg(MultipartFile file, HttpServletRequest request) throws FileNotFoundException {

        System.out.println("上传图片开始");

        SimpleDateFormat dateFormat = new SimpleDateFormat("/yyyy-MM-DD/");
        String filename = file.getOriginalFilename();//取得文件名称
        if (!filename.endsWith(".png") && !filename.endsWith(".jpg")) {
            return R.error().message("文件类型不对");
        }


        //1.后半段目录：  2020/03/15
        String directory = dateFormat.format(new Date());
        /**
         *  2.文件保存目录  E:/images/2020/03/15/
         *  如果目录不存在，则创建
         */
        File dir = new File(fileSavePath + directory);
        if (!dir.exists()) {
            dir.mkdirs();

        }


        String newName;
        if (filename.endsWith(".png"))
            newName = UUID.randomUUID().toString() + ".png";
        else
            newName = UUID.randomUUID().toString() + ".jpg";
        try {
            //新建文件
            file.transferTo(new File(dir, newName));
            //String url = realPath + newName;
        } catch (IOException e) {
            return R.error().message(e.getMessage());
        }
        //协议 :// ip地址 ：端口号 / 文件目录(/images/2020/03/15/xxx.jpg)
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + directory + newName;
        System.out.println("上传图片完成");
        return R.ok().data("url", url);
    }


     //上传视频
//    @PostMapping("/updateVideo")
//    public R UploadVideo(MultipartFile file, HttpServletRequest request) throws FileNotFoundException {
//
//        System.out.println("上传视频开始");
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("/yyyy-MM-DD/");
//        String filename = file.getOriginalFilename();//取得文件名称
//        if (!filename.endsWith(".mp4")) {
//            return R.error().message("文件类型不对");
//        }
//
//
//        //1.后半段目录：  2020/03/15
//        String directory = dateFormat.format(new Date());
//        /**
//         *  2.文件保存目录  E:/images/2020/03/15/
//         *  如果目录不存在，则创建
//         */
//        File dir = new File(fileSavePath + directory);
//        if (!dir.exists()) {
//            dir.mkdirs();
//
//        }
//
//
//        String newName = UUID.randomUUID().toString() + ".mp4";
//
//
//        try {
//            //新建文件
//            file.transferTo(new File(dir, newName));
//            //String url = realPath + newName;
//        } catch (IOException e) {
//            return R.error().message(e.getMessage());
//        }
//        //协议 :// ip地址 ：端口号 / 文件目录(/images/2020/03/15/xxx.jpg)
//        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + directory + newName;
//        System.out.println("上传视频完成");
//        return R.ok().data("url", url);
//    }



    //添加小节
    @RequestMapping("add")
    public  R add(@RequestBody Video video){
        boolean b = videoService.insertSelective(video);
        return b?R.ok():R.error();
    }

    //删除小节
    @RequestMapping("del/{id}")
    public R del(@PathVariable("id") Integer id){
        boolean b = videoService.deleteVideo(id);
        return b?R.ok():R.error();
    }


    //删除视频根据视频地址
    @RequestMapping("delVideo")
    public  R delVideo(@RequestParam("path") String fileSavePath){
        videoService.delVideo(fileSavePath);
        return R.ok();
    }
}
