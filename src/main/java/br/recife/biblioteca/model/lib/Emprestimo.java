package br.recife.biblioteca.model.lib;

import br.recife.biblioteca.model.user.Usuario;

import java.time.LocalDateTime;

public class Emprestimo {

    private Long id;
    private Recurso recurso;
    private Usuario usuario;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataPrevista;
    private String status;

    private Emprestimo(
            Long id,
            Recurso recurso,
            Usuario usuario,
            LocalDateTime dataEmprestimo,
            LocalDateTime dataPrevista,
            String status
    ) {
        this.id = id;
        this.recurso = recurso;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevista = dataPrevista;
        this.status = status;
    }

    public static Emprestimo novo(
            Long id,
            Recurso recurso,
            Usuario usuario,
            LocalDateTime dataEmprestimo,
            LocalDateTime dataPrevista,
            String status
    ){
        return new Emprestimo(id, recurso, usuario, dataEmprestimo, dataPrevista, status);
    }

    public Long getId() {
        return id;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDateTime getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(LocalDateTime dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
