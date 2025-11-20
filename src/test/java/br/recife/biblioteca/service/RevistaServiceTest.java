package br.recife.biblioteca.service;

import br.recife.biblioteca.exception.EntityNotFoundException;
import br.recife.biblioteca.model.dto.RevistaDTO;
import br.recife.biblioteca.model.lib.Revista;
import br.recife.biblioteca.repository.RevistaRepository;
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
class RevistaServiceTest {

    @Mock
    private RevistaRepository revistaRepository;

    @InjectMocks
    private RevistaService revistaService;

    @Test
    void deveSalvarRevista_comSucesso() {
        RevistaDTO dto = new RevistaDTO("1", 1, "Tech", "2023");
        doNothing().when(revistaRepository).salvar(any(Revista.class));

        Revista res = revistaService.salvar(dto);

        assertNotNull(res);
        assertEquals(dto.titulo(), res.getTitulo());
    }

    @Test
    void deveLancar_quandoNaoEncontrarPorId() {
        when(revistaRepository.buscarPorId(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> revistaService.buscarPorId(1L));
    }

    @Test
    void deveBuscarTodos_comSucesso() {
        Revista r1 = Revista.novo(1L, "A", "2020", true, "Mensal", 1);
        Revista r2 = Revista.novo(2L, "B", "2021", true, "Mensal", 2);
        when(revistaRepository.buscarTodos()).thenReturn(List.of(r1, r2));

        var res = revistaService.buscarTodos();

        assertEquals(2, res.size());
    }

    @Test
    void deveBuscarPorTitulo_comSucesso() {
        Revista r1 = Revista.novo(1L, "Science", "2022", true, "Mensal", 1);
        when(revistaRepository.buscarTodos()).thenReturn(List.of(r1));

        var res = revistaService.buscarPorTitulo("science");

        assertFalse(res.isEmpty());
    }

    @Test
    void deveRemover_semErro() {
        doNothing().when(revistaRepository).remover(1L);
        assertDoesNotThrow(() -> revistaService.remover(1L));
    }
}
