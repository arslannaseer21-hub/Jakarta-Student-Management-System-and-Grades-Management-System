/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.management.dao;

import com.student.management.model.Course;
import com.student.management.model.Enrollment;
import com.student.management.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author 120123
 */
public class EnrollmentDAO {
    
    EntityManager em;

    public EnrollmentDAO() {
        em=Persistence.createEntityManagerFactory("StudentManagementPU").createEntityManager();
    }
    
    public List<Enrollment> getAllEnrollments()
    {
       return em.createQuery("SELECT e FROM Enrollment e",Enrollment.class).getResultList();
    }

    public void enrollStudent(Student selectedStudent, Course selectedCourse) throws Exception {
        Enrollment enrollment=new Enrollment();
        
        
        selectedStudent= em.find(Student.class, selectedStudent.getId());
        selectedCourse= em.find(Course.class, selectedCourse.getId());
        enrollment.setStudent(selectedStudent);
        enrollment.setCourse(selectedCourse);
        
        if(isStudentAlreadyEnrolled(selectedStudent,selectedCourse))
        {
               throw new IllegalArgumentException("Student is already enrolled in this course.");
        }
        
        em.getTransaction().begin();
        em.persist(enrollment);
        em.getTransaction().commit();
    }
 
    
    public boolean isStudentAlreadyEnrolled(Student student, Course course) {
    Long count = em.createQuery(
        "SELECT COUNT(e) FROM Enrollment e WHERE e.student = :student AND e.course = :course", Long.class)
        .setParameter("student", student)
        .setParameter("course", course)
        .getSingleResult();

    return count > 0;
}
    
     // Get Enrollment by ID
    public Enrollment getEnrollmentById(int id) {
        return em.find(Enrollment.class, id);
    }

    // Update Enrollment (used for assigning/updating grade)
    public void updateEnrollment(Enrollment enrollment) {
        em.getTransaction().begin(); // start transaction
        em.merge(enrollment); 
        em.getTransaction().commit();
    }
    
    
    
    }


    

