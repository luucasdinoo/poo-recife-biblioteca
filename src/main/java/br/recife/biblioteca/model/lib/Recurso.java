package br.recife.biblioteca.model.lib;

public abstract class Recurso {

    private Long id;
    private String titulo;
    private String anoPublicacao;
    private Boolean disponivel;

    protected Recurso(Long id, String titulo, String anoPublicacao, Boolean disponivel) {
        this.id = id;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.disponivel = disponivel;
    }

    public abstract void calcularMulta(long diasAtraso);

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAnoPublicacao() {
        return anoPublicacao;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public String getDescricao(){
        return this.titulo + " - " + this.anoPublicacao;
    }
}
