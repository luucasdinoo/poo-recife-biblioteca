package br.recife.biblioteca.service;

import br.recife.biblioteca.exception.EntityNotFoundException;
import br.recife.biblioteca.exception.RecursoIndisponivelException;
import br.recife.biblioteca.model.lib.Emprestimo;
import br.recife.biblioteca.model.lib.Livro;
import br.recife.biblioteca.model.lib.MidiaDigital;
import br.recife.biblioteca.model.user.Aluno;
import br.recife.biblioteca.model.user.Visitante;
import br.recife.biblioteca.repository.EmprestimoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmprestimoServiceTest {

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @Mock
    private LivroService livroService;

    @Mock
    private MidiaDigitalService midiaService;

    @Mock
    private AlunoService alunoService;

    @Mock
    private VisitanteService visitanteService;

    @InjectMocks
    private EmprestimoService emprestimoService;

    @Test
    void deveEmprestarLivroParaAluno_comSucesso() {
        Livro livro = Livro.novo(1L, "Java", "2020", true, List.of());
        Aluno aluno = Aluno.novo(2L, "Aluno", "111");

        when(livroService.buscarPorId(1L)).thenReturn(livro);
        when(alunoService.buscarPorId(2L)).thenReturn(aluno);
        doNothing().when(emprestimoRepository).salvar(any(Emprestimo.class));

        Emprestimo e = emprestimoService.emprestarLivroParaAluno(1L, 2L);

        assertNotNull(e);
        assertEquals("EMPRESTADO", e.getStatus());
        assertFalse(e.getRecurso().getDisponivel());
    }

    @Test
    void naoDeveEmprestarLivro_quandoIndisponivel() {
        Livro livro = Livro.novo(1L, "Java", "2020", false, List.of());
        Aluno aluno = Aluno.novo(2L, "Aluno", "111");

        when(livroService.buscarPorId(1L)).thenReturn(livro);
        when(alunoService.buscarPorId(2L)).thenReturn(aluno);

        assertThrows(RecursoIndisponivelException.class, () -> emprestimoService.emprestarLivroParaAluno(1L, 2L));
    }

    @Test
    void deveEmprestarMidiaParaVisitante_comSucesso() {
        MidiaDigital midia = MidiaDigital.novo(3L, "Podcast", "2021", true, 10, "mp3");
        Visitante visitante = Visitante.novo(4L, "Visitante", "222");

        when(midiaService.buscarPorId(3L)).thenReturn(midia);
        when(visitanteService.buscarPorId(4L)).thenReturn(visitante);
        doNothing().when(emprestimoRepository).salvar(any(Emprestimo.class));

        Emprestimo e = emprestimoService.emprestarMidiaParaVisitante(3L, 4L);

        assertNotNull(e);
        assertEquals("EMPRESTADO", e.getStatus());
        assertFalse(e.getRecurso().getDisponivel());
    }

    @Test
    void naoDeveEmprestarMidia_quandoIndisponivel() {
        MidiaDigital midia = MidiaDigital.novo(3L, "Podcast", "2021", false, 10, "mp3");
        Visitante visitante = Visitante.novo(4L, "Visitante", "222");

        when(midiaService.buscarPorId(3L)).thenReturn(midia);
        when(visitanteService.buscarPorId(4L)).thenReturn(visitante);

        assertThrows(RecursoIndisponivelException.class, () -> emprestimoService.emprestarMidiaParaVisitante(3L, 4L));
    }

    @Test
    void deveDevolverSemMulta_quandoDentroDoPrazo() {
        Livro livro = Livro.novo(1L, "Java", "2020", false, List.of());
        Aluno aluno = Aluno.novo(2L, "Aluno", "111");
        LocalDateTime now = LocalDateTime.now();
        Emprestimo emp = Emprestimo.novo(10L, livro, aluno, now.minusDays(1), now.plusDays(5), "EMPRESTADO");

        when(emprestimoRepository.buscarPorId(10L)).thenReturn(Optional.of(emp));
        doNothing().when(emprestimoRepository).salvar(emp);

        double multa = emprestimoService.devolver(10L);

        assertEquals(0.0, multa);
        assertEquals("DEVOLVIDO", emp.getStatus());
        assertTrue(emp.getRecurso().getDisponivel());
    }

    @Test
    void deveCalcularMulta_quandoAtrasado() {
        Livro livro = Livro.novo(1L, "Java", "2020", false, List.of());
        Aluno aluno = Aluno.novo(2L, "Aluno", "111");
        LocalDateTime now = LocalDateTime.now();
        Emprestimo emp = Emprestimo.novo(11L, livro, aluno, now.minusDays(20), now.minusDays(5), "EMPRESTADO");

        when(emprestimoRepository.buscarPorId(11L)).thenReturn(Optional.of(emp));
        doNothing().when(emprestimoRepository).salvar(emp);

        double multa = emprestimoService.devolver(11L);

        assertTrue(multa > 0.0);
        assertEquals("DEVOLVIDO", emp.getStatus());
        assertTrue(emp.getRecurso().getDisponivel());
    }

    @Test
    void deveLancar_quandoEmprestimoNaoExistir() {
        when(emprestimoRepository.buscarPorId(999L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> emprestimoService.devolver(999L));
    }

    @Test
    void relatorioEmprestimosAtuais_deveFiltrarApenasEmprestados() {
        Livro l = Livro.novo(1L, "A", "2020", false, List.of());
        Emprestimo e1 = Emprestimo.novo(20L, l, Aluno.novo(2L, "X", "1"), LocalDateTime.now(), LocalDateTime.now().plusDays(1), "EMPRESTADO");
        Emprestimo e2 = Emprestimo.novo(21L, l, Aluno.novo(3L, "Y", "2"), LocalDateTime.now(), LocalDateTime.now().plusDays(1), "DEVOLVIDO");

        when(emprestimoRepository.buscarTodos()).thenReturn(List.of(e1, e2));

        var res = emprestimoService.relatorioEmprestimosAtuais();

        assertEquals(1, res.size());
        assertEquals("EMPRESTADO", res.get(0).getStatus());
    }

    @Test
    void relatorioAtrasados_deveRetornarApenasAtrasados() {
        Livro l = Livro.novo(1L, "A", "2020", false, List.of());
        Emprestimo e1 = Emprestimo.novo(30L, l, Aluno.novo(2L, "X", "1"), LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(5), "EMPRESTADO");
        Emprestimo e2 = Emprestimo.novo(31L, l, Aluno.novo(3L, "Y", "2"), LocalDateTime.now(), LocalDateTime.now().plusDays(5), "EMPRESTADO");

        when(emprestimoRepository.buscarTodos()).thenReturn(List.of(e1, e2));

        var res = emprestimoService.relatorioAtrasados();

        assertEquals(1, res.size());
        assertEquals(30L, res.get(0).getId().longValue());
    }
}

