package com.hygge.vblog.common.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/1/30 19:02
 * @description TODO    阿里云oss对象存储工具
 */

@Slf4j
@Component
public class OSSUtil {

    /**
     * 阿里云oss站点
     *
     * @endpoinnt
     */
    @Value("${oss.endpoint}")
    private String endpoint;

    /**
     * 阿里云oss公钥
     *
     * @accessKeyId
     */
    @Value("${oss.id}")
    private String accessKeyId;

    /**
     * 阿里云oss私钥
     *
     * @accessKeySecret
     */
    @Value("${oss.secret}")
    private  String sec;

    /**
     * 阿里云oss文件根目录
     *
     * @bucketName
     */
    @Value("${oss.bucketName}")
    private  String bucketName;
    /**
     * 阿里云oss文件域名
     *
     * @bucketName
     */
    @Value("${oss.bucket}")
    private  String bucket;


    /**
     * oss 工具客户端
     */
    private volatile static OSS ossClient = null;

    /**
     * 上传文件
     * @param file
     * @param fileDir
     * @param name
     * @return
     */
    public String upload(MultipartFile file, String fileDir, String name) {
        initOSS();
        StringBuilder fileUrl = new StringBuilder();
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = name + LocalDateTime.now().toString() + "-" + UUID.randomUUID().toString().substring(0, 18) + suffix;
            if (!fileDir.endsWith("/")) {
                fileDir = fileDir.concat("/");
            }
            fileUrl = fileUrl.append(fileDir + fileName);
            log.info(fileUrl + "----------------");
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());

            ossClient.putObject(bucketName, fileUrl.toString(), file.getInputStream(), objectMetadata);
            //有效时间访问期限 10年过期过期链接
            Date date = new Date(System.currentTimeMillis()+1000*60*60*24*365*10);
            return ossClient.generatePresignedUrl(bucketName, fileUrl.toString(), date).toString();
        } catch (IOException e) {
            log.info(e.getMessage());
            return "";
        }


    }

    /**
     * 初始化客户端
     *
     * @return
     */

    private  OSS initOSS() {
        if (ossClient == null) {
            synchronized (OSSUtil.class) {
                if (ossClient == null) {
                    ossClient = new OSSClientBuilder().build(endpoint,
                            accessKeyId, sec);
                }
            }
        }
        return ossClient;
    }

    /**
     * 根据前台传过来的文件地址 删除文件
     * @param objectName 2021-08-21/1629533077615-2f247719-f20f-48ef.jpg
     * @return
     */
    public  void delete(String objectName) {
        initOSS();
//        根据BucketName，objectName删除文件
        List<Bucket> buckets = ossClient.listBuckets();
        ossClient.deleteObject(buckets.get(0).getName(), objectName);
        ossClient.shutdown();
    }

    /**
     * 下载
     * @param path       保存地址
     * @param objectName 下载文件目录至文件名
     */
    public void download(String path, String objectName) {
        initOSS();
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(path + "\\" + objectName));
        return;
    }


}