package br.recife.biblioteca.repository.impl;

import br.recife.biblioteca.model.lib.Emprestimo;
import br.recife.biblioteca.repository.EmprestimoRepository;

import java.util.*;

public class EmprestimoRepositoryImpl implements EmprestimoRepository {

    private static final Map<Long, Emprestimo> emprestimos = new HashMap<>();

    @Override
    public void salvar(Emprestimo emprestimo) {
        emprestimos.put(emprestimo.getId(), emprestimo);
    }

    @Override
    public Optional<Emprestimo> buscarPorId(Long id) {
        return Optional.ofNullable(emprestimos.get(id));
    }

    @Override
    public List<Emprestimo> buscarTodos() {
        return new ArrayList<>(emprestimos.values());
    }

    @Override
    public void remover(Long id) {
        emprestimos.remove(id);
    }
}
