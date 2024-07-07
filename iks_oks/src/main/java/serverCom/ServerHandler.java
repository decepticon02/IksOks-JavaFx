package serverCom;

import com.example.iks_oks.IksOksController;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServerHandler extends Thread {
    private Socket socket;

    private List<Integer> tabla ;

    private BufferedReader prijem;
    private PrintWriter slanje;
    private IksOksController fxcontroller;
    private String role;
    public ServerHandler(IksOksController fxcontroller){
        tabla = new ArrayList<>(Collections.nCopies(9, 0));
        try {
            this.fxcontroller=fxcontroller;
            socket=new Socket("localhost",8787);
            prijem=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            slanje= new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendBtnClicked(int pos){
        slanje.println(pos);
        slanje.flush();
    }
    @Override
    public void run() {
        String ulaz;

                try {
                while ((ulaz = prijem.readLine()) != null) {
                    String finalUlaz = ulaz;
                    if (ulaz.startsWith("##")) {
                        Platform.runLater(()->{
                            fxcontroller.ispisUTextArea(finalUlaz.substring(2));
                            fxcontroller.blockButton(tabla);
                        });

                        String[] podela = ulaz.split(":");
                        role = podela[1].trim();

                    } else if (ulaz.startsWith("$$")) {

                        System.out.println(ulaz.substring(2));
                        System.out.println("Dozvoli odblokiranje");

                            Platform.runLater(()-> {
                                if (role.equals("X")) {

                                    fxcontroller.enableButton(tabla);
                                }
                                fxcontroller.ispisUTextArea("Igra je pocela");
                            });

                    }  else if (ulaz.startsWith("@@")) {
                        String info= ulaz.substring(2);;

                        Platform.runLater(()-> {
                            fxcontroller.finalWords(info);
                            fxcontroller.blockButton(tabla);
                        });

                        System.out.println(ulaz.substring(2));
                    }



                    else if (ulaz.startsWith("**")) {
                        String[] podela = ulaz.substring(2).split(":");
                        String znak =podela[0].trim();
                        int pozicija =Integer.parseInt(podela[1].trim());
                        tabla.set(pozicija,1);
                        Platform.runLater(()->{
                            fxcontroller.setImg(znak,pozicija);

                        });
                        if(znak.equals(role)){
                            Platform.runLater(()->{
                                fxcontroller.blockButton(tabla);

                            });
                        }else{
                            Platform.runLater(()->{
                                fxcontroller.enableButton(tabla);
                            });
                        }
                    } else if (ulaz.startsWith("^^")) {
                        System.out.println(ulaz.substring(2));
                        System.exit(0);
                    } else {
                        System.out.println("Greskaaa: " + ulaz);
                    }

                }
            } catch(IOException ex){
                System.out.println("odvezali ste se od servera");
            }

    }

}
