package com.wd.java;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Data
@Service
public class School implements ISchool {
    
    // Resource 
    @Autowired(required = true) //primary
    Klass class1;
    
    @Resource
    Student student100;
    
    @Override
    public void ding(){
    
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);
        
    }
    
}
