package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Gerenciador central dos dados e regras de negócio do sistema de eventos.
 * Mantém as coleções em memória por meio de estruturas ArrayList.
 */
public class SistemaEventos {
    private final List<Usuario> usuarios;
    private final List<Evento> eventos;

    public SistemaEventos() {
        this.usuarios = new ArrayList<>();
        this.eventos = new ArrayList<>();
        carregarEventosIniciais();
    }

    /**
     * Cadastra um novo usuário no sistema.
     *
     * @param usuario Instância preenchida do usuário.
     */
    public void cadastrarUsuario(Usuario usuario) {
        if (usuario != null) {
            usuarios.add(usuario);
        }
    }

    /**
     * Cadastra um novo evento na lista.
     *
     * @param evento Instância preenchida do evento.
     */
    public void cadastrarEvento(Evento evento) {
        if (evento != null) {
            eventos.add(evento);
            // Ordena os eventos cronologicamente
            eventos.sort(Comparator.comparing(Evento::getHorario));
        }
    }

    /**
     * Retorna uma lista imutável com todos os usuários cadastrados.
     */
    public List<Usuario> getUsuarios() {
        return Collections.unmodifiableList(usuarios);
    }

    /**
     * Retorna uma lista imutável com todos os eventos cadastrados.
     */
    public List<Evento> getEventos() {
        return Collections.unmodifiableList(eventos);
    }

    /**
     * Filtra e retorna eventos que pertencem a uma categoria específica.
     *
     * @param categoria Categoria pesquisada.
     * @return Lista com os eventos da respectiva categoria.
     */
    public List<Evento> buscarPorCategoria(CategoriaEvento categoria) {
        List<Evento> resultado = new ArrayList<>();
        if (categoria == null) {
            return resultado;
        }

        for (Evento evento : eventos) {
            if (evento.getCategoria() == categoria) {
                resultado.add(evento);
            }
        }
        return resultado;
    }

    /**
     * Gera lista de notificações do sistema:
     * - Eventos acontecendo no momento em Porto Alegre.
     * - Eventos programados para as próximas 48 horas.
     *
     * @return Lista de mensagens de notificação.
     */
    public List<String> obterNotificacoes() {
        List<String> notificacoes = new ArrayList<>();
        LocalDateTime agora = LocalDateTime.now();

        for (Evento evento : eventos) {
            if (evento.isEmAndamento()) {
                notificacoes.add(String.format("🔴 ACONTECENDO AGORA: \"%s\" em %s (Categoria: %s)",
                        evento.getNome(), evento.getEndereco(), evento.getCategoria().getDescricao()));
            } else if (evento.isProximo(48)) {
                notificacoes.add(String.format("🔔 EVENTO PRÓXIMO: \"%s\" agendado para %s no local %s",
                        evento.getNome(), evento.getHorarioFormatado(), evento.getEndereco()));
            }
        }

        if (notificacoes.isEmpty()) {
            notificacoes.add("Nenhum evento urgente ou nas próximas 48 horas no momento.");
        }

        return notificacoes;
    }

    /**
     * Popula a lista inicial com eventos em Porto Alegre para demonstração imediata do sistema.
     */
    private void carregarEventosIniciais() {
        LocalDateTime agora = LocalDateTime.now();

        eventos.add(new Evento(
                "Festival Gastronômico da Redenção",
                "Parque Farroupilha (Redenção), Av. Osvaldo Aranha",
                CategoriaEvento.GASTRONOMIA,
                agora.plusHours(2),
                "Feira ao ar livre com pratos típicos gaúchos e gastronomia de rua."
        ));

        eventos.add(new Evento(
                "Apresentação da OSPA",
                "Casa da OSPA - Centro Administrativo Fernando Ferrari",
                CategoriaEvento.SHOW,
                agora.plusHours(26),
                "Concerto clássico da Orquestra Sinfônica de Porto Alegre com solistas convidados."
        ));

        eventos.add(new Evento(
                "Espetáculo Teatral no São Pedro",
                "Theatro São Pedro, Praça Mal. Deodoro, s/n - Centro Histórico",
                CategoriaEvento.TEATRO,
                agora.plusDays(4),
                "Montagem teatral contemporânea premiada no circuito nacional."
        ));

        eventos.add(new Evento(
                "Circuito de Corrida Orla do Guaíba",
                "Av. Edvaldo Pereira Paiva, s/n - Praia de Belas",
                CategoriaEvento.ESPORTE,
                agora.plusDays(6),
                "Corrida rústica de 5km e 10km na orla do lago Guaíba ao entardecer."
        ));

        eventos.sort(Comparator.comparing(Evento::getHorario));
    }
}
