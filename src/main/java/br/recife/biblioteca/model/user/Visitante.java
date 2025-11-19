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
        return 5; // visitantes têm prazo padrão mais curto
    }

    @Override
    public double fatorMulta() {
        return 1.25; // fator de multa um pouco maior
    }
}
