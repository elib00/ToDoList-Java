package com.example.todolist.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
     public boolean initializeDB() throws SQLException {
         Statement statement = null;
         try(Connection connection = MySQLConnection.getConnection("")){
             statement = connection.createStatement();
             String createDBQuery  = "CREATE DATABASE IF NOT EXISTS dbnapinas;";
             statement.executeUpdate(createDBQuery);

             connection.setCatalog("dbnapinas");
             connection.setAutoCommit(false);
             statement = connection.createStatement();

             String createUserTableQuery = "CREATE TABLE IF NOT EXISTS user (" +
                    "user_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "username VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100) NOT NULL, " +
                    "password VARCHAR(100) NOT NULL);";

             statement.executeUpdate(createUserTableQuery);

             String createTaskTableQuery = "CREATE TABLE IF NOT EXISTS task (" +
                     "task_id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "user_id INT NOT NULL, " +
                     "task_title VARCHAR(100), " +
                     "task_content VARCHAR(100), " +
                     "FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE)";

             statement.executeUpdate(createTaskTableQuery);
             connection.commit();
         }catch (SQLException e){
             e.printStackTrace();
         }finally {
             assert statement != null;
             statement.close();
         }

         return true;
     }
}
