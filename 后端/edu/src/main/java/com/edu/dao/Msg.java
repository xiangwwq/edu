package com.edu.dao;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName msg
 */
@Data
public class Msg implements Serializable {
    /**
     * 课程id
     */
    private Integer id;

    /**
     * 课程描述
     */
    private String message;

    private static final long serialVersionUID = 1L;
}