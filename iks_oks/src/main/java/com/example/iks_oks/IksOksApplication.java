package com.example.iks_oks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import serverCom.ServerHandler;

import java.io.IOException;

public class IksOksApplication extends Application {

    public static ServerHandler server=null;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(IksOksApplication.class.getResource("iks_oks.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 640, 390);



        IksOksController controller= fxmlLoader.getController();
        server=new ServerHandler(controller);
        server.start();

        Image image= new Image("file:src/main/resources/com/example/iks_oks/gameimage.jpg");
        stage.getIcons().add(image);
        stage.setTitle("IKS OKS");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {

        launch();

    }
}