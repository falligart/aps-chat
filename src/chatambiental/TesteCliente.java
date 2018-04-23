    
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class TesteCliente {

    static Socket cli;
    static PrintStream out;
    static BufferedReader in;
    static BufferedReader inputline;

    public static void main(String args[]) {
        String response;
        try {
            cli = new Socket("localhost", 2222);
            out = new PrintStream(cli.getOutputStream());
            in = new BufferedReader(new InputStreamReader(cli.getInputStream()));
            inputline = new BufferedReader(new InputStreamReader(System.in));
            //conexão com o servidor
            
            new Thread(new resposta()).start();
            //Thread para aguardar resposta do servidor
            
            while (true) {
                out.println(inputline.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class resposta implements Runnable {
        String response;
        public void run() {
            try {
                while((response = in.readLine()) != null) {
                    System.out.println(response);
                }
            } catch (IOException ex) {
                System.out.println("Erro");
            }
        }
    }
}
