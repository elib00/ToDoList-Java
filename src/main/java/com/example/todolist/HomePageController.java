package com.example.todolist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomePageController {
    @FXML
    private Label currentUserUName;
    @FXML
    private Label currentUserEmail;

    public void setPageValues(){
        currentUserUName.setText(CurrentUser.username);
        currentUserEmail.setText(CurrentUser.email);
    }

    @FXML
    private void handleEditProfile(ActionEvent event) throws IOException {
        Stage currStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        EditProfileController.setPrevStage(currStage);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("edit-profile-view.fxml")));
        currStage.setTitle("Log In");
        currStage.setScene(new Scene(root));
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        Stage stage = new Stage(); // Create a new stage
        stage.setTitle("Log In");
        stage.setScene(new Scene(root));

        //set the current user to empty
        CurrentUser.userID = -1;
        CurrentUser.username = "";
        CurrentUser.email = "";

        Stage currStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.show();
        currStage.close(); //close the home page;
    }
}
