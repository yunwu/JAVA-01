package com.spring.test;

import org.springframework.stereotype.Service;

@Service
public class Hello1Service {

    public String SayHello(){
        return "hello1, world!";
    }
}
