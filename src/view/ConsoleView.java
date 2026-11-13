package view;

import model.CategoriaEvento;
import model.Evento;
import model.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Camada de Visão (View) responsável pela interação com o usuário por meio do terminal.
 * Realiza as leituras, impressões de menus e validações de entrada.
 */
public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Exibe o menu principal e retorna a opção selecionada.
     */
    public int exibirMenuPrincipal() {
        System.out.println("\n==================================================");
        System.out.println("   SISTEMA DE EVENTOS - PORTO ALEGRE (MVC/JAVA)   ");
        System.out.println("==================================================");
        System.out.println(" 1. Cadastrar Usuário");
        System.out.println(" 2. Cadastrar Novo Evento");
        System.out.println(" 3. Listar Todos os Eventos");
        System.out.println(" 4. Consultar Eventos por Categoria");
        System.out.println(" 5. Visualizar Notificações de Eventos");
        System.out.println(" 6. Listar Usuários Cadastrados");
        System.out.println(" 0. Sair");
        System.out.println("==================================================");
        System.out.print("Escolha uma opção: ");

        return lerInteiro();
    }

    /**
     * Coleta os dados para cadastro de um novo usuário.
     */
    public Usuario formularioNovoUsuario() {
        System.out.println("\n--- CADASTRO DE USUÁRIO ---");
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine().trim();
        while (nome.isEmpty()) {
            System.out.print("O nome não pode ser vazio. Digite novamente: ");
            nome = scanner.nextLine().trim();
        }

        System.out.print("E-mail de contato: ");
        String email = scanner.nextLine().trim();
        while (email.isEmpty() || !email.contains("@")) {
            System.out.print("E-mail inválido. Digite um e-mail válido: ");
            email = scanner.nextLine().trim();
        }

        System.out.print("Telefone / WhatsApp: ");
        String telefone = scanner.nextLine().trim();

        System.out.println("\nSelecione a categoria de maior interesse do usuário:");
        CategoriaEvento categoria = selecionarCategoria();

        return new Usuario(nome, email, telefone, categoria);
    }

    /**
     * Coleta os dados para cadastro de um novo evento em Porto Alegre.
     */
    public Evento formularioNovoEvento() {
        System.out.println("\n--- CADASTRO DE NOVO EVENTO ---");
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine().trim();
        while (nome.isEmpty()) {
            System.out.print("O nome não pode ser vazio. Digite novamente: ");
            nome = scanner.nextLine().trim();
        }

        System.out.print("Endereço em Porto Alegre (ex: Parque Marinha, Rua dos Andradas): ");
        String endereco = scanner.nextLine().trim();
        while (endereco.isEmpty()) {
            System.out.print("Endereço obrigatório. Digite novamente: ");
            endereco = scanner.nextLine().trim();
        }

        System.out.println("\nSelecione a categoria do evento:");
        CategoriaEvento categoria = selecionarCategoria();

        LocalDateTime dataHora = null;
        while (dataHora == null) {
            System.out.print("Horário do evento (formato dd/MM/yyyy HH:mm, ex: 15/11/2026 19:30): ");
            String textoDataHora = scanner.nextLine().trim();
            try {
                dataHora = Evento.parseHorario(textoDataHora);
                if (dataHora.isBefore(LocalDateTime.now())) {
                    System.out.println("⚠️ Essa data/hora já passou! Informe um horário futuro.");
                    dataHora = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("⚠️ Formato incorreto ou data inválida! Utilize o padrão dd/MM/yyyy HH:mm.");
            }
        }

        System.out.print("Descrição resumida do evento: ");
        String descricao = scanner.nextLine().trim();
        if (descricao.isEmpty()) {
            descricao = "Sem descrição adicional informada.";
        }

        return new Evento(nome, endereco, categoria, dataHora, descricao);
    }

    /**
     * Apresenta o menu de seleção de categorias e retorna a opção escolhida.
     */
    public CategoriaEvento selecionarCategoria() {
        for (CategoriaEvento cat : CategoriaEvento.values()) {
            System.out.printf(" [%d] %s\n", cat.getCodigo(), cat.getDescricao());
        }
        System.out.print("Opção: ");

        CategoriaEvento categoria = null;
        while (categoria == null) {
            int codigo = lerInteiro();
            categoria = CategoriaEvento.porCodigo(codigo);
            if (categoria == null) {
                System.out.print("Categoria inválida! Digite o número correspondente da lista: ");
            }
        }
        return categoria;
    }

    /**
     * Exibe a listagem de eventos formatada.
     */
    public void exibirListaEventos(List<Evento> eventos, String titulo) {
        System.out.println("\n==================================================");
        System.out.println("   " + titulo.toUpperCase());
        System.out.println("==================================================");

        if (eventos == null || eventos.isEmpty()) {
            System.out.println("Nenhum evento encontrado para esta consulta.");
        } else {
            for (int i = 0; i < eventos.size(); i++) {
                System.out.printf("[%d] %s\n", (i + 1), eventos.get(i).toString());
                System.out.println("--------------------------------------------------");
            }
        }
    }

    /**
     * Exibe a lista de usuários cadastrados.
     */
    public void exibirListaUsuarios(List<Usuario> usuarios) {
        System.out.println("\n==================================================");
        System.out.println("   USUÁRIOS CADASTRADOS NO SISTEMA   ");
        System.out.println("==================================================");

        if (usuarios == null || usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado até o momento.");
        } else {
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.printf("[%d] %s\n", (i + 1), usuarios.get(i).toString());
            }
        }
    }

    /**
     * Exibe o painel de notificações e avisos de eventos.
     */
    public void exibirNotificacoes(List<String> notificacoes) {
        System.out.println("\n==================================================");
        System.out.println("   PAINEL DE NOTIFICAÇÕES (PORTO ALEGRE)   ");
        System.out.println("==================================================");

        for (String msg : notificacoes) {
            System.out.println(msg);
        }
    }

    /**
     * Exibe uma mensagem qualquer no console.
     */
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    /**
     * Pausa para o usuário ler a tela antes de voltar ao menu.
     */
    public void aguardarEnter() {
        System.out.print("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    /**
     * Lê um valor inteiro com tratamento de exceção para evitar travamentos.
     */
    private int lerInteiro() {
        while (true) {
            String linha = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida! Digite um número inteiro: ");
            }
        }
    }
}
