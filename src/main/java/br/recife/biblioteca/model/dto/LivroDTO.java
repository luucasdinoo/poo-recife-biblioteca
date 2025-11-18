package br.recife.biblioteca.model.dto;

public record LivroDTO(
        String titulo,
        String anoPublicacao,
        Integer capitulos,
        Integer paginas
) {
}
