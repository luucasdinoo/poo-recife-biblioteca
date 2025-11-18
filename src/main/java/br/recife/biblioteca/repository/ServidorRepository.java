package br.recife.biblioteca.repository;

import br.recife.biblioteca.model.user.Servidor;

import java.util.List;
import java.util.Optional;

public interface ServidorRepository {

    void salvar(Servidor revista);

    Optional<Servidor> buscarPorId(Long id);

    List<Servidor> buscarTodos();

    void remover(Long id);
}
