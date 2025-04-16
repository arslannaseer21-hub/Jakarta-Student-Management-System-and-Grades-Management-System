package com.student.management.bean;


import com.student.management.dao.StudentDAO;
import com.student.management.model.Student;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
@Getter@Setter
public class StudentBean implements Serializable {
     private Student newStudent=new Student();
    private List<Student> students;
    private Student selectedStudent;
    private String searchQuery;
   

    private final StudentDAO studentService = new StudentDAO(); // Service for database operations

    @PostConstruct
    public void init() {
        selectedStudent=new Student();
        
        students = studentService.getAllStudents(); // Load all students when page loads
    }

    // Search students by name or ID
    public void searchStudents() {
        if (searchQuery == null || searchQuery.isEmpty()) {
            students = studentService.getAllStudents(); // Reset if search is empty
        } else {
            students = studentService.searchStudents(searchQuery);
        }
    }

    // Edit Student
    public String editStudent(Student student) {
        this.selectedStudent = student;
        return "edit_student.xhtml?faces-redirect=true"; // Navigate to edit page
    }

    // Delete Student
    public void deleteStudent(int id) {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            students = studentService.getAllStudents(); // Refresh list
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Student deleted successfully"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error deleting student", ""));
        }
    }
    // Prepare Edit (Fills selectedStudent with the student's data)
    public void prepareEdit(Student student) {
        
        
        
        this.selectedStudent = new Student();
        
        this.selectedStudent.setId(student.getId());
        this.selectedStudent.setFirstName(student.getFirstName());
        this.selectedStudent.setLastName(student.getLastName());
        this.selectedStudent.setEmail(student.getEmail());
        this.selectedStudent.setEnrollments(student.getEnrollments());
        
    }
    
    // Save the edited student (update in DB)
    public void saveStudent() {
        studentService.updateStudent(selectedStudent);  // Update student in DB
        students = studentService.getAllStudents();  // Refresh student list
    }



    // Getters and Setters
    public List<Student> getStudents() {
        return students;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
    
    
    public void addStudent()
    {
        studentService.addStudent(newStudent);
        newStudent=new Student();//reset
         students = studentService.getAllStudents(); 
    }
}
