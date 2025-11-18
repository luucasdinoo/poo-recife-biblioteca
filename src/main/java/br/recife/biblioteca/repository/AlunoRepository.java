package br.recife.biblioteca.repository;

import br.recife.biblioteca.model.user.Aluno;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository {

    void salvar(Aluno revista);

    Optional<Aluno> buscarPorId(Long id);

    List<Aluno> buscarTodos();

    void remover(Long id);
}
