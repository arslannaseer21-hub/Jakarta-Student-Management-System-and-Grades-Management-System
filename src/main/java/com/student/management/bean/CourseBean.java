/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.management.bean;

import com.student.management.dao.CourseDAO;
import com.student.management.model.Course;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Arslan
 */

@Named
@ViewScoped
@Getter@Setter
public class CourseBean implements Serializable{
    private Course newCourse=new Course();
    private List<Course> courses=new ArrayList<>();
    private final CourseDAO courseService=new CourseDAO();;
    
    @PostConstruct
    public void init()
    {
        
        newCourse=new Course();
        courses=courseService.getAllCourses();
    }
    
    
    public void addCourse()
    {
        courseService.addCourse(newCourse);
        newCourse=new Course();
        courses=courseService.getAllCourses();
        
    }
    
    
    
    
}
