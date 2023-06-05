package com.edu.dao;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName courser
 */
@Data
public class Courser implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private Integer price;

    /**
     * 课时数
     */
    private Integer lessonNum;

    /**
     * 课程封面
     */
    private String cover;

    /**
     * 更新时间
     */
    private Date updateTime;


     private String msg;

    private static final long serialVersionUID = 1L;
}