package br.recife.biblioteca.service;

import br.recife.biblioteca.util.IdUtils;
import br.recife.biblioteca.exception.EntityNotFoundException;
import br.recife.biblioteca.model.dto.RevistaDTO;
import br.recife.biblioteca.model.lib.Revista;
import br.recife.biblioteca.repository.RevistaRepository;
import br.recife.biblioteca.repository.impl.RevistaRepositoryImpl;

import java.util.List;

public class RevistaService {

    private RevistaRepository revistaRepository = new RevistaRepositoryImpl();

    public void salvar(RevistaDTO dto) {
        Revista revista = Revista
                .novo(IdUtils.gerarId(), dto.titulo(), dto.anoPublicacao(), Boolean.TRUE, dto.edicao(), dto.periodicidade());
        this.revistaRepository.salvar(revista);
    }

    public Revista buscarPorId(Long id) {
        return this.revistaRepository.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Revista não encontrada."));
    }

    public List<Revista> buscarTodos() {
        return this.revistaRepository.buscarTodos();
    }

    public void remover(Long id) {
        this.revistaRepository.remover(id);
    }
}
