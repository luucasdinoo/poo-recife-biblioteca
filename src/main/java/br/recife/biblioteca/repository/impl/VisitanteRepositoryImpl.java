package br.recife.biblioteca.repository.impl;

import br.recife.biblioteca.model.user.Visitante;
import br.recife.biblioteca.repository.VisitanteRepository;

import java.util.*;

public class VisitanteRepositoryImpl implements VisitanteRepository {

    private Map<Long, Visitante> visitantes = new HashMap<>();

    @Override
    public void salvar(Visitante revista) {
        visitantes.put(revista.getId(), revista);
    }

    @Override
    public Optional<Visitante> buscarPorId(Long id) {
        return Optional.ofNullable(visitantes.get(id));
    }

    @Override
    public List<Visitante> buscarTodos() {
        return new ArrayList<>(visitantes.values());
    }

    @Override
    public void remover(Long id) {
        visitantes.remove(id);
    }
}
