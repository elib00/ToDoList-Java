package com.example.todolist.Server;

import com.example.todolist.CurrentUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;

public class DeleteUser {
    private static DeleteUser instance;

    private DeleteUser() {};

    public static DeleteUser getInstance(){
        if(instance == null){
            instance = new DeleteUser();
        }

        return instance;
    }

    public Status deleteUser(int userID){
        try(Connection c = MySQLConnection.getConnection("dbnapinas");
            PreparedStatement statement = c.prepareStatement("DELETE FROM user WHERE user_id = ?")){

            statement.setInt(1, userID);
            int res = statement.executeUpdate();

            if(res == 0){
                return Status.ACCOUNT_DELETION_FAILED;
            }

            //set it to default
            CurrentUser.userID = -1;
            CurrentUser.username = "";
            CurrentUser.email = "";

        }catch(SQLException e){
            e.printStackTrace();
            return Status.ACCOUNT_DELETION_FAILED;
        }

        return Status.ACCOUNT_DELETED_SUCCESSFULLY;
    }
}
