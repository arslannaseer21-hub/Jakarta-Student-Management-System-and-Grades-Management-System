/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.management.bean;

import com.student.management.dao.EnrollmentDAO;
import com.student.management.dao.StudentDAO;
import com.student.management.model.Course;
import com.student.management.model.Enrollment;
import com.student.management.model.Student;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

/**
 *
 * @author 120123
 */

@Named
@ViewScoped
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class EnrollmentBean implements Serializable{
   
    private List<Student> students=new ArrayList();
    private Student selectedStudent=new Student();
    private Course  selectedCourse=new Course();
    private List<Enrollment> enrollments=new ArrayList<>();
    private final StudentDAO studentService=new StudentDAO();
    private final EnrollmentDAO enrollmentService=new EnrollmentDAO();
    private String searchQuery;
    private String selectButtonText="Select";
    private boolean disable=false;
    private Integer selectedEnrollmentId;
    private String grade;
    
   
    
    @PostConstruct
    public void init()
    {
     
   
       enrollments=enrollmentService.getAllEnrollments();
    }
    
    public void selectStudent(Student student)
    {
        this.selectedStudent=student;
        this.disable=true;
        selectButtonText="Selected";
    }
    
     // Search students by name or ID
    public void searchStudents() {
        this.disable=false;
        selectButtonText="Select";
        if (searchQuery == null || searchQuery.isEmpty()) {
            students = new ArrayList<>(); // Reset if search is empty
        } else {
            students = studentService.searchStudents(searchQuery);
            
        }
    }
    
    public void enrollStudent()
    {
         try
         {
             if(selectedStudent==null||selectedStudent.getFirstName()==null || selectedStudent.getFirstName().length()==0)
             {
                      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please Search and select a Student", ""));
                      return;
            
             }
             
        
     
         enrollmentService.enrollStudent(selectedStudent,selectedCourse);
         enrollments=enrollmentService.getAllEnrollments();
         
          FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Enrollment successful!", null));
         }
         catch(Exception e)
         {
              // Error message when student is already enrolled
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
    }
         }
    
    
      public void assignGrade() {
        Enrollment enrollment = enrollmentService.getEnrollmentById(selectedEnrollmentId);
        if (enrollment != null) {
            enrollment.setGrade(grade);
            enrollmentService.updateEnrollment(enrollment);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Grade assigned successfully."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enrollment not found.", null));
        }
    }
    
    
    
    }
    
   
    
    
    

