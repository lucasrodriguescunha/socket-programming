import java.io.*;
import java.net.*;

/**
 * Cliente de chat que se conecta ao servidor para enviar e receber mensagens em tempo real.
 *
 * <p>Este cliente estabelece uma conexão TCP com o servidor de chat e permite que o usuário:</p>
 * <ul>
 *   <li>Envie mensagens digitando no terminal</li>
 *   <li>Receba mensagens de outros clientes conectados ao servidor</li>
 *   <li>Participe de um chat em grupo em tempo real</li>
 * </ul>
 *
 * <p>O cliente utiliza duas threads:</p>
 * <ul>
 *   <li><b>Thread principal:</b> Lê mensagens do teclado e envia para o servidor</li>
 *   <li><b>Thread Recebedor:</b> Recebe mensagens do servidor e exibe no terminal</li>
 * </ul>
 *
 * <p>Configuração padrão:</p>
 * <ul>
 *   <li>Host: 127.0.0.1 (localhost)</li>
 *   <li>Porta: 5000</li>
 *   <li>Protocolo: TCP</li>
 * </ul>
 *
 * @author Lucas
 * @version 1.0
 * @since 2025-09-17
 */
public class Cliente {

    /** Endereço IP do servidor de chat (localhost) */
    private static final String HOST = "127.0.0.1"; // localhost

    /** Porta do servidor de chat (deve coincidir com a porta do servidor) */
    private static final int PORTA = 5000; // Alterado para coincidir com o servidor

    /**
     * Método principal que inicia o cliente e estabelece conexão com o servidor.
     *
     * <p>Este método:</p>
     * <ol>
     *   <li>Estabelece conexão TCP com o servidor</li>
     *   <li>Inicia uma thread para receber mensagens ({@link Recebedor})</li>
     *   <li>Fica em loop lendo mensagens do teclado e enviando para o servidor</li>
     * </ol>
     *
     * <p>Para enviar uma mensagem, digite no terminal e pressione Enter.
     * Para sair do chat, pressione Ctrl+C.</p>
     *
     * @param args argumentos da linha de comando (não utilizados)
     * @throws IOException se ocorrer erro na conexão com o servidor ou na comunicação
     */
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(HOST, PORTA);
        System.out.println("Conectado ao servidor!");

        // Inicia thread para receber mensagens do servidor
        new Thread(new Recebedor(socket)).start();

        // Configura writer para enviar mensagens e reader para ler do teclado
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

        String msg;
        // Loop principal: lê mensagens do teclado e envia para o servidor
        while ((msg = teclado.readLine()) != null) {
            out.println(msg);
        }
    }

    /**
     * Classe interna responsável por receber e exibir mensagens do servidor.
     *
     * <p>Esta classe roda em uma thread separada para permitir que o cliente
     * receba mensagens de outros usuários enquanto pode continuar digitando
     * suas próprias mensagens. Todas as mensagens recebidas são exibidas
     * no terminal precedidas por ">>" para diferenciá-las das mensagens
     * que o próprio usuário está digitando.</p>
     *
     * <p>A thread do Recebedor termina automaticamente quando:</p>
     * <ul>
     *   <li>A conexão com o servidor é perdida</li>
     *   <li>O servidor é desligado</li>
     *   <li>Ocorre um erro de I/O</li>
     * </ul>
     *
     * @author Lucas
     * @version 1.0
     */
    static class Recebedor implements Runnable {

        /** Reader para receber mensagens do servidor */
        private BufferedReader in;

        /**
         * Construtor que inicializa o recebedor com o socket de comunicação.
         *
         * @param socket o socket de comunicação com o servidor
         * @throws IOException se ocorrer erro ao criar o BufferedReader
         */
        public Recebedor(Socket socket) throws IOException {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        /**
         * Método principal da thread que recebe e exibe mensagens do servidor.
         *
         * <p>Fica em loop contínuo aguardando mensagens do servidor. Cada mensagem
         * recebida é exibida no terminal com o prefixo ">>" para indicar que
         * é uma mensagem recebida (não digitada pelo usuário local).</p>
         *
         * <p>O loop termina quando:</p>
         * <ul>
         *   <li>readLine() retorna null (conexão encerrada)</li>
         *   <li>Ocorre uma IOException</li>
         * </ul>
         */
        public void run() {
            try {
                String msg;
                // Loop de recepção: exibe mensagens recebidas do servidor
                while ((msg = in.readLine()) != null) {
                    System.out.println(">> " + msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}