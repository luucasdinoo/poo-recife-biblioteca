package br.recife.biblioteca.service;

import br.recife.biblioteca.util.IdUtils;
import br.recife.biblioteca.exception.EntityNotFoundException;
import br.recife.biblioteca.model.dto.UsuarioDTO;
import br.recife.biblioteca.model.user.Aluno;
import br.recife.biblioteca.repository.AlunoRepository;
import br.recife.biblioteca.repository.impl.AlunoRepositoryImpl;

import java.util.List;

public class AlunoService {

    private AlunoRepository alunoRepository = new AlunoRepositoryImpl();

    public void salvar(UsuarioDTO dto) {
        Aluno aluno = Aluno.novo(IdUtils.gerarId(), dto.nome(), dto.documento());
        this.alunoRepository.salvar(aluno);
    }

    public Aluno buscarPorId(Long id) {
        return this.alunoRepository.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado."));
    }

    public List<Aluno> buscarTodos() {
        return this.alunoRepository.buscarTodos();
    }

    public void remover(Long id) {
        this.alunoRepository.remover(id);
    }
}
