package br.recife.biblioteca.repository;

import br.recife.biblioteca.model.lib.MidiaDigital;

import java.util.List;
import java.util.Optional;

public interface MidiaDigitalRepository {

    void salvar(MidiaDigital midiaDigital);

    Optional<MidiaDigital> buscarPorId(Long id);

    List<MidiaDigital> buscarTodos();

    void remover(Long id);
}
