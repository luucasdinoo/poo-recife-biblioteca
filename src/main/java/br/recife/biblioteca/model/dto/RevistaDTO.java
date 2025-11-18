package br.recife.biblioteca.model.dto;

public record RevistaDTO(
        String edicao,
        Integer periodicidade,
        String titulo,
        String anoPublicacao
) {
}
