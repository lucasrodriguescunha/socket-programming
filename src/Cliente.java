import java.io.*;
import java.net.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Cliente {

    private static final String HOST = "127.0.0.1";
    private static final int PORTA = 5000;
    private static final Logger logger = Logger.getLogger(Cliente.class.getName());

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORTA);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado ao servidor!");

            new Thread(new Recebedor(socket)).start();

            String msg;
            while ((msg = teclado.readLine()) != null) {
                out.println(msg);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro na conexão com o servidor", e);
        }
    }

    static class Recebedor implements Runnable {

        private final Socket socket;

        public Recebedor(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println(">> " + msg);
                }
            } catch (IOException e) {
                logger.log(Level.WARNING, "Erro ao receber mensagens do servidor", e);
            }
        }
    }
}