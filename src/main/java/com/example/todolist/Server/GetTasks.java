package com.example.todolist.Server;

import com.example.todolist.CurrentUser;

import java.lang.reflect.AnnotatedArrayType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetTasks {
    public List<Map<String, String>> getTasks(){
        try(Connection c = MySQLConnection.getConnection("dbnapinas");
            PreparedStatement statement = c.prepareStatement("SELECT * FROM task WHERE user_id = ?")){
            statement.setInt(1, CurrentUser.userID);

            ResultSet res = statement.executeQuery();

            List<Map<String, String>> tasks = new ArrayList<>();

            while(res.next()){
                Map<String, String> temp = new HashMap<>();
                temp.put("task_id", res.getString("task_id"));
                temp.put("user_id", res.getString("user_id"));
                temp.put("task_title", res.getString("task_title"));
                temp.put("task_content", res.getString("task_content"));

                tasks.add(temp);
            }

            return tasks;


        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
