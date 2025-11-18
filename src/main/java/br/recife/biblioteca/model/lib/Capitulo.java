package br.recife.biblioteca.model.lib;

public class Capitulo {

    private Integer numero;
    private String titulo;
    private Integer paginas;

    private Capitulo(Integer numero, String titulo, Integer paginas) {
        this.numero = numero;
        this.titulo = titulo;
        this.paginas = paginas;
    }

    public static Capitulo novo(Integer numero, String titulo, Integer paginas) {
        return new Capitulo(numero, titulo, paginas);
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }
}
