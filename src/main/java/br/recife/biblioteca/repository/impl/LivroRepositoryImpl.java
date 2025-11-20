package br.recife.biblioteca.repository.impl;

import br.recife.biblioteca.model.lib.Livro;
import br.recife.biblioteca.repository.LivroRepository;

import java.util.*;

public class LivroRepositoryImpl implements LivroRepository {

    private static final Map<Long, Livro> livros = new HashMap<>();

    @Override
    public void salvar(Livro livro) {
        livros.put(livro.getId(), livro);
    }

    @Override
    public Optional<Livro> buscarPorId(Long id) {
        return Optional.ofNullable(livros.get(id));
    }

    @Override
    public List<Livro> buscarTodos() {
        return new ArrayList<>(livros.values());
    }

    @Override
    public void remover(Long id) {
        livros.remove(id);
    }
}
