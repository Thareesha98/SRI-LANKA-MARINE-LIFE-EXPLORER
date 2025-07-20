/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.students.sri_lanka_marine_life_explorer;

/**
 *
 * @author thareesha
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/marine";
    private static final String USER = "appuser";
    private static final String PASSWORD = "app_pass123";
    
    public static Connection getConnection() throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); 
   
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        
        return DriverManager.getConnection(URL,USER,PASSWORD);
        
    }
}
