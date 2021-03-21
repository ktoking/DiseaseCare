package com.fehead.diseaseCare.utility;


import java.util.Random;

/**
 * 工具类，修改上传图片名
 */
public class IDUtils {
    /**
     * 生成随机id
     */
    public static String getImageName(){
        //获取当前时间的长整型值包含毫秒
        long millis= System.currentTimeMillis();
        //加上三位随机数
        Random random=new Random();
        int end3=random.nextInt(999);
        //如果不足三位数千亩补0
        String str=millis+String.format("%03d",end3);

        return str;
    }
}
