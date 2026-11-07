# Sistema de Gerenciamento de Eventos — Porto Alegre

Aplicação desenvolvida em **Java** (Console) aplicando os conceitos de **Programação Orientada a Objetos (POO)** e o padrão arquitetural **MVC (Model-View-Controller)** para gerenciamento e notificação de eventos na cidade de Porto Alegre.

---

## 1. Visão Geral da Arquitetura (MVC)

O projeto está organizado em camadas bem definidas para separação de responsabilidades:

```
src/
├── Main.java                      # Ponto de entrada da aplicação
├── TesteSistema.java              # Suite de testes automatizados das regras de negócio
├── model/                         # Camada de Dados e Regras de Negócio
│   ├── CategoriaEvento.java       # Enumeração com as categorias delimitadas de eventos
│   ├── Evento.java                # Entidade do Evento com atributos obrigatórios e lógica temporal
│   ├── Usuario.java               # Entidade do Usuário (nome, e-mail, telefone, interesse)
│   └── SistemaEventos.java        # Gerenciador das coleções em memória (ArrayList) e notificações
├── view/                          # Camada de Apresentação / Interação
│   └── ConsoleView.java           # Menus, leitura com Scanner, formatação e tratamento de exceções
└── controller/                    # Camada Controladora
    └── EventoController.java      # Orquestrador entre View e Model
docs/
├── REQUISITOS.md                  # Especificação formal de requisitos funcionais e não funcionais
└── DIAGRAMA_CLASSES.md            # Diagrama de Classes UML completo em formato Mermaid
```

---

## 2. Requisitos e Funcionalidades Atendidas

* **Execução em Console:** Interface interativa via terminal com validação de entrada de dados numéricos e formatos de data/hora.
* **Cadastro de Usuários:** Coleta e valida atributos essenciais (Nome, E-mail, Telefone e Categoria de Interesse).
* **Cadastro de Eventos:** Suporta todos os atributos obrigatórios:
  * Nome do evento
  * Endereço (com foco em Porto Alegre)
  * Categoria delimitada (`Show`, `Festival`, `Teatro`, `Gastronomia`, `Esporte`, `Conferência`)
  * Horário (data e hora no padrão `dd/MM/yyyy HH:mm`)
  * Descrição do evento
* **Consultas e Filtros:**
  * Listagem geral ordenada cronologicamente.
  * Busca e filtragem específica por categoria.
* **Sistema de Notificações:**
  * Alertas dinâmicos no console identificando eventos que estão ocorrendo no momento ou com início programado para as próximas 48 horas.
* **Armazenamento em Memória:** Utilização de `ArrayList` dinâmico para controle de dados em tempo de execução, com dados iniciais para demonstração imediata.

Para a documentação completa dos requisitos funcionais e não funcionais, consulte [docs/REQUISITOS.md](docs/REQUISITOS.md).  
Para visualizar o diagrama de classes UML estrutural, consulte [docs/DIAGRAMA_CLASSES.md](docs/DIAGRAMA_CLASSES.md).

---

## 3. Pré-requisitos

* **Java Development Kit (JDK):** Versão 17 ou superior (testado com OpenJDK 21 LTS).

Verifique sua instalação com:
```bash
javac -version
java -version
```

---

## 4. Compilação e Execução

### Opção 1: Execução Rápida (Windows)
Basta dar um duplo clique no arquivo `executar.bat` ou executá-lo no terminal:
```cmd
executar.bat
```

### Opção 2: Linha de Comando Manual

1. Compile todos os arquivos para a pasta `bin/`:
```bash
javac -d bin -sourcepath src src/model/*.java src/view/*.java src/controller/*.java src/Main.java
```

2. Execute o sistema:
```bash
java -cp bin Main
```

3. (Opcional) Execute a bateria de testes automatizados:
```bash
javac -d bin -sourcepath src src/TesteSistema.java
java -cp bin TesteSistema
```

---

## 5. Estrutura do Menu Interativo

```text
==================================================
   SISTEMA DE EVENTOS - PORTO ALEGRE (MVC/JAVA)   
==================================================
 1. Cadastrar Usuário
 2. Cadastrar Novo Evento
 3. Listar Todos os Eventos
 4. Consultar Eventos por Categoria
 5. Visualizar Notificações de Eventos
 6. Listar Usuários Cadastrados
 0. Sair
==================================================
```

