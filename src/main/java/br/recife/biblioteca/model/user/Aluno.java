package br.recife.biblioteca.model.user;

public class Aluno extends Usuario {

    private Aluno(Long id, String nome, String documento) {
        super(id, nome, documento);
    }

    public static Aluno novo(Long id, String nome, String documento) {
        return new Aluno(id, nome, documento);
    }

    @Override
    public int prazoDiasPadrao() {
        return 14;
    }

    @Override
    public double fatorMulta() {
        return 1.0;
    }
}
