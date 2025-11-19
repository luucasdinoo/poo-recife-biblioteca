package br.recife.biblioteca.repository.impl;

import br.recife.biblioteca.model.lib.Emprestimo;
import br.recife.biblioteca.repository.EmprestimoRepository;

import java.util.*;

public class EmprestimoRepositoryImpl implements EmprestimoRepository {

    private Map<Long, Emprestimo> emprestimos = new HashMap<>();

    @Override
    public void salvar(Emprestimo emprestimo) {
        this.emprestimos.put(emprestimo.getId(), emprestimo);
    }

    @Override
    public Optional<Emprestimo> buscarPorId(Long id) {
        return Optional.ofNullable(this.emprestimos.get(id));
    }

    @Override
    public List<Emprestimo> buscarTodos() {
        return new ArrayList<>(this.emprestimos.values());
    }

    @Override
    public void remover(Long id) {
        this.emprestimos.remove(id);
    }
}

