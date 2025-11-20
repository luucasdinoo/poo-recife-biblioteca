package br.recife.biblioteca.repository.impl;

import br.recife.biblioteca.model.user.Servidor;
import br.recife.biblioteca.repository.ServidorRepository;

import java.util.*;

public class ServidorRepositoryImpl implements ServidorRepository {

    private static final Map<Long, Servidor> servidores = new HashMap<>();

    @Override
    public void salvar(Servidor revista) {
        servidores.put(revista.getId(), revista);
    }

    @Override
    public Optional<Servidor> buscarPorId(Long id) {
        return Optional.ofNullable(servidores.get(id));
    }

    @Override
    public List<Servidor> buscarTodos() {
        return new ArrayList<>(servidores.values());
    }

    @Override
    public void remover(Long id) {
        servidores.remove(id);
    }
}
