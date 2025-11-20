package br.recife.biblioteca.service;

import br.recife.biblioteca.exception.EntityNotFoundException;
import br.recife.biblioteca.model.dto.UsuarioDTO;
import br.recife.biblioteca.model.user.Aluno;
import br.recife.biblioteca.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    @Test
    void deveSalvarAluno_comSucesso() {
        UsuarioDTO dto = new UsuarioDTO("Lucas", "123");

        doNothing().when(alunoRepository).salvar(any(Aluno.class));

        Aluno resultado = alunoService.salvar(dto);

        assertNotNull(resultado);
        assertEquals(dto.nome(), resultado.getNome());
        assertEquals(dto.documento(), resultado.getDocumento());
    }

    @Test
    void naoDeveSalvarAluno_quandoRepositoryFalhar() {
        UsuarioDTO dto = new UsuarioDTO("Lucas", "123");

        doThrow(new RuntimeException("Erro no banco"))
                .when(alunoRepository)
                .salvar(any(Aluno.class));

        assertThrows(RuntimeException.class, () -> alunoService.salvar(dto));
    }

    @Test
    void deveBuscarAlunoPorId_comSucesso() {
        Long id = 1L;
        Aluno aluno = Aluno.novo(id, "Lucas", "123");

        when(alunoRepository.buscarPorId(id)).thenReturn(Optional.of(aluno));

        Aluno resultado = alunoService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
    }

    @Test
    void deveLancarExcecao_quandoBuscarAlunoInexistente() {
        Long id = 99L;

        when(alunoRepository.buscarPorId(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> alunoService.buscarPorId(id));
    }

    @Test
    void deveBuscarTodosAlunos_comSucesso() {
        List<Aluno> lista = List.of(
                Aluno.novo(1L, "Lucas", "123"),
                Aluno.novo(2L, "Maria", "456")
        );

        when(alunoRepository.buscarTodos()).thenReturn(lista);

        List<Aluno> resultado = alunoService.buscarTodos();

        assertEquals(2, resultado.size());
    }

    @Test
    void deveRetornarListaVazia_quandoNaoExistirAlunos() {
        when(alunoRepository.buscarTodos()).thenReturn(List.of());

        List<Aluno> resultado = alunoService.buscarTodos();

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveRemoverAluno_comSucesso() {
        Long id = 1L;

        doNothing().when(alunoRepository).remover(id);

        assertDoesNotThrow(() -> alunoService.remover(id));
    }

    @Test
    void deveLancarErro_quandoFalhaAoRemoverAluno() {
        Long id = 1L;

        doThrow(new RuntimeException("Falha ao remover"))
                .when(alunoRepository)
                .remover(id);

        assertThrows(RuntimeException.class, () -> alunoService.remover(id));
    }
}

