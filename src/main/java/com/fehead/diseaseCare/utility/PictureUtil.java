package com.fehead.diseaseCare.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fehead.diseaseCare.error.BusinessException;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PictureUtil {

    @Resource
    private FtpUtil ftpUtil;

    // 上传图片拿到地址
    public  Object uploadPicture(MultipartFile uploadFile) throws BusinessException, JSchException, SftpException {
        //1.给上传的图片生成新的文件名
        //1.1获取原始文件名getAcitivity
        String oldName=uploadFile.getOriginalFilename();
        //1.2使用IDUtils工具类生成新的文件名，新的文件名=newName+文件后缀
        String newName= IDUtils.getImageName();
        assert oldName!=null;
        String substring = StringUtils.substring(oldName,oldName.lastIndexOf("."));
        newName=newName+substring;
        //1.3生成文件在服务器端存储的子目录
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime localDateTime=LocalDateTime.now();
        String filePath = "/"+localDateTime.format(formatter)+"/";

        //2.把图片上传到图片服务器
        //2.1获取上传的io流
        InputStream inputStream=null;
        try {
            inputStream=uploadFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.2调用FtpUtil工具进行上传
        return ftpUtil.putImages(inputStream,filePath,newName);
    }

    public String getPicUrl(MultipartFile uploadFile) throws SftpException, JSchException, JsonProcessingException {
        Object result = uploadPicture(uploadFile);
        String cover = new ObjectMapper().writeValueAsString(result);
        String avatarUrl= cover.replace("\"", "");
        return avatarUrl;
    }
}
