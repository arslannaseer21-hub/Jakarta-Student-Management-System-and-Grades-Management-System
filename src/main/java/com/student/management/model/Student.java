
package com.student.management.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.*;

/**
 *
 * @author Arslan
 */


@Entity
@Table(name="students")
@Getter@Setter// Lombok generates getters and setters
@NoArgsConstructor // Lombok generates a no-args constructor
@AllArgsConstructor // Lombok generates an all-args constructor


public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column (name = "student_id")
    private int id;
    
    //@Column is optional jpa automatical maps fields to column in table but if we need specific things wel8ike unique =true or nullable= false we can specify @Column
    private String firstName;
    private String lastName;
    
    @Column (unique=true, name="email")
    private String email;
    
    @OneToMany (mappedBy = "student", cascade = CascadeType.PERSIST)
    private List<Enrollment> enrollments;
    

    
    
   
}
