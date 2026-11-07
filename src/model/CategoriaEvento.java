package model;

/**
 * Enumeração que delimita as categorias permitidas para eventos na cidade de Porto Alegre.
 */
public enum CategoriaEvento {
    SHOW(1, "Show / Apresentação Musical"),
    FESTIVAL(2, "Festival / Feira de Rua"),
    TEATRO(3, "Teatro / Cultura"),
    GASTRONOMIA(4, "Gastronomia / Culinária"),
    ESPORTE(5, "Esporte / Atividade Física"),
    CONFERENCIA(6, "Conferência / Palestra");

    private final int codigo;
    private final String descricao;

    CategoriaEvento(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna a categoria a partir do código numérico informado pelo usuário.
     *
     * @param codigo Código numérico correspondente à categoria.
     * @return CategoriaEvento correspondente ou null se inválido.
     */
    public static CategoriaEvento porCodigo(int codigo) {
        for (CategoriaEvento cat : values()) {
            if (cat.getCodigo() == codigo) {
                return cat;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
