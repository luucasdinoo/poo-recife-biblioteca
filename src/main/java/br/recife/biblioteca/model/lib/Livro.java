package br.recife.biblioteca.model.lib;

import java.util.List;

public class Livro extends Recurso{

    List<Capitulo> capitulos;

    private Livro(
            Long id,
            String titulo,
            String anoPublicacao,
            Boolean disponivel,
            List<Capitulo> capitulos
    ) {
        super(id, titulo, anoPublicacao, disponivel);
        this.capitulos = capitulos;
    }

    public static Livro novo(
            Long id,
            String titulo,
            String anoPublicacao,
            Boolean disponivel,
            List<Capitulo> capitulos
    ){
        return new Livro(id, titulo, anoPublicacao, disponivel, capitulos);
    }

    public List<Capitulo> getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }

    @Override
    public void calcularMulta(long diasAtraso) {

    }
}
