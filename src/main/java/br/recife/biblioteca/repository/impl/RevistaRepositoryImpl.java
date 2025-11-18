package br.recife.biblioteca.repository.impl;

import br.recife.biblioteca.model.lib.Revista;

import java.util.*;

public class RevistaRepositoryImpl implements br.recife.biblioteca.repository.RevistaRepository {

    private Map<Long, Revista> revistas = new HashMap<>();

    @Override
    public void salvar(Revista revista) {
        revistas.put(revista.getId(), revista);
    }

    @Override
    public Optional<Revista> buscarPorId(Long id) {
        return Optional.ofNullable(revistas.get(id));
    }

    @Override
    public List<Revista> buscarTodos() {
        return new ArrayList<>(revistas.values());
    }

    @Override
    public void remover(Long id) {
        revistas.remove(id);
    }
}
