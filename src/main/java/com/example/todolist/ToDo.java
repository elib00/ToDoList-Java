package com.example.todolist;

import com.example.todolist.Server.CreateDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ToDo extends Application {
    CreateDatabase databaseCreator = new CreateDatabase();

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(ToDo.class.getResource("task-card.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Log In");
        stage.setScene(scene);
        databaseCreator.initializeDB();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}