package com.example.todolist.Server;

import com.example.todolist.CurrentUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidateLogin {
    private static ValidateLogin instance;
    private String username;
    private String password;

    private ValidateLogin() {}; // private constructor

    public static ValidateLogin getInstance(){
        if(instance == null) {
            instance = new ValidateLogin();
        }

        return instance;
    }

    public Status validate(String username, String password){
        this.username = username;
        this.password = password;

        try(Connection c = MySQLConnection.getConnection("dbnapinas");
            PreparedStatement statement = c.prepareStatement("SELECT * FROM user where username = ?")){

            statement.setString(1, username);
            ResultSet res = statement.executeQuery();

            if(!res.next()) return Status.USERNAME_NOT_FOUND;

            String passwordFromDB = res.getString("password");
            if(!passwordFromDB.equals(password)) return Status.INCORRECT_PASSWORD;

            //setting the current user
            CurrentUser.userID = res.getInt("user_id");
            CurrentUser.username = res.getString("username");
            CurrentUser.email = res.getString("email");
        }catch(SQLException e){
            e.printStackTrace();
        }

        return Status.LOGIN_SUCCESS;
    }
}
