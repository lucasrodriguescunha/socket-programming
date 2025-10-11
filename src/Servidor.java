import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final int PORTA = 12345;

    public static void main(String[] args) {
        System.out.println("Servidor iniciado com sucesso na porta: " + PORTA + "!");

        try (ServerSocket servidor = new ServerSocket(PORTA)){
            System.out.println("Aguardando conexão de um cliente ...");

            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            String mensagem;
            System.out.println("Aguardando mensagens do cliente...");
            while ((mensagem = entrada.readLine()) != null) {
                System.out.println("Cliente: " + mensagem);

                if (mensagem.equalsIgnoreCase("sair")) {
                    System.out.println("Cliente solicitou encerramento.");
                    break;
                }
            }
            entrada.close();
            cliente.close();
            System.out.println("Conexão encerrada!");
        }
        catch(IOException e){
            System.out.println("Erro ao tentar abrir o servidor! " + e.getMessage());
        }
    }
}
