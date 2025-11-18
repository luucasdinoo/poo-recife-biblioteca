package br.recife.biblioteca.service;

import br.recife.biblioteca.util.IdUtils;
import br.recife.biblioteca.exception.EntityNotFoundException;
import br.recife.biblioteca.model.dto.UsuarioDTO;
import br.recife.biblioteca.model.user.Visitante;
import br.recife.biblioteca.repository.VisitanteRepository;
import br.recife.biblioteca.repository.impl.VisitanteRepositoryImpl;

import java.util.List;

public class VisitanteService {

    private VisitanteRepository visitanteRepository = new VisitanteRepositoryImpl();

    public void salvar(UsuarioDTO dto) {
        Visitante visitante = Visitante.novo(IdUtils.gerarId(), dto.nome(), dto.documento());
        this.visitanteRepository.salvar(visitante);
    }

    public Visitante buscarPorId(Long id) {
        return this.visitanteRepository.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Visitante não encontrado."));
    }

    public List<Visitante> buscarTodos() {
        return this.visitanteRepository.buscarTodos();
    }

    public void remover(Long id) {
        this.visitanteRepository.remover(id);
    }
}
