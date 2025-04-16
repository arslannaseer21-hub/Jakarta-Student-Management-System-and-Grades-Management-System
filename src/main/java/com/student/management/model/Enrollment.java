
package com.student.management.model;
import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author Arslan
 * 
 */



@Entity
@Table(name = "enrollments")
@NoArgsConstructor@AllArgsConstructor@Getter@Setter
public class Enrollment {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    
    private String grade;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn (name ="course_id")
    private Course course;
    
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn (name ="student_id")
    private Student student;
    
    
}
