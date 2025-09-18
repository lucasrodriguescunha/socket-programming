# Sistema de Chat com Socket Programming

Este é um projeto de chat em tempo real implementado em Java usando sockets TCP. O sistema permite que múltiplos clientes se conectem a um servidor central e troquem mensagens entre si.

## 📋 Funcionalidades

- ✅ Servidor multi-threaded que suporta múltiplos clientes simultaneamente
- ✅ Chat em tempo real entre todos os clientes conectados
- ✅ Mensagens broadcast (uma mensagem enviada por um cliente é vista por todos)
- ✅ Conexão e desconexão automática de clientes
- ✅ Interface simples via terminal/console

## 🏗️ Arquitetura do Projeto

```
socket-programming/
├── src/
│   ├── Servidor.java       # Servidor principal com classe ClienteHandler
│   └── Cliente.java        # Cliente com classe Recebedor
├── README.md               # Este arquivo
└── socket-programming.iml  # Arquivo de configuração do IntelliJ
```

### Componentes Principais:

- **Servidor.java**: Contém o servidor principal e a classe interna `ClienteHandler` para gerenciar cada cliente
- **Cliente.java**: Contém o cliente e a classe interna `Recebedor` para receber mensagens
- **Porta padrão**: 5000

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 8 ou superior instalado
- Terminal/Prompt de comando

### Passo 1: Compilar o Projeto

Navegue até o diretório do projeto e compile todos os arquivos Java:

```bash
cd C:\Users\Lucas\IdeaProjects\socket-programming\src
javac *.java
```

Após a compilação, você verá os seguintes arquivos `.class` gerados:
- `Servidor.class`
- `Servidor$ClienteHandler.class`
- `Cliente.class`
- `Cliente$Recebedor.class`

### Passo 2: Executar o Servidor

Abra um terminal e execute o servidor:

```bash
cd C:\Users\Lucas\IdeaProjects\socket-programming\src
java Servidor
```

Você verá a mensagem:
```
Servidor rodando na porta 5000
```

### Passo 3: Executar os Clientes

Para cada cliente que quiser conectar, abra um **novo terminal** e execute:

```bash
cd C:\Users\Lucas\IdeaProjects\socket-programming\src
java Cliente
```

Você verá a mensagem:
```
Conectado ao servidor!
```

## 💬 Como Usar o Chat

1. **Enviar mensagens**: Digite qualquer mensagem no terminal do cliente e pressione Enter
2. **Receber mensagens**: As mensagens de outros clientes aparecerão automaticamente com o prefixo `>>`
3. **Sair do chat**: Pressione `Ctrl+C` para desconectar

### Exemplo de uso:

**Cliente 1:**
```
Conectado ao servidor!
Olá pessoal!
>> João: Oi! Como vocês estão?
Bem, obrigado!
```

**Cliente 2:**
```
Conectado ao servidor!
>> Olá pessoal!
João: Oi! Como vocês estão?
>> Bem, obrigado!
```

## ⚠️ Problemas Comuns

### Erro: "Invalid or corrupt jarfile"
**Problema**: Tentou usar `java -jar` com arquivo `.java`
```bash
# ❌ ERRADO
java -jar Cliente.java

# ✅ CORRETO
java Cliente
```

### Erro: "Could not find or load main class"
**Solução**: Certifique-se de estar no diretório correto e que os arquivos foram compilados:
```bash
cd C:\Users\Lucas\IdeaProjects\socket-programming\src
javac *.java
java Servidor
```

### Erro de conexão
**Solução**: Verifique se:
1. O servidor está rodando antes de conectar os clientes
2. A porta 5000 não está sendo usada por outro programa
3. O firewall não está bloqueando a conexão

## 🔧 Tecnologias Utilizadas

- **Java**: Linguagem de programação
- **Socket Programming**: Comunicação TCP/IP
- **Multithreading**: Para suportar múltiplos clientes
- **BufferedReader/PrintWriter**: Para comunicação de dados

## 📝 Estrutura do Código

### Servidor.java
- **Main**: Aceita conexões e cria threads para cada cliente
- **ClienteHandler**: Gerencia comunicação individual com cada cliente

### Cliente.java  
- **Main**: Conecta ao servidor e envia mensagens do teclado
- **Recebedor**: Thread separada para receber mensagens do servidor

---

**Desenvolvido por Lucas** 🚀
