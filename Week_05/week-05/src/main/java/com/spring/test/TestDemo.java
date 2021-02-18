package com.spring.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class TestDemo {

    public static void main(String[] args){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        //自动扫描，注解方式实现bean装配
        Hello1Service hello1Service = (Hello1Service)applicationContext.getBean("hello1Service");
        System.out.println(hello1Service.SayHello());

        //xml 装配beanm，根据名称获取bean
        Hello2Service hello2Service = (Hello2Service)applicationContext.getBean("hello2Service");
        System.out.println(hello2Service.SayHello());

        //xml 装配beanm，根据类型获取bean
        Hello3Service hello3Service = applicationContext.getBean(Hello3Service.class);
        System.out.println(hello3Service.SayHello());

    }
}
