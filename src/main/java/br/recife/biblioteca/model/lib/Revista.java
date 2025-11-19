package br.recife.biblioteca.model.lib;

public class Revista extends Recurso{

    private String edicao;
    private Integer periodicidade;

    private Revista(
            Long id,
            String titulo,
            String anoPublicacao,
            Boolean disponivel,
            String edicao,
            Integer periodicidade
    ) {
        super(id, titulo, anoPublicacao, disponivel);
        this.edicao = edicao;
        this.periodicidade = periodicidade;
    }

    public static Revista novo(
            Long id,
            String titulo,
            String anoPublicacao,
            Boolean disponivel,
            String edicao,
            Integer periodicidade
    ){
       return new Revista(id, titulo, anoPublicacao, disponivel, edicao, periodicidade);
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public Integer getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(Integer periodicidade) {
        this.periodicidade = periodicidade;
    }

    @Override
    public double calcularMulta(long diasAtraso) {
        if (diasAtraso <= 0) return 0.0;
        return diasAtraso * 0.75; // R$0.75 por dia para revistas
    }
}
