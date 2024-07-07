import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static List<PrintWriter> izlaziSoketa = new ArrayList<PrintWriter>(2);

    public  void startServer(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(Main.SERVER_PORT);
            System.out.println("Server started on port " + Main.SERVER_PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                if(izlaziSoketa.size()==2){
                    System.out.println("Maks broj korisnika dostignut");
                    new PrintWriter(clientSocket.getOutputStream()).println("Vec su povezana dva korisnika i u igri su");

                }else{
                    new ClientHandler(clientSocket).start();
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            stopServer(serverSocket);
        }
    }

    public void stopServer(ServerSocket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
