package com.edu.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import com.edu.commer.R;
import com.edu.service.VideoServiceTT;
import com.edu.utils.Client;
import com.edu.utils.ConstantPropertiesUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author aaa
 * @description: TODO
 * @date: 2022/11/1 11:11
 */

@RequestMapping("/aliyun")
@CrossOrigin
@RestController
public class VideoAdminController {
    @Autowired
    private VideoServiceTT videoService;

    @Autowired
   private ConstantPropertiesUtil constantPropertiesUtil;


    @PostMapping("upload")
    public R uploadVideo(@RequestParam("file") MultipartFile file) throws Exception {
        System.out.println("开始上传视频");
        //返回上传视频id
        String videoId = videoService.uploadVideo(file);
        return R.ok().message("视频上传成功").data("videoId", videoId);
    }


     //删除单个阿里云id视频，对应一个小节
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){
        videoService.removeVideo(id);
        return R.ok();
    }

     //删除课程所有视频的方法
     @DeleteMapping("deleteBatch")
     public R deleteBatchVideo(@RequestParam("VideoIds") List<String> videoIds){
         videoService.removeMoreAlyVideo(videoIds);
         System.gc();
         return R.ok();
     }


     @GetMapping("getPath/{videoId}")
     public R getVideoPath(@PathVariable("videoId") String videoId){
         try {
         DefaultAcsClient client = Client.initVodClient(constantPropertiesUtil.getKeyid(),constantPropertiesUtil.getKeysecret());

         //请求
         GetPlayInfoRequest request = new GetPlayInfoRequest();

         request.setVideoId(videoId);

         GetPlayInfoResponse response = client.getAcsResponse(request);

         List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();

         String path="";
         //播放地址
         for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
             System.out.print("播放地址 = " + playInfo.getPlayURL() + "\n");
             path=playInfo.getPlayURL();
         }
         //Base信息
         System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
         return R.ok().data("path",path);
         }catch (Exception e){
             e.printStackTrace();
             return R.error();
         }
     }
     //根据id获得视频播放凭证
    @GetMapping("getPlayAuth/{videoId}")
    public R getVideoPlayAuth(@PathVariable("videoId") String videoId)  {
         try {

             DefaultAcsClient client = Client.initVodClient(constantPropertiesUtil.getKeyid(),constantPropertiesUtil.getKeysecret());

             //请求
             GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();

             request.setVideoId(videoId);

             //响应
             GetVideoPlayAuthResponse response = client.getAcsResponse(request);

             //得到播放凭证
             String playAuth = response.getPlayAuth();
             return R.ok().data("playAuth",playAuth);
         }catch (Exception e){
             e.printStackTrace();
             return R.error();
         }
    }
}
