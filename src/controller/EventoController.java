package controller;

import model.CategoriaEvento;
import model.Evento;
import model.SistemaEventos;
import model.Usuario;
import view.ConsoleView;

import java.util.List;

/**
 * Camada Controladora (Controller) do padrão arquitetural MVC.
 * Coordena as interações entre a interface de console (View) e o gerenciador de dados (Model).
 */
public class EventoController {
    private final SistemaEventos model;
    private final ConsoleView view;

    public EventoController(SistemaEventos model, ConsoleView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Inicia o ciclo principal do sistema com menu e opções.
     */
    public void iniciar() {
        boolean executando = true;

        while (executando) {
            int opcao = view.exibirMenuPrincipal();

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    cadastrarEvento();
                    break;
                case 3:
                    listarEventos();
                    break;
                case 4:
                    consultarPorCategoria();
                    break;
                case 5:
                    exibirNotificacoes();
                    break;
                case 6:
                    listarUsuarios();
                    break;
                case 0:
                    executando = false;
                    view.exibirMensagem("\nEncerrando o Sistema de Eventos de Porto Alegre. Até logo!");
                    break;
                default:
                    view.exibirMensagem("Opção inválida! Escolha um número presente no menu.");
                    view.aguardarEnter();
                    break;
            }
        }
    }

    private void cadastrarUsuario() {
        Usuario usuario = view.formularioNovoUsuario();
        model.cadastrarUsuario(usuario);
        view.exibirMensagem("\n✅ Usuário cadastrado com sucesso!");
        view.aguardarEnter();
    }

    private void cadastrarEvento() {
        Evento evento = view.formularioNovoEvento();
        model.cadastrarEvento(evento);
        view.exibirMensagem("\n✅ Evento em Porto Alegre cadastrado com sucesso!");
        view.aguardarEnter();
    }

    private void listarEventos() {
        List<Evento> eventos = model.getEventos();
        view.exibirListaEventos(eventos, "Todos os Eventos Cadastrados");
        view.aguardarEnter();
    }

    private void consultarPorCategoria() {
        view.exibirMensagem("\nEscolha a categoria que deseja filtrar:");
        CategoriaEvento categoria = view.selecionarCategoria();
        List<Evento> eventos = model.buscarPorCategoria(categoria);
        view.exibirListaEventos(eventos, "Eventos na Categoria: " + categoria.getDescricao());
        view.aguardarEnter();
    }

    private void exibirNotificacoes() {
        List<String> notificacoes = model.obterNotificacoes();
        view.exibirNotificacoes(notificacoes);
        view.aguardarEnter();
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = model.getUsuarios();
        view.exibirListaUsuarios(usuarios);
        view.aguardarEnter();
    }
}
