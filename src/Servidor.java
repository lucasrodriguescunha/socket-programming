import java.io.BufferedReader; //le as mensagens do cliente
import java.io.InputStreamReader;  //converte os bytes para texto
import java.io.IOException; //trata erros de entrada/saida (ex: porta ocupada, cliente desconectado, falha na leitura de texto)
import java.net.ServerSocket; //abre a porta e espera uma conexão
import java.net.Socket; //mantem a conexão ativa com um cliente
import java.util.Random; //gerer valores aleatórios


public class Servidor {
    public static void main(String[] args) {
        Random random = new Random();
        final int PortaAleatoria = 10000 + random.nextInt(90000); //gera uma porta aleatória e mostra a mensagem de sucesso ao entrar na porta
        System.out.println("Servidor iniciado com sucesso na porta: " + PortaAleatoria + "!");

        try (ServerSocket servidor = new ServerSocket(PortaAleatoria)){ //cria o servidor, onde o cliente se conctará
            System.out.println("Aguardando conexão de um cliente ...");

            Socket cliente = servidor.accept(); //"accept() bloqueia o programa até ter um cliente, e devolve o Socket (conexão com o cliente)"
            System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());


            //troca de mensagens (pega os bytes, converte em texto, e permite a leitura do texto)
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            String mensagem;
            while ((mensagem = entrada.readLine()) != null) {
                System.out.println("Cliente: "  + mensagem);
            }

            //encerra a conexão e libera a porta
            entrada.close();
            cliente.close();
            System.out.println("Conexão encerrada!");

        }
        //analisa problemas de entrada/saída
        catch(IOException e){
            System.out.println("Erro ao tentar abrir o servidor!" + e.getMessage());
        }
    }
}
