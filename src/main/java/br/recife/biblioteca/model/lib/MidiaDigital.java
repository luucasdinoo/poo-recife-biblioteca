package br.recife.biblioteca.model.lib;

public class MidiaDigital extends Recurso{

    private Integer tamanhoMB;
    private String tipoArquivo;

    private MidiaDigital(
            Long id,
            String titulo,
            String anoPublicacao,
            Boolean disponivel,
            Integer tamanhoMB,
            String tipoArquivo
    ) {
        super(id, titulo, anoPublicacao, disponivel);
        this.tamanhoMB = tamanhoMB;
        this.tipoArquivo = tipoArquivo;
    }

    public static MidiaDigital novo(
            Long id,
            String titulo,
            String anoPublicacao,
            Boolean disponivel,
            Integer tamanhoMB,
            String tipoArquivo
    ){
        return new MidiaDigital(id, titulo, anoPublicacao, disponivel, tamanhoMB, tipoArquivo);
    }

    public Integer getTamanhoMB() {
        return tamanhoMB;
    }

    public void setTamanhoMB(Integer tamanhoMB) {
        this.tamanhoMB = tamanhoMB;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    @Override
    public void calcularMulta(long diasAtraso) {

    }
}
