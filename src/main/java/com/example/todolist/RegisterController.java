package com.example.todolist;

import com.example.todolist.Server.Status;
import com.example.todolist.Server.CreateNewUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterController {
    @FXML
    private VBox registerContainer;
    @FXML
    private TextField registerUsernameField;
    @FXML
    private PasswordField registerPasswordField;
    @FXML
    private TextField registerEmailField;

    @FXML
    private void handleCreateAccount(ActionEvent event) throws IOException {
        Status res = create();
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Register Status");

        if(res == Status.ACCOUNT_CREATED_SUCCESSFULLY){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
            Stage currStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currStage.setTitle("Log In");
            currStage.setScene(new Scene(root));

            //for the auth status
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Account creation successful!");
            alert.setHeaderText(null);
            alert.showAndWait();
            currStage.show();
        }else{
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Account creation failed. Please try again.");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    private Status create(){
        String username = registerUsernameField.getText();
        String password = registerPasswordField.getText();
        String email = registerEmailField.getText();

        CreateNewUser userCreator = CreateNewUser.getInstance();
        return userCreator.createUser(username, email, password);
    }

}
