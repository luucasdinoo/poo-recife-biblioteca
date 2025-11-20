package br.recife.biblioteca.service;

import br.recife.biblioteca.exception.EntityNotFoundException;
import br.recife.biblioteca.model.dto.MidiaDigitalDTO;
import br.recife.biblioteca.model.lib.MidiaDigital;
import br.recife.biblioteca.repository.MidiaDigitalRepository;
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
class MidiaDigitalServiceTest {

    @Mock
    private MidiaDigitalRepository midiaRepository;

    @InjectMocks
    private MidiaDigitalService midiaService;

    @Test
    void deveSalvarMidia_comSucesso() {
        MidiaDigitalDTO dto = new MidiaDigitalDTO(10, "mp3", "Musica", "2020");

        doNothing().when(midiaRepository).salvar(any(MidiaDigital.class));

        MidiaDigital res = midiaService.salvar(dto);

        assertNotNull(res);
        assertEquals(dto.titulo(), res.getTitulo());
    }

    @Test
    void deveLancar_quandoNaoEncontrarPorId() {
        when(midiaRepository.buscarPorId(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> midiaService.buscarPorId(1L));
    }

    @Test
    void deveBuscarTodos_comSucesso() {
        MidiaDigital m1 = MidiaDigital.novo(1L, "A", "2020", true, 10, "mp3");
        MidiaDigital m2 = MidiaDigital.novo(2L, "B", "2021", true, 20, "mp4");
        when(midiaRepository.buscarTodos()).thenReturn(List.of(m1, m2));

        var res = midiaService.buscarTodos();

        assertEquals(2, res.size());
    }

    @Test
    void deveBuscarPorTitulo_comSucesso() {
        MidiaDigital m1 = MidiaDigital.novo(1L, "Podcast", "2022", true, 50, "mp3");
        when(midiaRepository.buscarTodos()).thenReturn(List.of(m1));

        var res = midiaService.buscarPorTitulo("podcast");

        assertFalse(res.isEmpty());
    }

    @Test
    void deveRemover_semErro() {
        doNothing().when(midiaRepository).remover(1L);
        assertDoesNotThrow(() -> midiaService.remover(1L));
    }
}

