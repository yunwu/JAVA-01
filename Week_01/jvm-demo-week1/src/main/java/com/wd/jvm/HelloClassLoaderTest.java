package com.wd.jvm;

import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * @author wangdan
 * @date 2021/1/9
 *
 * 出现jar包无法加载问题，这是加载自己编译的jar的测试类
 */
public class HelloClassLoaderTest extends ClassLoader {


    public static void main(String[] args){
        InputStream inputStream = HelloClassLoaderTest.class.getResourceAsStream("HelloTest.xlass");
        try{
            byte[] classArray = new byte[inputStream.available()];
            inputStream.read(classArray);
            for (byte b : classArray) {
                b = (byte)(255-b);
                //b = (byte) ~b;
            }
            Class clazz = new HelloClassLoaderTest().findClass(classArray);
            Object obj = clazz.newInstance();
            Method method = clazz.getMethod("hello");
            method.invoke(obj);
        }catch (Exception ex){
        }
    }



    private Class<?> findClass(byte[] bytes){
        return defineClass("com.wd.jvm.Hello", bytes,0, bytes.length );
    }


}
