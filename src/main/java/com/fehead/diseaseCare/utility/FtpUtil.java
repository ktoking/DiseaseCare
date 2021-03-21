//package com.fehead.diseaseCare.utility;
//
//import com.fehead.community.error.BusinessException;
//import com.fehead.community.error.EmBusinessError;
//import com.jcraft.jsch.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.io.InputStream;
//import java.util.Properties;
//
//@Component
//@Slf4j
//public class FtpUtil {
//
//    //ftp服务器ip地址
//    @Value("${ftp.host}")
//    private String host;
//    //端口
//    @Value("${ftp.port}")
//    private Integer port;
//    //用户名
//    @Value("${ftp.userName}")
//    private String userName;
//    //密码
//    @Value("${ftp.password}")
//    private String password;
//    //存放图片的根目录
//    @Value("${ftp.rootPath}")
//    private String rootPath;
//    //存放图片的路径
//    @Value("${ftp.img.url}")
//    private String imgUrl;
//    //获取链接
//    private ChannelSftp getChannel() throws JSchException {
//        JSch jSch=new JSch();
//        //->ssh root@host:port
//        Session sshSession=jSch.getSession(userName,host,port);
//        //密码
//        sshSession.setPassword(password);
//
//        Properties sshConfig=new Properties();
//        sshConfig.put("StrictHostKeyChecking", "no");
//        sshSession.setConfig(sshConfig);
//        sshSession.connect();
//
//        Channel channel=sshSession.openChannel("sftp");
//        channel.connect();
//
//        return (ChannelSftp) channel;
//    }
//
//    //ftp上传图片
//    public String putImages(InputStream inputStream,String imagePath,String imagesName) throws JSchException, SftpException, BusinessException {
//        try {
//            ChannelSftp sftp = getChannel();
//            String path = rootPath + imagePath + "/";
//            //创建目录
//            createDir(path, sftp);
//
//            //上传文件
//            sftp.put(inputStream, path + imagesName);
//            log.info("上传成功！");
//            sftp.quit();
//            sftp.exit();
//
//            //处理返回的路径
//            String resultFile;
//            resultFile = imgUrl + imagePath + imagesName;
//            return resultFile;
//        }catch (Exception e){
//            log.info("上传失败");
//            throw new BusinessException(EmBusinessError.IMAGE_INSERT_ERROR);
//        }
//    }
//
//    //创建目录
//    private void createDir(String path,ChannelSftp sftp) throws SftpException {
//        String[] folders=path.split("/");
//        sftp.cd("/");
//        for(String folder:folders){
//            if(folder.length()>0){
//                try{
//                    sftp.cd(folder);
//                }catch (SftpException e){
//                    sftp.mkdir(folder);
//                    sftp.cd(folder);
//                }
//            }
//        }
//
//    }
//}
