package com.example.todolist;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TaskCardController {
    @FXML
    private Label taskCardTitle;
    @FXML
    private Label taskCardContent;
    public void setTaskCardValues(String title, String content){
        taskCardTitle.setText(title);
        taskCardContent.setText(content);
    }
}
