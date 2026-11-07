package model;

/**
 * Representa um usuário cadastrado no sistema de eventos.
 */
public class Usuario {
    private String nome;
    private String email;
    private String telefone;
    private CategoriaEvento categoriaInteresse;

    public Usuario(String nome, String email, String telefone, CategoriaEvento categoriaInteresse) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.categoriaInteresse = categoriaInteresse;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public CategoriaEvento getCategoriaInteresse() {
        return categoriaInteresse;
    }

    public void setCategoriaInteresse(CategoriaEvento categoriaInteresse) {
        this.categoriaInteresse = categoriaInteresse;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s | E-mail: %s | Telefone: %s | Interesse: %s",
                nome, email, telefone, (categoriaInteresse != null ? categoriaInteresse.getDescricao() : "Geral"));
    }
}
