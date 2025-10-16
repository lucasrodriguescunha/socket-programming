import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClienteGUI extends JFrame {

    public ClienteGUI() {

        setTitle("Socket");
        setSize(600, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a aplicação ao fechar o frame
        setLayout(null); // Define o layout como nulo
        setLocationRelativeTo(null); // Centraliza o frame
        setResizable(false);//Desativar o maximinizador


        JTextField msgTxt = new JTextField(20);
        msgTxt.setText("Digite sua mensagem...");
        msgTxt.setBounds(20, 400, 450, 30);

        add(msgTxt);

        JButton botao = new JButton("Enviar");
        botao.setBounds(480, 400, 100, 30);

        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Enviado!");
            }
        });

        add(botao);


    }

}
