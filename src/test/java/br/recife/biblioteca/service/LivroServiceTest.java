package br.recife.biblioteca.service;

import br.recife.biblioteca.exception.EntityNotFoundException;
import br.recife.biblioteca.model.dto.LivroDTO;
import br.recife.biblioteca.model.lib.Livro;
import br.recife.biblioteca.repository.LivroRepository;
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
class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @InjectMocks
    private LivroService livroService;

    @Test
    void deveSalvarLivro_comSucesso() {
        LivroDTO dto = new LivroDTO("Java", "2022", 1, 10);

        doNothing().when(livroRepository).salvar(any(Livro.class));

        Livro resultado = livroService.salvar(dto);

        assertNotNull(resultado);
        assertEquals(dto.titulo(), resultado.getTitulo());
    }

    @Test
    void naoDeveBuscarLivro_quandoInexistente() {
        when(livroRepository.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> livroService.buscarPorId(1L));
    }

    @Test
    void deveBuscarTodos_comSucesso() {
        Livro l1 = Livro.novo(1L, "A", "2020", true, List.of());
        Livro l2 = Livro.novo(2L, "B", "2021", true, List.of());

        when(livroRepository.buscarTodos()).thenReturn(List.of(l1, l2));

        var res = livroService.buscarTodos();

        assertEquals(2, res.size());
    }

    @Test
    void deveBuscarPorTitulo_comSucesso() {
        Livro l1 = Livro.novo(1L, "Effective Java", "2008", true, List.of());
        when(livroRepository.buscarTodos()).thenReturn(List.of(l1));

        var res = livroService.buscarPorTitulo("effective");

        assertFalse(res.isEmpty());
    }

    @Test
    void deveRemover_semErro() {
        doNothing().when(livroRepository).remover(1L);
        assertDoesNotThrow(() -> livroService.remover(1L));
    }
}

