package br.recife.biblioteca.service;

import br.recife.biblioteca.util.IdUtils;
import br.recife.biblioteca.exception.EntityNotFoundException;
import br.recife.biblioteca.model.dto.MidiaDigitalDTO;
import br.recife.biblioteca.model.lib.MidiaDigital;
import br.recife.biblioteca.repository.MidiaDigitalRepository;
import br.recife.biblioteca.repository.impl.MidiaDigitalRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class MidiaDigitalService {

    private MidiaDigitalRepository midiaDigitalRepository = new MidiaDigitalRepositoryImpl();

    public MidiaDigital salvar(MidiaDigitalDTO dto) {
        MidiaDigital midiaDigital = MidiaDigital
                .novo(IdUtils.gerarId() ,dto.titulo(), dto.anoPublicacao(), Boolean.TRUE, dto.tamanhoMB(), dto.tipoArquivo());
        this.midiaDigitalRepository.salvar(midiaDigital);
        return midiaDigital;
    }

    public MidiaDigital buscarPorId(Long id) {
        return this.midiaDigitalRepository.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Midia Digital não encontrada."));
    }

    public List<MidiaDigital> buscarTodos() {
        return this.midiaDigitalRepository.buscarTodos();
    }

    public List<MidiaDigital> buscarPorTitulo(String titulo) {
        String lower = titulo == null ? "" : titulo.toLowerCase();
        return this.midiaDigitalRepository.buscarTodos().stream()
                .filter(m -> m.getTitulo() != null && m.getTitulo().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }

    public void remover(Long id) {
        this.midiaDigitalRepository.remover(id);
    }
}
