# Diagrama de Classes UML — Sistema de Eventos

Abaixo encontra-se a modelagem estrutural das classes do sistema, demonstrando as relações de associação, agregação e dependência dentro do padrão arquitetural **MVC**.

---

```mermaid
classDiagram
    direction TB

    class CategoriaEvento {
        <<enumeration>>
        SHOW
        FESTIVAL
        TEATRO
        GASTRONOMIA
        ESPORTE
        CONFERENCIA
        -int codigo
        -String descricao
        +getCodigo() int
        +getDescricao() String
        +porCodigo(int codigo)$ CategoriaEvento
    }

    class Usuario {
        -String nome
        -String email
        -String telefone
        -CategoriaEvento categoriaInteresse
        +getNome() String
        +setNome(String nome) void
        +getEmail() String
        +setEmail(String email) void
        +getTelefone() String
        +setTelefone(String telefone) void
        +getCategoriaInteresse() CategoriaEvento
        +setCategoriaInteresse(CategoriaEvento cat) void
        +toString() String
    }

    class Evento {
        -String nome
        -String endereco
        -CategoriaEvento categoria
        -LocalDateTime horario
        -String descricao
        +getNome() String
        +setNome(String nome) void
        +getEndereco() String
        +setEndereco(String endereco) void
        +getCategoria() CategoriaEvento
        +setCategoria(CategoriaEvento cat) void
        +getHorario() LocalDateTime
        +getHorarioFormatado() String
        +setHorario(LocalDateTime horario) void
        +getDescricao() String
        +setDescricao(String descricao) void
        +isProximo(long horas) boolean
        +isEmAndamento() boolean
        +parseHorario(String texto)$ LocalDateTime
        +toString() String
    }

    class SistemaEventos {
        -List~Usuario~ usuarios
        -List~Evento~ eventos
        +cadastrarUsuario(Usuario usuario) void
        +cadastrarEvento(Evento evento) void
        +getUsuarios() List~Usuario~
        +getEventos() List~Evento~
        +buscarPorCategoria(CategoriaEvento cat) List~Evento~
        +obterNotificacoes() List~String~
        -carregarEventosIniciais() void
    }

    class ConsoleView {
        -Scanner scanner
        +exibirMenuPrincipal() int
        +formularioNovoUsuario() Usuario
        +formularioNovoEvento() Evento
        +selecionarCategoria() CategoriaEvento
        +exibirListaEventos(List~Evento~, String titulo) void
        +exibirListaUsuarios(List~Usuario~) void
        +exibirNotificacoes(List~String~) void
        +exibirMensagem(String msg) void
        +aguardarEnter() void
        -lerInteiro() int
    }

    class EventoController {
        -SistemaEventos model
        -ConsoleView view
        +iniciar() void
        -cadastrarUsuario() void
        -cadastrarEvento() void
        -listarEventos() void
        -consultarPorCategoria() void
        -exibirNotificacoes() void
        -listarUsuarios() void
    }

    class Main {
        +main(String[] args)$ void
    }

    Usuario --> CategoriaEvento : possui
    Evento --> CategoriaEvento : classificado em
    SistemaEventos *-- Usuario : gerencia
    SistemaEventos *-- Evento : gerencia
    EventoController o-- SistemaEventos : coordena
    EventoController o-- ConsoleView : interage
    Main ..> EventoController : instancia
```

---

### Descrição dos Relacionamentos

1. **`Usuario` e `CategoriaEvento`**: O usuário possui uma referência à sua categoria preferida para direcionamento de avisos.
2. **`Evento` e `CategoriaEvento`**: Cada evento obrigatoriamente pertence a uma das categorias delimitadas.
3. **`SistemaEventos`**: Atua como o núcleo da camada **Model**, gerenciando listas em memória de `Usuario` e `Evento`.
4. **`EventoController`**: Coordena o fluxo da aplicação; recebe eventos da `ConsoleView` e manipula as regras de negócio em `SistemaEventos`.
5. **`ConsoleView`**: Isola todas as saídas no terminal e entradas do teclado via `Scanner`.
6. **`Main`**: Ponto de entrada do programa, responsável pela injeção das dependências do padrão MVC.
