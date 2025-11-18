package br.recife.biblioteca.model.user;

public class Servidor extends Usuario {

    private Servidor(Long id, String nome, String documento) {
        super(id, nome, documento);
    }

    public static Servidor novo(Long id, String nome, String documento) {
        return new Servidor(id, nome, documento);
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
