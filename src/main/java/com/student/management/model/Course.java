
package com.student.management.model;
import lombok.*;
import jakarta.persistence.*;
import java.util.List;
/**
 *
 * @author Arslan
 */



@Entity
@Table(name = "courses")
@NoArgsConstructor@AllArgsConstructor@Getter@Setter// use lombok to auto generate getters setters and constructor
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name ="course_id")
    private int id;
    
    
    private String courseName;
    private String courseCode;
    
    
    @OneToMany (mappedBy ="course", cascade = CascadeType.PERSIST)
    private List<Enrollment> enrollments;
    
    
 
    
    
    
}
