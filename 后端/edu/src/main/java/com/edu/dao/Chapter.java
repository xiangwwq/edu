package com.edu.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName chapter
 */
@Data
public class Chapter implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer courserId;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private Integer sort;

    /**
     * 
     */
    private Date updateTime;


    private List<Video> videoList;


    private static final long serialVersionUID = 1L;
}