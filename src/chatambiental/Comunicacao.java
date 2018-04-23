
package chatambiental;
import View.TelaChat;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * Classe onde vai ocorrer toda a parte de comunicação do Usuario com o servidor.
 * 
 * @author Math
 */
public class Comunicacao {
    
    /**
     * 
     * 
     * 
     */
    static Socket cli;
    static PrintStream out;
    static BufferedReader in;
    static BufferedReader inputline;
    
    /**
     * 
     * cli cria um Socket(parametro de conexão: ip e porta utilizadas )
     * out (manda para o servidor)
     * in (recebe do servidor)
     * intputline (recebe do cliente)
     * 
     */
    public static String mensagem(){
        try {
        cli = new Socket("localhost", 2222);
        out = new PrintStream(cli.getOutputStream());
        in = new BufferedReader(new InputStreamReader(cli.getInputStream()));      
        
      
        
        new Thread(new resposta()).start();
        
        while (true) {
                out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return in.toString();
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
