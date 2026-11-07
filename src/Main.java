import controller.EventoController;
import model.SistemaEventos;
import view.ConsoleView;

/**
 * Ponto de entrada principal da aplicação.
 * Instancia os componentes da arquitetura MVC e inicializa o fluxo do sistema.
 */
public class Main {
    public static void main(String[] args) {
        // Inicialização do Modelo (Model)
        SistemaEventos model = new SistemaEventos();

        // Inicialização da Visão (View)
        ConsoleView view = new ConsoleView();

        // Inicialização do Controlador (Controller) conectando Model e View
        EventoController controller = new EventoController(model, view);

        // Disparo da execução da aplicação
        controller.iniciar();
    }
}
