import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteGUI extends JFrame {
    private static final String HOST = "localhost";
    private static final int PORTA = 12345;

    private final JTextField campoNome;
    private final JTextField campoMensagem;
    private final JTextArea areaTexto;
    private final JButton botaoEnviar;
    private final JButton botaoConectar;

    private Socket socket;
    private PrintWriter saida;
    private boolean conectado = false;

    public ClienteGUI() {
        setTitle("Cliente - Chat");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelSuperior = new JPanel(new FlowLayout());
        painelSuperior.add(new JLabel("Nome:"));
        campoNome = new JTextField(15);
        painelSuperior.add(campoNome);

        botaoConectar = new JButton("Conectar");
        botaoConectar.addActionListener(e -> conectar());
        painelSuperior.add(botaoConectar);

        add(painelSuperior, BorderLayout.NORTH);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new BorderLayout());
        campoMensagem = new JTextField();
        campoMensagem.setEnabled(false);
        campoMensagem.addActionListener(e -> enviarMensagem());
        painelInferior.add(campoMensagem, BorderLayout.CENTER);

        botaoEnviar = new JButton("Enviar");
        botaoEnviar.setEnabled(false);
        botaoEnviar.addActionListener(e -> enviarMensagem());
        painelInferior.add(botaoEnviar, BorderLayout.EAST);

        add(painelInferior, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void conectar() {
        if (campoNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira seu nome!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            socket = new Socket(HOST, PORTA);
            saida = new PrintWriter(socket.getOutputStream(), true);
            conectado = true;

            areaTexto.append("Conectado ao servidor!\n");
            campoMensagem.setEnabled(true);
            botaoEnviar.setEnabled(true);
            botaoConectar.setEnabled(false);
            campoNome.setEnabled(false);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao servidor: " + e.getMessage(),
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void enviarMensagem() {
        if (!conectado) {
            JOptionPane.showMessageDialog(this, "Você não está conectado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String mensagem = campoMensagem.getText().trim();
        if (mensagem.isEmpty()) {
            return;
        }

        String nome = campoNome.getText().trim();
        String mensagemCompleta = nome + ": " + mensagem;

        saida.println(mensagemCompleta);
        areaTexto.append("Você (" + nome + "): " + mensagem + "\n");
        campoMensagem.setText("");

        if (mensagem.equalsIgnoreCase("sair")) {
            desconectar();
        }
    }

    private void desconectar() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            conectado = false;
            areaTexto.append("Desconectado do servidor!\n");
            campoMensagem.setEnabled(false);
            botaoEnviar.setEnabled(false);
            botaoConectar.setEnabled(true);
            campoNome.setEnabled(true);
        } catch (IOException e) {
            areaTexto.append("Erro ao desconectar: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClienteGUI::new);
    }
}


