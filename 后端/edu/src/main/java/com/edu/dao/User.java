package com.edu.dao;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@Data
public class User implements Serializable {
    /**
     * 
     */
    private Integer uid;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Integer auth;

    private static final long serialVersionUID = 1L;
}