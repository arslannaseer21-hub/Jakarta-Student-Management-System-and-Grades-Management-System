package com.student.management;
import com.student.management.dao.StudentDAO;
import com.student.management.model.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLTest {
    public static void main(String[] args) {
       /* String url = "jdbc:mysql://localhost:3306/student_management?serverTimezone=UTC";
        String user = "root";
        String password = "";

        try {
           /* Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL successfully!");
            conn.close();
           
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
*/
       
       StudentDAO dao=new StudentDAO();
       Student s=new Student();
       s.setFirstName("abc");
       s.setLastName("xyz");
       s.setEmail("lmn");
       dao.addStudent(s);
    }
}
