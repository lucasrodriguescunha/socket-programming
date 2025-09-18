import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    private static final int PORTA = 5000;
    private static final Set<PrintWriter> clientes = new HashSet<>();
    private static final Logger logger = Logger.getLogger(Servidor.class.getName());

    public static void main(String[] args) {
        System.out.println("Servidor rodando na porta " + PORTA);

        try (ServerSocket servidor = new ServerSocket(PORTA)) {
            while (true) {
                Socket socket = servidor.accept();
                System.out.println("Novo cliente conectado " + socket);
                new Thread(new ClienteHandler(socket)).start();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro no servidor", e);
        }
    }

    record ClienteHandler(Socket socket) implements Runnable {

        public void run() {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    synchronized (clientes) {
                        clientes.add(out);
                    }

                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println("Mensagem recebida: " + msg);

                        synchronized (clientes) {
                            for (PrintWriter writer : clientes) {
                                writer.println(msg);
                            }
                        }
                    }
                } catch (IOException e) {
                    logger.log(Level.WARNING, "Erro na comunicação com cliente", e);
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        logger.log(Level.WARNING, "Erro ao fechar socket do cliente", e);
                    }
                    synchronized (clientes) {
                        clientes.removeIf(PrintWriter::checkError);
                    }
                }
            }
        }
}
