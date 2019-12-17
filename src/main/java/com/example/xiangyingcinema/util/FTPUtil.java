//package com.example.xiangyingcinema.util;
//
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.net.ftp.FTPClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import java.io.*;
//
//@Slf4j
//@Data
//@Configuration
////@ConfigurationProperties(prefix = "ftp")
////@Component
//public class FTPUtil {
//
//    // 地址 端口 用户名 密码
//    private String hostName = "192.168.43.14";
//    private Integer port=21;
//    private String userName = "yangchengsen";
//    private String password = "yangchengsen";
//    private String uploadPath = "/zfbqrcode";
//
//    private FTPClient ftpClient = null;
//
//    private void initFTPClient(){
//        try{
//            ftpClient = new FTPClient();
//            ftpClient.setControlEncoding("utf-8");
//            ftpClient.connect(hostName,port);
//            ftpClient.login(userName,password);
//        }catch (Exception e){
//            log.error("初始化FTP失败",e);
//        }
//    }
//
//    // 输入一个路径，然后将路径里的文件转换成字符串返回给我
//    public String getFileStrByAddress(String fileAddress){
//        BufferedReader bufferedReader = null;
//        try{
//            initFTPClient();
//            bufferedReader = new BufferedReader(
//                    new InputStreamReader(
//                            ftpClient.retrieveFileStream(fileAddress))
//            );
//
//            StringBuffer stringBuffer = new StringBuffer();
//            while(true){
//                String lineStr = bufferedReader.readLine();
//                if(lineStr == null){
//                    break;
//                }
//                stringBuffer.append(lineStr);
//            }
//
//            ftpClient.logout();
//            return stringBuffer.toString();
//        }catch (Exception e){
//            log.error("获取文件信息失败",e);
//        }finally {
//            try {
//                bufferedReader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    // 上传FTP文件
//    public boolean uploadFile(String fileName,File file) {
//        FileInputStream fileInputStream = null;
//        try{
//            fileInputStream = new FileInputStream(file);
//
//            // FTP相关内容
//            initFTPClient();
//            // 设置FTP的关键参数
//            ftpClient.setControlEncoding("utf-8");
//            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//            ftpClient.enterLocalPassiveMode();
//
//            // 将ftpClient的工作空间修改
//            ftpClient.changeWorkingDirectory(this.getUploadPath());
//
//            // 上传文件
//            ftpClient.storeFile(fileName,fileInputStream);
//
//            return true;
//        }catch (Exception e){
//            log.error("上传失败",e);
//            return false;
//        }finally {
//            try {
//                fileInputStream.close();
//                ftpClient.logout();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//
//        FTPUtil ftpUtil = new FTPUtil();
//        String fileStrByAddress = ftpUtil.getFileStrByAddress("seats/cgs.json");
//
//        System.out.println(fileStrByAddress);
//    }
//
//}















package com.example.xiangyingcinema.util;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.*;

@Slf4j
@Data
@Configuration
//@ConfigurationProperties(prefix = "ftp")
//@Component
public class FTPUtil {
    // 输入一个路径，然后将路径里的文件转换成字符串返回给我
    public String getFileStrByAddress(String fileAddress){
//        BufferedReader bufferedReader = null;
//        try{
////            initFTPClient();
//            bufferedReader = new BufferedReader(
//                    new InputStreamReader(
//                            ftpClient.retrieveFileStream(fileAddress))
//            );
//
//            StringBuffer stringBuffer = new StringBuffer();
//            while(true){
//                String lineStr = bufferedReader.readLine();
//                if(lineStr == null){
//                    break;
//                }
//                stringBuffer.append(lineStr);
//            }
//
//            ftpClient.logout();
//            return stringBuffer.toString();




//            Properties pps = new Properties();
        BufferedReader br = null;
        try{
            InputStream stream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("4dx.properties");
               br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

        StringBuffer stringBuffer = new StringBuffer();
            while(true){
                String lineStr = br.readLine();

                if(lineStr == null){
                    break;
                }
                stringBuffer.append(lineStr);
            }

            return stringBuffer.toString();



        }catch (Exception e){
            log.error("获取文件信息失败",e);
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 上传FTP文件
    public boolean uploadFile(String fileName,File file) {
//        FileInputStream fileInputStream = null;
//        try{
//            fileInputStream = new FileInputStream(file);
//
//            // FTP相关内容
//            initFTPClient();
//            // 设置FTP的关键参数
//            ftpClient.setControlEncoding("utf-8");
//            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//            ftpClient.enterLocalPassiveMode();
//
//            // 将ftpClient的工作空间修改
//            ftpClient.changeWorkingDirectory(this.getUploadPath());
//
//            // 上传文件
//            ftpClient.storeFile(fileName,fileInputStream);
//
//            return true;
//        }catch (Exception e){
//            log.error("上传失败",e);
//            return false;
//        }finally {
//            try {
//                fileInputStream.close();
//                ftpClient.logout();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return true;
    }

    public static void main(String[] args) {

        FTPUtil ftpUtil = new FTPUtil();
        String fileStrByAddress = ftpUtil.getFileStrByAddress("seats/cgs.json");
        JSONObject jsonObject = JSONObject.parseObject(fileStrByAddress);
        // seats=1,2,3   ids="1,3,4,5,6,7,88"
        String ids = jsonObject.get("ids").toString();

        System.out.println(fileStrByAddress);
        System.out.println("----------------------------------------------");
        System.out.println(jsonObject);
        System.out.println("----------------------------------------");
        System.out.println(ids);
    }

}