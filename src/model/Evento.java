package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Representa um evento sediado em Porto Alegre.
 * Contém obrigatoriamente: nome, endereço, categoria, horário e descrição.
 */
public class Evento {
    private String nome;
    private String endereco;
    private CategoriaEvento categoria;
    private LocalDateTime horario;
    private String descricao;

    public static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Evento(String nome, String endereco, CategoriaEvento categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public CategoriaEvento getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEvento categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public String getHorarioFormatado() {
        return horario.format(FORMATADOR);
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Verifica se o evento está programado para ocorrer nas próximas horas indicadas.
     *
     * @param horas Quantidade de horas de antecedência para aviso.
     * @return true se o evento estiver no intervalo entre agora e o limite de horas.
     */
    public boolean isProximo(long horas) {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime limite = agora.plusHours(horas);
        return horario.isAfter(agora) && horario.isBefore(limite);
    }

    /**
     * Verifica se o evento está ocorrendo no momento (considerando tolerância de 2 horas após início).
     */
    public boolean isEmAndamento() {
        LocalDateTime agora = LocalDateTime.now();
        return (agora.isEqual(horario) || agora.isAfter(horario)) && agora.isBefore(horario.plusHours(2));
    }

    /**
     * Converte uma string no formato "dd/MM/yyyy HH:mm" em LocalDateTime.
     *
     * @param textoDataHora Texto com data e horário.
     * @return LocalDateTime correspondente.
     * @throws DateTimeParseException se a data/hora for inválida.
     */
    public static LocalDateTime parseHorario(String textoDataHora) throws DateTimeParseException {
        return LocalDateTime.parse(textoDataHora, FORMATADOR);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s\n  Local: %s (Porto Alegre)\n  Horário: %s\n  Descrição: %s",
                categoria.getDescricao(), nome, endereco, getHorarioFormatado(), descricao);
    }
}
