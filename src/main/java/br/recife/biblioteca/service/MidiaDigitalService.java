package br.recife.biblioteca.service;

import br.recife.biblioteca.util.IdUtils;
import br.recife.biblioteca.exception.EntityNotFoundException;
import br.recife.biblioteca.model.dto.MidiaDigitalDTO;
import br.recife.biblioteca.model.lib.MidiaDigital;
import br.recife.biblioteca.repository.MidiaDigitalRepository;
import br.recife.biblioteca.repository.impl.MidiaDigitalRepositoryImpl;

import java.util.List;

public class MidiaDigitalService {

    private MidiaDigitalRepository midiaDigitalRepository = new MidiaDigitalRepositoryImpl();

    public void salvar(MidiaDigitalDTO revista) {
        MidiaDigital midiaDigital = MidiaDigital
                .novo(IdUtils.gerarId() ,revista.titulo(), revista.anoPublicacao(), Boolean.TRUE, revista.tamanhoMB(), revista.tipoArquivo());
        this.midiaDigitalRepository.salvar(midiaDigital);
    }

    public MidiaDigital buscarPorId(Long id) {
        return this.midiaDigitalRepository.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Midia Digital não encontrada."));
    }

    public List<MidiaDigital> buscarTodos() {
        return this.midiaDigitalRepository.buscarTodos();
    }

    public void remover(Long id) {
        this.midiaDigitalRepository.remover(id);
    }
}
