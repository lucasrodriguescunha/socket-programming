# Sistema de Chat com Socket Programming

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Sockets](https://img.shields.io/badge/Sockets-TCP%2FIP-blue?style=for-the-badge)
![Swing](https://img.shields.io/badge/Swing-GUI-orange?style=for-the-badge)

## Descrição

Este projeto é um sistema de chat cliente-servidor desenvolvido em Java utilizando socket programming. O sistema permite que múltiplos clientes se conectem simultaneamente a um servidor central e enviem mensagens identificadas por seus nomes.

**Qual foi minha motivação?**

A motivação foi aprender e aplicar conceitos fundamentais de programação de redes, incluindo comunicação TCP/IP, programação concorrente com threads e desenvolvimento de interfaces gráficas em Java.

**Por que construí esse projeto?**

Construí este projeto para demonstrar na prática como funciona a comunicação entre aplicações através de sockets, implementando um servidor capaz de gerenciar múltiplas conexões simultâneas e clientes com interface gráfica amigável.

**Que problema ele resolve?**

O projeto resolve o problema de comunicação em rede entre múltiplos usuários, permitindo que diferentes aplicações clientes se conectem a um servidor central e troquem mensagens de forma assíncrona e identificada.

**O que aprendi?**

- Implementação de sockets TCP/IP em Java
- Programação concorrente com threads para gerenciar múltiplos clientes
- Desenvolvimento de interfaces gráficas com Swing
- Gerenciamento de streams de entrada e saída
- Tratamento de conexões e desconexões de clientes
- Boas práticas de estruturação de código cliente-servidor

## Índice

- [Instalação](#instalação)
- [Uso](#uso)
- [Características](#características)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Como Funciona](#como-funciona)
- [Créditos](#créditos)
- [Licença](#licença)

## Instalação

### Pré-requisitos

- Java Development Kit (JDK) 8 ou superior
- Editor de código ou IDE (recomendado: IntelliJ IDEA, Eclipse, VS Code)

### Passos de Instalação

1. Clone o repositório ou baixe os arquivos do projeto:
```bash
git clone https://github.com/seu-usuario/socket-programming.git
```

2. Navegue até o diretório do projeto:
```bash
cd socket-programming/src
```

3. Compile os arquivos Java:
```bash
javac Servidor.java ClienteGUI.java Cliente.java
```

## Uso

### 1. Iniciando o Servidor

Primeiro, inicie o servidor que ficará escutando na porta 12345:

```bash
java Servidor
```

Você verá a mensagem:
```
Servidor iniciado na porta 12345
Aguardando conexões...
```

### 2. Conectando Clientes com Interface Gráfica

Em terminais separados (ou executando múltiplas instâncias), inicie os clientes GUI:

```bash
java ClienteGUI
```

**Passos para usar o ClienteGUI:**

1. Digite seu nome no campo "Nome"
2. Clique no botão "Conectar"
3. Digite sua mensagem no campo de texto inferior
4. Clique em "Enviar" ou pressione Enter
5. Para sair, digite "sair" e envie

### 3. Cliente de Linha de Comando (Opcional)

Você também pode usar o cliente de linha de comando:

```bash
java Cliente
```

### Exemplo de Uso

**Terminal do Servidor:**
```
Servidor iniciado na porta 12345
Aguardando conexões...
Novo cliente conectado: 127.0.0.1
Mensagem recebida: Lucas: Olá pessoal!
Novo cliente conectado: 127.0.0.1
Mensagem recebida: Maria: Oi Lucas, tudo bem?
Mensagem recebida: Lucas: Tudo ótimo!
```

**ClienteGUI (Lucas):**
```
Nome: Lucas
[Conectar]
Conectado ao servidor!
Você (Lucas): Olá pessoal!
Você (Lucas): Tudo ótimo!
```

**ClienteGUI (Maria):**
```
Nome: Maria
[Conectar]
Conectado ao servidor!
Você (Maria): Oi Lucas, tudo bem?
```

## Características

✅ **Suporte a Múltiplos Clientes**: O servidor pode gerenciar vários clientes conectados simultaneamente usando threads

✅ **Interface Gráfica Intuitiva**: Cliente com GUI desenvolvida em Swing, fácil de usar

✅ **Identificação de Usuários**: Cada mensagem é identificada com o nome do remetente

✅ **Gerenciamento de Conexões**: Conectar e desconectar de forma segura

✅ **Comunicação em Tempo Real**: Mensagens são enviadas e recebidas instantaneamente

✅ **Tratamento de Erros**: Gerenciamento adequado de exceções e desconexões inesperadas

✅ **Cliente de Linha de Comando**: Opção alternativa ao cliente GUI

## Estrutura do Projeto

```
socket-programming/
│
├── README.md
├── socket-programming.iml
│
└── src/
    ├── Servidor.java          # Servidor multi-threaded
    ├── ClienteGUI.java        # Cliente com interface gráfica
    └── Cliente.java           # Cliente de linha de comando
```

### Arquivos Principais

**Servidor.java**
- Gerencia múltiplas conexões de clientes usando threads
- Classe interna `ClienteHandler` para tratar cada conexão
- Porta padrão: 12345

**ClienteGUI.java**
- Interface gráfica com Swing
- Campos para nome de usuário e mensagens
- Área de texto para histórico

**Cliente.java**
- Cliente básico de linha de comando
- Alternativa ao ClienteGUI

## Como Funciona

### Arquitetura

O sistema utiliza a arquitetura **cliente-servidor** com comunicação TCP/IP:

1. **Servidor**: Escuta na porta 12345 e aceita múltiplas conexões. Para cada cliente conectado, cria uma thread separada (`ClienteHandler`) que gerencia a comunicação.

2. **Cliente**: Conecta-se ao servidor, permite que o usuário insira seu nome e envia mensagens no formato "Nome: mensagem".

3. **Comunicação**: As mensagens são enviadas via `PrintWriter` e recebidas via `BufferedReader` sobre streams de socket.

### Fluxo de Comunicação

```
Cliente 1 ----> Socket ----> Servidor (Thread 1)
Cliente 2 ----> Socket ----> Servidor (Thread 2)
Cliente 3 ----> Socket ----> Servidor (Thread 3)
```

### Protocolo de Mensagens

- Formato: `Nome: mensagem`
- Comando especial: `sair` (encerra a conexão)

## Como Contribuir

Contribuições são bem-vindas! Se você deseja melhorar este projeto:

1. Faça um fork do repositório
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

### Ideias para Contribuições

- Implementar broadcast de mensagens (todos os clientes recebem as mensagens)
- Adicionar salas de chat
- Implementar sistema de autenticação
- Adicionar criptografia nas mensagens
- Criar interface gráfica mais moderna (JavaFX)
- Adicionar suporte a emojis
- Implementar histórico de mensagens persistente

## Créditos

Desenvolvido por [Lucas](https://github.com/seu-usuario)

### Recursos Utilizados

- [Oracle Java Documentation - Socket Programming](https://docs.oracle.com/javase/tutorial/networking/sockets/)
- [Java Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)
- [Baeldung - Java Networking](https://www.baeldung.com/java-networking)

## Licença

Este projeto está licenciado sob a [MIT License](https://choosealicense.com/licenses/mit/).

---

## Próximos Passos

- [ ] Implementar broadcast de mensagens entre clientes
- [ ] Adicionar interface gráfica ao servidor para visualizar clientes conectados
- [ ] Implementar sistema de salas de chat
- [ ] Adicionar autenticação de usuários
- [ ] Criar testes unitários
- [ ] Adicionar logging com Log4j
- [ ] Implementar reconexão automática

---

**Nota**: Este projeto foi desenvolvido para fins educacionais e demonstração de conceitos de programação de redes em Java.

Para dúvidas ou sugestões, abra uma issue no repositório!
