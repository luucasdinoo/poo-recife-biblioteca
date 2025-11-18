package br.recife.biblioteca.model.user;

public class Visitante extends Usuario {

    private Visitante(Long id, String nome, String documento) {
        super(id, nome, documento);
    }

    public static Visitante novo(Long id, String nome, String documento) {
        return new Visitante(id, nome, documento);
    }

    @Override
    public int prazoDiasPadrao() {
        return 0;
    }

    @Override
    public double fatorMulta() {
        return 0;
    }
}
