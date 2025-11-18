package br.recife.biblioteca.repository;

import br.recife.biblioteca.model.lib.Livro;

import java.util.List;
import java.util.Optional;

public interface LivroRepository {

    void salvar(Livro livro);

    Optional<Livro> buscarPorId(Long id);

    List<Livro> buscarTodos();

    void remover(Long id);
}
