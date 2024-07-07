package com.example.iks_oks;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class IksOksController {

    private Boolean gamestartFlag =false;
    @FXML
    private TextArea textArea;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button b0;
    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b8;



    @FXML
    protected void btnclick0() {
        if(IksOksApplication.server!=null){
            IksOksApplication.server.sendBtnClicked(0);
        }else{
            System.out.println("nije poslato null");
        }

    }
    @FXML
    protected void btnclick1() {
        if(IksOksApplication.server!=null){
            IksOksApplication.server.sendBtnClicked(1);
        }else{
            System.out.println("nije poslato null");
        }
        }
    @FXML
    protected void btnclick2() {
        if(IksOksApplication.server!=null){
            IksOksApplication.server.sendBtnClicked(2);
        }else{
            System.out.println("nije poslato null");
        }
    }
    @FXML
    protected void btnclick3() {
        if(IksOksApplication.server!=null){
            IksOksApplication.server.sendBtnClicked(3);
        }else{
            System.out.println("nije poslato null");
        }}
    @FXML
    protected void btnclick4() {
        if(IksOksApplication.server!=null){
            IksOksApplication.server.sendBtnClicked(4);
        }else{
            System.out.println("nije poslato null");
        }}
    @FXML
    protected void btnclick5() {
        if(IksOksApplication.server!=null){
            IksOksApplication.server.sendBtnClicked(5);
        }else{
            System.out.println("nije poslato null");
        }}
    @FXML
    protected void btnclick6() {
        if(IksOksApplication.server!=null){
            IksOksApplication.server.sendBtnClicked(6);
        }else{
            System.out.println("nije poslato null");
        }}
    @FXML
    protected void btnclick7() {
        if(IksOksApplication.server!=null){
            IksOksApplication.server.sendBtnClicked(7);
        }else{
            System.out.println("nije poslato null");
        }}
    @FXML
    protected void btnclick8() {
        if(IksOksApplication.server!=null){
            IksOksApplication.server.sendBtnClicked(8);
        }else{
            System.out.println("nije poslato null");
        }}



    public void setGamestartFlag(){
        this.gamestartFlag=true;
        // ENABLE ALL BUTTONS
    }
    public  void ispisUTextArea(String poruka){
        textArea.appendText("\n");
        textArea.appendText(poruka);
    }

    public void setImg(String znak, int pozicija){

        Button pomButton=(Button)(gridPane.getChildren().get(pozicija));
        pomButton.setDisable(true);
        ImageView imgview=new ImageView(new Image("file:src/main/resources/com/example/iks_oks/"+ (znak.equals("X") ?"iks":"oks")+".png"));
        imgview.setFitHeight(50);
        imgview.setFitWidth(50);
        pomButton.setGraphic(imgview);
    }

    public void blockButton( List<Integer> tabla){
        for(int i=0;i<9;i++){
            if(tabla.get(i)==0){
                ( (Button)(gridPane.getChildren().get(i))).setDisable(true);
            }
        }
    }
    public void enableButton( List<Integer> tabla){
        for(int i=0;i<9;i++){
            if(tabla.get(i)==0){
                ( (Button)(gridPane.getChildren().get(i))).setDisable(false);
            }
        }
    }

    public void finalWords(String info){
        if(info.equalsIgnoreCase("Pobedili ste")){
           Alert alert= new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("POBEDA");
                    alert.setHeaderText("CESTITAMO POBEDILI STE");
                    alert.setContentText("DA LI ZELITE DA SACUVATE IGRU?");
                    alert.getButtonTypes().clear();
                    alert.getButtonTypes().addAll(ButtonType.YES,ButtonType.NO);


                    alert.showAndWait().ifPresent(response->{
                        if(response==ButtonType.YES){
                            new SaveToDataBaseDialog();
                        }else{
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run(){System.exit(0);}
                            },4000,1000);


                        }
                    });

        }else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PORAZ");
            alert.setHeaderText("NAZALOST, IZGUBILI STE");
            alert.showAndWait().ifPresent(response->{
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run(){System.exit(0);}
                },5000,10000);

            });
        }
    }
}