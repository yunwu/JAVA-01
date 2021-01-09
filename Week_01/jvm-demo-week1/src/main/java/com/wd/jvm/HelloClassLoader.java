package com.wd.jvm;

import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * @author wangdan
 * @date 2021/1/9
 */
public class HelloClassLoader extends ClassLoader {


    public static void main(String[] args){
        try{
            Class clazz = new HelloClassLoader().findClass("Hello.xlass");
            if (clazz == null){
                return;
            }
            Object obj = clazz.newInstance();
            Method method = clazz.getMethod("hello");
            method.invoke(obj);
        }catch (Exception ex){

        }


    }



    @Override
    protected Class<?> findClass(String name){
        InputStream inputStream = this.getClass().getResourceAsStream(name);
        try{
            byte[] classArray = new byte[inputStream.available()];
            inputStream.read(classArray);
            for (byte b : classArray) {
                b = (byte)(255-b);
                //b = (byte) ~b;
            }
            return defineClass("com.wd.jvm.Hello", classArray,0, classArray.length );
        }catch (Exception ex){
            return null;
        }
    }


}
