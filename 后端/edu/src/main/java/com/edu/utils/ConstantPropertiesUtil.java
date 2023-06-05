package com.edu.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author aaa
 * @description: TODO
 * @date: 2022/11/1 11:26
 */


@Data
@Component
@ConfigurationProperties(prefix = "aliyun.vod.file")
public class ConstantPropertiesUtil {
    String keyid="";
    String keysecret="";
}
