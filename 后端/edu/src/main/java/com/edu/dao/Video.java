package com.edu.dao;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName video
 */
@Data
public class Video implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer courseId;

    /**
     * 
     */
    private Integer chapterId;

    /**
     * 
     */
    private String title;

    /**
     * 视频地址
     */
    private String videoSourcePath;

    private static final long serialVersionUID = 1L;
}