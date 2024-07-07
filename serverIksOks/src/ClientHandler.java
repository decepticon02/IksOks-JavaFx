import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends  Thread{
    private Socket socket;
    private BufferedReader prijem;
    private PrintWriter slanje;
    private Role role;
  public ClientHandler(Socket clientSocket){

      socket=clientSocket;
      try {
          prijem=new BufferedReader(new InputStreamReader(socket.getInputStream()));
          slanje= new PrintWriter(socket.getOutputStream());
          Server.izlaziSoketa.add(slanje);

          role=(Server.izlaziSoketa.size()==1 ? Role.IKS:Role.OKS);
          welcome();

          if(Server.izlaziSoketa.size()==2 ){
              for (PrintWriter p: Server.izlaziSoketa) {
                  p.println("$$Igra je pocela");
                  p.flush();

              }
          }




      } catch (IOException e) {
          throw new RuntimeException(e);
      }


  }

    private void welcome() {
      slanje.println("##Povezani ste na server Vas simbol je:" + (role == Role.IKS ? "X" : "O"));
      slanje.flush();
    }

    @Override
    public void run() {
        Igra igra =new Igra();
        String prijemString =null;
        int pozicijaNaTabli;
        try{
            while((prijemString=prijem.readLine())!=null){

                pozicijaNaTabli=-1;
                pozicijaNaTabli=Integer.parseInt(prijemString);
                try{
                    igra.upisiPotez(pozicijaNaTabli);
                    if(igra.pobeda()==1){
                        for (PrintWriter p: Server.izlaziSoketa) {
                            p.println("**"+(role == Role.IKS ? "X" : "O")+ ":"+pozicijaNaTabli);
                            p.flush();
                        }
                        slanje.println("@@Pobedili ste");
                        slanje.flush();
                        if(role==Role.IKS){
                            Server.izlaziSoketa.get(1).println("@@Izgubili ste");
                            Server.izlaziSoketa.get(1).flush();
                        }else{
                            Server.izlaziSoketa.get(0).println("@@Izgubili ste");
                            Server.izlaziSoketa.get(0).flush();
                        }
                    }else{
                        for (PrintWriter p: Server.izlaziSoketa) {
                            p.println("**"+(role == Role.IKS ? "X" : "O")+ ":"+pozicijaNaTabli);
                            p.flush();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    slanje.println("Greska pogresan unos u zauzeto polje");
                    slanje.flush();
                }

        }

        }catch(IOException e){
            Server.izlaziSoketa.remove(slanje); // kada se klijent diskonektuje brisemo ga iz liste
            System.out.println("Klijent "+ role +" se diskonektovao. ");
            for (PrintWriter p: Server.izlaziSoketa) {
                p.println("^^Drugi korisnik se diskonektovao");
                p.flush();
            }
        }

    }
}
