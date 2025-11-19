package br.recife.biblioteca.repository;

import br.recife.biblioteca.model.lib.Emprestimo;

import java.util.List;
import java.util.Optional;

public interface EmprestimoRepository {

    void salvar(Emprestimo emprestimo);

    Optional<Emprestimo> buscarPorId(Long id);

    List<Emprestimo> buscarTodos();

    void remover(Long id);
}

