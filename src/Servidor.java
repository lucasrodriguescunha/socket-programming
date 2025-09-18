import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * Servidor de chat multi-threaded que permite múltiplos clientes conectarem simultaneamente.
 *
 * <p>Este servidor utiliza sockets TCP para estabelecer comunicação com clientes.
 * Cada cliente conectado é gerenciado por uma thread separada através da classe
 * {@link ClienteHandler}. As mensagens enviadas por qualquer cliente são
 * retransmitidas (broadcast) para todos os outros clientes conectados.</p>
 *
 * <p>Configuração padrão:</p>
 * <ul>
 *   <li>Porta: 5000</li>
 *   <li>Host: localhost (aceita conexões locais)</li>
 *   <li>Protocolo: TCP</li>
 * </ul>
 *
 * @author Lucas
 * @version 1.0
 * @since 2025-09-17
 */
public class Servidor {

    /** Porta padrão do servidor para aceitar conexões de clientes */
    private static final int PORTA = 5000;

    /**
     * Conjunto thread-safe que armazena os PrintWriters de todos os clientes conectados.
     * Utilizado para broadcast de mensagens para todos os clientes.
     */
    private static final Set<PrintWriter> clientes = new HashSet<>();

    /**
     * Método principal que inicia o servidor e aceita conexões de clientes.
     *
     * <p>O servidor fica em loop infinito aguardando novas conexões. Para cada
     * cliente que se conecta, uma nova thread é criada para gerenciar a comunicação.</p>
     *
     * @param args argumentos da linha de comando (não utilizados)
     * @throws IOException se ocorrer erro na criação do ServerSocket ou aceitar conexões
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Servidor rodando na porta " + PORTA);
        ServerSocket servidor = new ServerSocket(PORTA);

        while (true) {
            Socket socket = servidor.accept();
            System.out.println("Novo cliente conectado " + socket);
            new Thread(new ClienteHandler(socket)).start();
        }
    }

    /**
     * Classe interna responsável por gerenciar a comunicação com um cliente específico.
     *
     * <p>Cada instância desta classe roda em uma thread separada e é responsável por:</p>
     * <ul>
     *   <li>Receber mensagens do cliente</li>
     *   <li>Retransmitir mensagens para todos os clientes conectados</li>
     *   <li>Gerenciar a conexão e desconexão do cliente</li>
     *   <li>Limpar recursos quando o cliente se desconecta</li>
     * </ul>
     *
     * @author Lucas
     * @version 1.0
     */
    static class ClienteHandler implements Runnable {

        /** Socket de comunicação com o cliente específico */
        private Socket socket;

        /** Writer para enviar mensagens para este cliente */
        private PrintWriter out;

        /** Reader para receber mensagens deste cliente */
        private BufferedReader in;

        /**
         * Construtor que inicializa o handler com o socket do cliente.
         *
         * @param socket o socket de comunicação com o cliente
         */
        public ClienteHandler(Socket socket) {
            this.socket = socket;
        }

        /**
         * Método principal da thread que gerencia a comunicação com o cliente.
         *
         * <p>Este método:</p>
         * <ol>
         *   <li>Configura os streams de entrada e saída</li>
         *   <li>Adiciona o cliente à lista de clientes conectados</li>
         *   <li>Fica em loop recebendo mensagens do cliente</li>
         *   <li>Retransmite cada mensagem para todos os clientes</li>
         *   <li>Remove o cliente da lista quando a conexão é encerrada</li>
         * </ol>
         *
         * <p>A sincronização é feita através de blocos synchronized para garantir
         * thread-safety ao acessar a coleção de clientes.</p>
         */
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Adiciona o cliente à lista de forma thread-safe
                synchronized (clientes) {
                    clientes.add(out);
                }

                String msg;
                // Loop principal: recebe e retransmite mensagens
                while ((msg = in.readLine()) != null) {
                    System.out.println("Mensagem recebida: " + msg);

                    // Envia a mensagem para todos os clientes conectados
                    synchronized (clientes) {
                        for (PrintWriter writer : clientes) {
                            writer.println(msg);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Limpa recursos e remove cliente da lista
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                synchronized (clientes) {
                    clientes.remove(out);
                }
            }
        }
    }
}
