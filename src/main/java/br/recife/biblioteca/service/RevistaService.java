package br.recife.biblioteca.service;

import br.recife.biblioteca.util.IdUtils;
import br.recife.biblioteca.exception.EntityNotFoundException;
import br.recife.biblioteca.model.dto.RevistaDTO;
import br.recife.biblioteca.model.lib.Revista;
import br.recife.biblioteca.repository.RevistaRepository;
import br.recife.biblioteca.repository.impl.RevistaRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class RevistaService {

    private RevistaRepository revistaRepository = new RevistaRepositoryImpl();

    public Revista salvar(RevistaDTO dto) {
        Revista revista = Revista
                .novo(IdUtils.gerarId(), dto.titulo(), dto.anoPublicacao(), Boolean.TRUE, dto.edicao(), dto.periodicidade());
        this.revistaRepository.salvar(revista);
        return revista;
    }

    public Revista buscarPorId(Long id) {
        return this.revistaRepository.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Revista não encontrada."));
    }

    public List<Revista> buscarTodos() {
        return this.revistaRepository.buscarTodos();
    }

    public List<Revista> buscarPorTitulo(String titulo) {
        String lower = titulo == null ? "" : titulo.toLowerCase();
        return this.revistaRepository.buscarTodos().stream()
                .filter(r -> r.getTitulo() != null && r.getTitulo().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }

    public void remover(Long id) {
        this.revistaRepository.remover(id);
    }
}
