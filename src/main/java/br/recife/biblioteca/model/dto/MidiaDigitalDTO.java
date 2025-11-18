package br.recife.biblioteca.model.dto;

public record MidiaDigitalDTO(
         Integer tamanhoMB,
         String tipoArquivo,
         String titulo,
         String anoPublicacao
) {
}
