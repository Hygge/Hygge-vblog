package com.hygge.vblog.common.util.file;


import com.hygge.vblog.common.emu.Constants;
import com.hygge.vblog.common.emu.HygType;
import com.hygge.vblog.common.exception.HyggeException;
import com.hygge.vblog.common.util.DateUtils;
import com.hygge.vblog.domain.VFileRecord;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Classname FileUploadUtil
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/4/23 14:07
 * @Author hygge
 */

public class FileUploadUtil {

    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 文件上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @return 返回上传成功的文件名
     * @throws IOException 比如读写文件出错时
     */
    public static Map<String, String> upload(String baseDir, MultipartFile file) throws IOException {

        int fileNameLength = Objects.requireNonNull(file.getOriginalFilename()).length();
        if (fileNameLength > FileUploadUtil.DEFAULT_FILE_NAME_LENGTH)
        {
            throw new HyggeException(424, "文件名过长");
        }

        assertAllowed(file);

        Map<String, String> map = extractFilename(file);
        String fileFullName =  map.get(Constants.NEW_FILE_NAME.getKey());
        //
        String absPath = getAbsoluteFile(baseDir, fileFullName).getAbsolutePath();
        file.transferTo(Paths.get(absPath));
        String pathFileName = getPathFileName(baseDir, fileFullName);
        map.put(Constants.PATH_FILE_NAME.getKey(), pathFileName);
        map.put(Constants.LOCAL_OR_CLOUD.getKey(), String.valueOf(HygType.LOCAL.type()));
        return map;
    }
    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     */
    public static void assertAllowed(MultipartFile file) {
        long size = file.getSize();
        if (size > DEFAULT_MAX_SIZE)
        {
            throw new HyggeException(424, "文件过大");
        }
    }
    /**
     * 返回文件名路径等
     */
    public static Map<String, String> extractFilename(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String name = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        String date = DateUtils.dateTimeNow();
        String newFileName = DateUtils.datePath() + name + date + suffix;
        Integer type = checkFileType(suffix);
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(Constants.SUFFIX.getKey(), suffix);
        hashMap.put(Constants.FILE_NAME.getKey(), name);
        hashMap.put(Constants.NEW_FILE_NAME.getKey(), newFileName);
        hashMap.put(Constants.TYPE.getKey(), String.valueOf(type));
        return hashMap;
    }

    /**
     * 保存文件
     * @param uploadDir
     * @param fileName
     * @return
     * @throws IOException
     */
    public static File getAbsoluteFile(String uploadDir, String fileName){
        File desc = new File(uploadDir + File.separator + fileName);
        if (!desc.exists())
        {
            if (!desc.getParentFile().exists())
            {
                desc.getParentFile().mkdirs();
            }
        }
        return desc;
    }

    public static String getPathFileName(String uploadDir, String fileName) {
        int dirLastIndex = uploadDir.length() + 1;
        String currentDir = StringUtils.substring(uploadDir, dirLastIndex);
        return Constants.RESOURCE_PREFIX.getKey() + currentDir + "/" + fileName;
    }

    /**
     * 根据文件后缀返回文件类型
     * @param suffix
     * @return
     */
    public static Integer checkFileType(String suffix){
        String newSuffix = suffix.substring(1);

        for (String s : MimeTypeUtils.IMG_TYPE) {
            if (newSuffix.equals(s)){
                return HygType.IMG_TYPE.type();
            }
        }
        throw new  HyggeException(424,"未知文件类型，请上传对应的文件类型");
    }

}
