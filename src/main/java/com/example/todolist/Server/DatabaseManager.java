package com.example.todolist.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager instance;
    private DatabaseManager() {};

    public static DatabaseManager getInstance(){
        if(instance == null){
            instance = new DatabaseManager();
        }

        return instance;
    }

    public Status createTask(String title, String content, int userID){
        try(Connection c = MySQLConnection.getConnection("dbnapinas");
            PreparedStatement statement = c.prepareStatement("INSERT INTO task(user_id, task_title, task_content) VALUES(?, ?, ?)")){

            statement.setInt(1, userID);
            statement.setString(2, title);
            statement.setString(3, content);

            int res = statement.executeUpdate();

            if(res == 0){
                return Status.TASK_CREATION_FAILED;
            }

        }catch(SQLException e){
            e.printStackTrace();
            return Status.TASK_CREATION_FAILED;
        }

        return Status.TASK_CREATED_SUCCESSFULLY;
    }
}
