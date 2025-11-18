package br.recife.biblioteca.repository;

import br.recife.biblioteca.model.lib.Revista;

import java.util.List;
import java.util.Optional;

public interface RevistaRepository {

    void salvar(Revista revista);

    Optional<Revista> buscarPorId(Long id);

    List<Revista> buscarTodos();

    void remover(Long id);
}
