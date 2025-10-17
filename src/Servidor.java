import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    private static final int PORTA = 12345;
    private static final List<ClienteHandler> clientes = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            System.out.println("Servidor iniciado na porta " + PORTA);
            System.out.println("Aguardando conexões...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Novo cliente conectado: " + socket.getInetAddress().getHostAddress());

                ClienteHandler clienteHandler = new ClienteHandler(socket);
                clientes.add(clienteHandler);
                new Thread(clienteHandler).start();
            }
        } catch (IOException e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }

    record ClienteHandler(Socket socket) implements Runnable {
        @Override
            public void run() {
                try {
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String mensagem;

                    while ((mensagem = entrada.readLine()) != null) {
                        if (mensagem.equalsIgnoreCase("sair")) {
                            System.out.println("Cliente desconectado: " + socket.getInetAddress().getHostAddress());
                            break;
                        }

                        System.out.println("Mensagem recebida de " + mensagem);
                    }
                } catch (IOException e) {
                    System.out.println("Erro na comunicação com cliente: " + e.getMessage());
                } finally {
                    try {
                        clientes.remove(this);
                        socket.close();
                    } catch (IOException e) {
                        System.out.println("Erro ao fechar socket: " + e.getMessage());
                    }
                }
            }
        }
}

