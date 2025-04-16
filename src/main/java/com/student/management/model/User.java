
package com.student.management.model;
import jakarta.persistence.*;
import lombok.*;



/**
 *
 * @author Arslan
 */


@Entity
@Table (name = "users")
@NoArgsConstructor@AllArgsConstructor@Getter @Setter
public class User {
    
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)@Column(name = "user_id")
    private int id;
    
    private String userName;
    private String password;
    private String role; // admin / teacher
    
}
