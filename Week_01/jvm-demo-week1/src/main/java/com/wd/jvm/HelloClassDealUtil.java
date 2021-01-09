package com.wd.jvm;

import java.io.*;

/**
 * @author wangdan
 * @date 2021/1/9
 * 自己编译HelloTest.xclass
 */
public class HelloClassDealUtil {

    public static void main(String[] args){
        try{
            FileInputStream fileInputStream= new FileInputStream("D:\\学习\\java进阶\\code\\Week_01\\jvm-demo-week1\\target\\classes\\com\\wd\\jvm\\Hello.class");
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            for (byte b : bytes) {
                b = (byte) (255-b);
            }
            FileOutputStream fos =new FileOutputStream(new File("D:\\学习\\java进阶\\code\\Week_01\\jvm-demo-week1\\target\\classes\\com\\wd\\jvm\\HelloTest.xlass"));
            fos.write(bytes);
            fos.flush();
        }catch (Exception ex){

        }

    }
}
