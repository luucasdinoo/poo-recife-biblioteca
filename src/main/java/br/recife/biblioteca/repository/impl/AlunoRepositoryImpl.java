package br.recife.biblioteca.repository.impl;

import br.recife.biblioteca.model.user.Aluno;
import br.recife.biblioteca.repository.AlunoRepository;

import java.util.*;

public class AlunoRepositoryImpl implements AlunoRepository {

    private static final Map<Long, Aluno> alunos = new HashMap<>();

    @Override
    public void salvar(Aluno revista) {
        alunos.put(revista.getId(), revista);
    }

    @Override
    public Optional<Aluno> buscarPorId(Long id) {
        return Optional.ofNullable(alunos.get(id));
    }

    @Override
    public List<Aluno> buscarTodos() {
        return new ArrayList<>(alunos.values());
    }

    @Override
    public void remover(Long id) {
        alunos.remove(id);
    }
}
