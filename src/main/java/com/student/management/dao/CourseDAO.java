/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.management.dao;

import com.student.management.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author 120123
 */
public class CourseDAO {
    EntityManager em;

    public CourseDAO() {
        em=Persistence.createEntityManagerFactory("StudentManagementPU").createEntityManager();
    }
    
    
    public List<Course> getAllCourses()
    {
        return em.createQuery("Select c from Course c",Course.class).getResultList();
    }
    
    
    public boolean addCourse(Course course)
    {
        try
        {
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
            return true;
                
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
}
