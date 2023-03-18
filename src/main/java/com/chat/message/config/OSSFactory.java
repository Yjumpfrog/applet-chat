package com.chat.message.config;
/**
 * 文件上传Factory
 *
 */
public final class OSSFactory {


    public static CloudStorageService build(){
        //获取云存储配置信息
        CloudStorageConfig config = SpringContextHolder.getBean(CloudStorageConfig.class);
            return new AliyunCloudStorageService(config);
    }

}
