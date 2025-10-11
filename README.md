# Programação com Socket
## Comunicação Cliente-Servidor via Sockets (JAVA)

Este projeto implementa uma solução básica de comunicação de processos de rede [1], onde um **Cliente** se conecta a um **Servidor** através de um socket TCP . O principal objetivo é permitir que o cliente envie mensagens que são lidas e exibidas no console do servidor .

O projeto foi desenvolvido em JAVA, e a estrutura de classes inclui `Servidor.java` e `Cliente.java`.

---

## Componentes do Projeto

O sistema é composto por duas classes principais que interagem através da rede:

### 1. Servidor.java (já existente, mantido)

O servidor atua como o ouvinte da rede.

*   Abre `ServerSocket` na porta fixa **12345** .
*   Aceita conexões de clientes .
*   Lê mensagens do `InputStream` do cliente, utilizando um `BufferedReader` , e imprime a mensagem recebida no console .
*   Encerra a conexão com o cliente quando recebe a mensagem **"sair"** (independentemente de maiúsculas/minúsculas) .

### 2. Cliente.java (criado agora)

O cliente inicia a comunicação e interage com o utilizador.

*   Conecta ao servidor via `Socket` no endereço `localhost` e na porta **12345** .
*   Envia mensagens pelo `OutputStream`, utilizando `PrintWriter` para garantir o envio imediato dos dados .
*   Possui uma interface de console, usando `Scanner`, para que o utilizador possa digitar as mensagens .
*   Encerra a conexão quando digita **"sair"** .

---

## Como Testar

Para testar esta comunicação de rede, siga os passos abaixo. Certifique-se de que possui o ambiente JAVA (JDK) configurado.

**Passo 1: Compilação**

Abra um terminal (prompt de comando/shell) na pasta onde os ficheiros `Servidor.java` e `Cliente.java` estão localizados (exemplo fornecido):

```bash
cd C:\Users\Lucas\Documents\socket-programming\src
javac Servidor.java Cliente.java
Passo 2: Execute o Servidor (em um terminal)
O servidor deve ser iniciado primeiro para poder aguardar conexões.
java Servidor
A saída deverá ser: Servidor iniciado com sucesso na porta: 12345!.
Passo 3: Execute o Cliente (em outro terminal)
Abra um segundo terminal e execute o cliente, que tentará conectar-se à porta 12345.
java Cliente
Passo 4: Comunicação
Digite mensagens no terminal do cliente e veja-as aparecerem no terminal do servidor! Digite "sair" para encerrar a conexão em ambos os lados.
Se houver um erro de conexão, o cliente irá informar: "Certifique-se de que o servidor está rodando na porta 12345".

--------------------------------------------------------------------------------
Características Implementadas
• Servidor: Aguarda conexões, recebe e exibe mensagens.
• Cliente: Interface de console para enviar mensagens.
• Comunicação: Cliente → Servidor via socket TCP.
• Encerramento gracioso: O comando "sair" fecha a conexão de forma organizada.
• Tratamento de erros: IOException tratada em ambos os componentes, lidando com falhas na inicialização do servidor ou na tentativa de conexão do cliente.
