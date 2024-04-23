package com.example.todolist.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateNewUser {
    private static CreateNewUser instance;

    private CreateNewUser() {};

    public static CreateNewUser getInstance(){
        if(instance == null){
            instance = new CreateNewUser();
        }

        return instance;
    }


    public Status createUser(String username, String email, String password){

        try(Connection c = MySQLConnection.getConnection("dbnapinas");
            PreparedStatement statement = c.prepareStatement("INSERT INTO user(username, email, password) VALUES(?, ?, ?)")){

            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);

            int res = statement.executeUpdate();
            if(res == 0){
                return Status.ACCOUNT_CREATION_FAILED;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return Status.ACCOUNT_CREATION_FAILED;
        }

        return Status.ACCOUNT_CREATED_SUCCESSFULLY;
    }
}
