package com.student.management.dao;

import com.student.management.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author 120123
 */
public class StudentDAO {
    EntityManager em;
   
    public StudentDAO() {
        em= Persistence.createEntityManagerFactory("StudentManagementPU").createEntityManager();

    }
    
    public void addStudent(Student student)
    {
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
    }
    
     public List<Student> getAllStudents() {
        return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    // Search students by name or ID
    public List<Student> searchStudents(String query) {
        return em.createQuery("SELECT s FROM Student s WHERE s.firstName LIKE :query OR s.id LIKE :query", Student.class)
                .setParameter("query", "%" + query + "%")
                .getResultList();
    }

    // Delete a student by ID
    public boolean deleteStudent(int id) {
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.remove(student);
                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            new RuntimeException(e);
        }
        return false;
    }

    public void updateStudent(Student selectedStudent) {
        
        try
        {
        em.getTransaction().begin();
        Student toUpdate=em.find(Student.class, selectedStudent.getId());
        
        if(toUpdate!=null)
        {
            
            toUpdate.setFirstName(selectedStudent.getFirstName());
            toUpdate.setLastName(selectedStudent.getLastName());
            toUpdate.setEmail(selectedStudent.getEmail());
            em.merge(toUpdate);
        }
        
        em.getTransaction().commit();
        }
        catch(Exception e)
        {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        
        
    }
    
    
}
