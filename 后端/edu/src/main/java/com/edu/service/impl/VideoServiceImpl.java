package com.edu.service.impl;

import com.aliyun.oss.common.utils.StringUtils;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.edu.service.VideoServiceTT;
import com.edu.utils.ConstantPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.edu.utils.Client.initVodClient;


/**
 * @author aaa
 * @description: TODO
 * @date: 2022/11/1 11:12
 */
@Service
public class VideoServiceImpl implements VideoServiceTT {

    @Autowired
    ConstantPropertiesUtil constantPropertiesUtil;
    @Override
    public String uploadVideo(MultipartFile file) {

        try {
            //获得文件流
            InputStream inputStream = file.getInputStream();
            //获得文件原名(有后缀)
            String originalFilename = file.getOriginalFilename();
            //获得文件上传后的名字(没有后缀)
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

            UploadStreamRequest request = new UploadStreamRequest(constantPropertiesUtil.getKeyid(), constantPropertiesUtil.getKeysecret(), title, originalFilename, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();

            UploadStreamResponse response = uploader.uploadStream(request);

            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();
            String message = response.getMessage();

            System.out.println("上传响应信息"+message);
            System.out.println(response);
            System.out.println("获得上传视频的id"+videoId);
            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
//            throw new GuliException(20001, "guli vod 服务上传失败");
            return null;
        }


    }

    @Override
    public void removeVideo(String id) {
        System.out.println("删除单个云端视频:"+id);
        try {
            //1.初始化对象
            DefaultAcsClient client = initVodClient("LTAI5tCpv1BiMfWhYjMigDFT", "JbzTwhPObWZZ6yFlFvP9xTWk2n3Ii0");
            //2.获得请求对象
            DeleteVideoRequest request=new DeleteVideoRequest();

            request.setVideoIds(id);

            client.getAcsResponse(request);
        }catch (Exception e){
            e.printStackTrace();
//            throw new GuliException(20001,"删除视频失败");
        }

    }

    @Override
    public void removeMoreAlyVideo(List videoIds) {
        System.out.println("删除多个云端视频:"+videoIds);
          //把list拼接成1，2，3
        String join = StringUtils.join(String.valueOf(videoIds.toArray()), ",");
        try {
            //1.初始化对象
            DefaultAcsClient client = initVodClient("LTAI5tCpv1BiMfWhYjMigDFT", "JbzTwhPObWZZ6yFlFvP9xTWk2n3Ii0");
            //2.获得请求对象
            DeleteVideoRequest request=new DeleteVideoRequest();

            request.setVideoIds(join);

            client.getAcsResponse(request);
        }catch (Exception e){
            e.printStackTrace();
//            throw new GuliException(20001,"删除视频失败");
        }
    }
}
