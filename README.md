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

**Abra um terminal** e execute o servidor:

```bash
# Opção 1: Executar do diretório src
cd C:\Users\Lucas\IdeaProjects\socket-programming\src
java Servidor

# Opção 2: Executar de qualquer lugar especificando o classpath
java -cp "C:\Users\Lucas\IdeaProjects\socket-programming\src" Servidor
```

Você verá a mensagem:
```
Servidor rodando na porta 5000
```

### Passo 3: Executar os Clientes

**Abra um novo terminal** para cada cliente que deseja conectar:

```bash
# Opção 1: Executar do diretório src
cd C:\Users\Lucas\IdeaProjects\socket-programming\src
java Cliente

# Opção 2: Executar de qualquer lugar especificando o classpath
java -cp "C:\Users\Lucas\IdeaProjects\socket-programming\src" Cliente
```

Você verá a mensagem:
```
Conectado ao servidor!
```

## 💬 Como Usar o Chat

1. **Inicie o servidor** primeiro (Passo 2)
2. **Conecte um ou mais clientes** (Passo 3)
3. **Digite mensagens** no terminal do cliente e pressione Enter
4. **Todas as mensagens** aparecerão em todos os clientes conectados precedidas por `>>`
5. **Para sair**, pressione `Ctrl+C` no terminal

### Exemplo de Uso:

**Terminal do Servidor:**
```
Servidor rodando na porta 5000
Novo cliente conectado Socket[addr=/127.0.0.1,port=62851,localport=5000]
Mensagem recebida: Olá pessoal!
Novo cliente conectado Socket[addr=/127.0.0.1,port=62852,localport=5000]
Mensagem recebida: Oi! Como vocês estão?
```

**Terminal do Cliente 1:**
```
Conectado ao servidor!
Olá pessoal!
>> Olá pessoal!
>> Oi! Como vocês estão?
```

**Terminal do Cliente 2:**
```
Conectado ao servidor!
>> Olá pessoal!
Oi! Como vocês estão?
>> Oi! Como vocês estão?
```

## ⚠️ Erros Comuns e Soluções

### ❌ "Invalid or corrupt jarfile"
**Problema**: Tentativa de usar `java -jar` com arquivos `.java`
```bash
# ❌ ERRADO
java -jar Cliente.java

# ✅ CORRETO
java Cliente
```

### ❌ "Could not find or load main class"
**Problema**: Não estar no diretório correto ou classpath incorreto
```bash
# ✅ SOLUÇÃO 1: Navegar para o diretório correto
cd C:\Users\Lucas\IdeaProjects\socket-programming\src
java Servidor

# ✅ SOLUÇÃO 2: Especificar o classpath completo
java -cp "C:\Users\Lucas\IdeaProjects\socket-programming\src" Servidor
```

### ❌ "Connection refused"
**Problema**: Servidor não está rodando ou porta incorreta
- Certifique-se de que o servidor foi iniciado primeiro
- Verifique se a porta 5000 não está sendo usada por outro processo

## 🔧 Configurações

### Alterar a Porta
Para usar uma porta diferente, modifique a constante `PORTA` nos arquivos:
- `Servidor.java`: linha 11
- `Cliente.java`: linha 6

### Alterar o Host
Para conectar a um servidor remoto, modifique a constante `HOST` no `Cliente.java`: linha 5

## 🛠️ Tecnologias Utilizadas

- **Java SE**: Linguagem principal
- **java.net.Socket**: Para comunicação TCP
- **java.io**: Para entrada/saída de dados
- **java.util.concurrent**: Para sincronização de threads

## 📝 Notas Importantes

- O servidor deve ser iniciado **antes** dos clientes
- Cada cliente roda em uma thread separada no servidor
- As mensagens são enviadas para **todos** os clientes conectados (broadcast)
- O sistema é **case-sensitive** para comandos
- Para parar o servidor, use `Ctrl+C` no terminal

## 🤝 Contribuição

Para melhorar este projeto, você pode:
- Adicionar autenticação de usuários
- Implementar salas de chat privadas
- Adicionar interface gráfica (GUI)
- Implementar comandos especiais (/help, /quit, etc.)
- Adicionar logs mais detalhados

---

**Autor**: Lucas  
**Data**: Setembro 2025  
**Versão**: 1.0
