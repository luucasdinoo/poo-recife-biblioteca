package br.recife.biblioteca.model.user;

public abstract class Usuario {

    private Long id;
    private String nome;
    private String documento;

    protected Usuario(
            Long id,
            String nome,
            String documento
    ) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public abstract int prazoDiasPadrao();

    public abstract double fatorMulta();
}
