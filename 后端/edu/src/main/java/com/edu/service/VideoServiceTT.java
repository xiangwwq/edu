package com.edu.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/13 23:41
 */
public interface VideoServiceTT {
    String uploadVideo(MultipartFile file);

    void removeVideo(String id);

    void removeMoreAlyVideo(List videoIds);
}
