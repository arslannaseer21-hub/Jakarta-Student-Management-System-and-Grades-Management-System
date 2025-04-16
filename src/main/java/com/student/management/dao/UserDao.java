/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.management.dao;

import com.student.management.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author 120123
 */
public class UserDao {
    EntityManager manager;

    public UserDao() {
        manager=Persistence.createEntityManagerFactory("StudentManagementPU").createEntityManager();
    }
    
    public User authenticateUser(String userName, String password)
    {
        try
        {
      List<User> users = manager.createQuery(
        "SELECT u FROM User u WHERE u.userName = :userName AND u.password = :password", User.class)
    .setParameter("userName", userName)
    .setParameter("password", password)
    .getResultList();

       
       if(users!=null && users.size()>0)
       {
           return users.get(0);
       }
        else
        
       return null;
    }
    finally
    {
     manager.close();   
    }
        
    }
    
    
}
