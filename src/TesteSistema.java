import model.CategoriaEvento;
import model.Evento;
import model.SistemaEventos;
import model.Usuario;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Suite de verificação e testes automatizados para validar as regras de negócio
 * e funcionamento do Sistema de Eventos em Java.
 */
public class TesteSistema {
    private static int testesExecutados = 0;
    private static int testesAprovados = 0;

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("  INICIANDO BATERIA DE TESTES - SISTEMA DE EVENTOS");
        System.out.println("==================================================");

        testarCadastroUsuario();
        testarCadastroEvento();
        testarFiltroPorCategoria();
        testarNotificacoesTemporalidade();
        testarParserHorario();

        System.out.println("\n--------------------------------------------------");
        System.out.printf("RESULTADO FINAL: %d/%d testes aprovados.\n", testesAprovados, testesExecutados);
        if (testesAprovados == testesExecutados) {
            System.out.println("TODAS AS VERIFICAÇÕES PASSARAM COM SUCESSO!");
        } else {
            System.out.println("ATENÇÃO: Houve falhas em algum dos testes.");
        }
        System.out.println("==================================================");
    }

    private static void testarCadastroUsuario() {
        SistemaEventos sistema = new SistemaEventos();
        Usuario user = new Usuario("Maria Silva", "maria@example.com", "51999990000", CategoriaEvento.TEATRO);
        sistema.cadastrarUsuario(user);

        afirmar("Cadastro de usuário", sistema.getUsuarios().size() == 1);
        afirmar("Integridade dos dados do usuário",
                sistema.getUsuarios().get(0).getEmail().equals("maria@example.com") &&
                sistema.getUsuarios().get(0).getCategoriaInteresse() == CategoriaEvento.TEATRO);
    }

    private static void testarCadastroEvento() {
        SistemaEventos sistema = new SistemaEventos();
        int totalInicial = sistema.getEventos().size();

        Evento novo = new Evento(
                "Feira do Livro de Porto Alegre",
                "Praça da Alfândega - Centro Histórico",
                CategoriaEvento.FESTIVAL,
                LocalDateTime.now().plusDays(2),
                "Maior feira de livros a céu aberto da América Latina."
        );
        sistema.cadastrarEvento(novo);

        afirmar("Adição de novo evento", sistema.getEventos().size() == totalInicial + 1);
        afirmar("Persistência dos atributos do evento",
                novo.getNome().contains("Feira do Livro") &&
                novo.getCategoria() == CategoriaEvento.FESTIVAL &&
                novo.getEndereco().contains("Praça da Alfândega"));
    }

    private static void testarFiltroPorCategoria() {
        SistemaEventos sistema = new SistemaEventos();
        List<Evento> shows = sistema.buscarPorCategoria(CategoriaEvento.SHOW);

        afirmar("Filtro por categoria SHOW retorna resultados válidos", !shows.isEmpty());
        boolean apenasShows = true;
        for (Evento e : shows) {
            if (e.getCategoria() != CategoriaEvento.SHOW) {
                penasShows: apenasShows = false;
                break;
            }
        }
        afirmar("Todos os itens filtrados pertencem à categoria SHOW", apenasShows);
    }

    private static void testarNotificacoesTemporalidade() {
        LocalDateTime agora = LocalDateTime.now();

        Evento eventoAgora = new Evento(
                "Pocket Show Redenção",
                "Monumento ao Expedicionário",
                CategoriaEvento.SHOW,
                agora.minusMinutes(10), // Começou há 10 min
                "Show acústico ao ar livre."
        );

        Evento eventoProximo = new Evento(
                "Oficina de Culinária Gaúcha",
                "Mercado Público Central",
                CategoriaEvento.GASTRONOMIA,
                agora.plusHours(5), // Daqui a 5 horas
                "Oficina prática."
        );

        Evento eventoDistante = new Evento(
                "Maratona Anual",
                "Parque Marinha do Brasil",
                CategoriaEvento.ESPORTE,
                agora.plusDays(30), // Daqui a 30 dias
                "Prova de 42km."
        );

        afirmar("Identificação de evento em andamento", eventoAgora.isEmAndamento());
        afirmar("Identificação de evento próximo (48h)", eventoProximo.isProximo(48));
        afirmar("Evento distante não entra nas próximas 48h", !eventoDistante.isProximo(48));
    }

    private static void testarParserHorario() {
        String dataValida = "20/12/2026 14:30";
        LocalDateTime parsed = Evento.parseHorario(dataValida);

        afirmar("Parser de data/hora válido",
                parsed.getDayOfMonth() == 20 &&
                parsed.getMonthValue() == 12 &&
                parsed.getYear() == 2026 &&
                parsed.getHour() == 14 &&
                parsed.getMinute() == 30);
    }

    private static void afirmar(String descricao, boolean condicao) {
        testesExecutados++;
        if (condicao) {
            testesAprovados++;
            System.out.printf("  [PASSOU] %s\n", descricao);
        } else {
            System.err.printf("  [FALHOU] %s\n", descricao);
        }
    }
}
