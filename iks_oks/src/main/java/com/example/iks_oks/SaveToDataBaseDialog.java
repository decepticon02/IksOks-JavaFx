package com.example.iks_oks;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Timer;
import java.util.TimerTask;

public class SaveToDataBaseDialog {
    public SaveToDataBaseDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Unos imena");
        alert.setHeaderText("Unesite korisnicko ime:");


        TextField textField = new TextField();
        textField.setPromptText("Korisnicko ime");


        GridPane grid = new GridPane();
        grid.setMaxWidth(Double.MAX_VALUE);
        grid.add(textField, 0, 0);


        alert.getDialogPane().setContent(grid);


        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String username = textField.getText();
                new Thread(() -> {
                    new DataBase("src/main/resources/baza.db").writeToDataBase(username);
                }).start();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run(){System.exit(0);}
                },4000,1000);
            }
        });
    }
}
