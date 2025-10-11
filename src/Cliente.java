import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PORTA = 12345;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(HOST, PORTA);
            System.out.println("Conectado ao servidor na porta " + PORTA + "!");
            System.out.println("Digite suas mensagens (digite 'sair' para encerrar):");

            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            String mensagem;
            while (true) {
                System.out.print("Você: ");
                mensagem = scanner.nextLine();
                saida.println(mensagem);

                if (mensagem.equalsIgnoreCase("sair")) {
                    System.out.println("Encerrando conexão...");
                    break;
                }
            }
            scanner.close();
            saida.close();
            socket.close();
            System.out.println("Desconectado do servidor!");

        } catch (IOException e) {
            System.out.println("Erro ao conectar ao servidor: " + e.getMessage());
            System.out.println("Certifique-se de que o servidor está rodando na porta " + PORTA);
        }
    }
}

