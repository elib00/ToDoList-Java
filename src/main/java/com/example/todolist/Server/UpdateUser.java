package com.example.todolist.Server;

import com.example.todolist.CurrentUser;

import java.sql.*;

public class UpdateUser {
    private static UpdateUser instance;

    private UpdateUser() {};

    public static UpdateUser getInstance(){
        if(instance == null){
            instance = new UpdateUser();
        }

        return instance;
    }

    public Status updateUser(String field, String value, int userID){

        try(Connection c = MySQLConnection.getConnection("dbnapinas");
            PreparedStatement statement = c.prepareStatement("UPDATE user SET " + field + " = ? WHERE user_id = ?")){

            statement.setString(1, value);
            statement.setInt(2, userID);

            int res = statement.executeUpdate();

            if(res == 0){
                return Status.ACCOUNT_UPDATE_FAILED;
            }

            PreparedStatement getUserStatement = c.prepareStatement("SELECT * FROM user WHERE user_id = ?");
            getUserStatement.setInt(1, userID);

            ResultSet userData = getUserStatement.executeQuery();

            if(userData.next()){
                CurrentUser.username = userData.getString("username");
                CurrentUser.email = userData.getString("email");
                CurrentUser.userID = userData.getInt("user_id");
            }

        }catch (SQLException e){
            e.printStackTrace();
            return Status.ACCOUNT_UPDATE_FAILED;
        }

        return Status.ACCOUNT_UPDATED_SUCCESSFULLY;
    }
}
