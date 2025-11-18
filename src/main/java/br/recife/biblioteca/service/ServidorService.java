package br.recife.biblioteca.service;

import br.recife.biblioteca.util.IdUtils;
import br.recife.biblioteca.exception.EntityNotFoundException;
import br.recife.biblioteca.model.dto.UsuarioDTO;
import br.recife.biblioteca.model.user.Servidor;
import br.recife.biblioteca.repository.ServidorRepository;
import br.recife.biblioteca.repository.impl.ServidorRepositoryImpl;

import java.util.List;

public class ServidorService {

    private ServidorRepository servidorRepository = new ServidorRepositoryImpl();

    public void salvar(UsuarioDTO dto) {
        Servidor servidor = Servidor.novo(IdUtils.gerarId(), dto.nome(), dto.documento());
        this.servidorRepository.salvar(servidor);
    }

    public Servidor buscarPorId(Long id) {
        return this.servidorRepository.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Servidor não encontrado."));
    }

    public List<Servidor> buscarTodos() {
        return this.servidorRepository.buscarTodos();
    }

    public void remover(Long id) {
        this.servidorRepository.remover(id);
    }
}
