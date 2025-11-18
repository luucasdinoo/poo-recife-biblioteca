package br.recife.biblioteca.repository;

import br.recife.biblioteca.model.user.Visitante;

import java.util.List;
import java.util.Optional;

public interface VisitanteRepository {

    void salvar(Visitante revista);

    Optional<Visitante> buscarPorId(Long id);

    List<Visitante> buscarTodos();

    void remover(Long id);
}
