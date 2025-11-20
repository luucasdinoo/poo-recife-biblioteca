package br.recife.biblioteca.service;

import br.recife.biblioteca.exception.EntityNotFoundException;
import br.recife.biblioteca.model.dto.UsuarioDTO;
import br.recife.biblioteca.model.user.Visitante;
import br.recife.biblioteca.repository.VisitanteRepository;
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
class VisitanteServiceTest {

    @Mock
    private VisitanteRepository visitanteRepository;

    @InjectMocks
    private VisitanteService visitanteService;

    @Test
    void deveSalvarVisitante_comSucesso() {
        UsuarioDTO dto = new UsuarioDTO("Paulo", "321");
        doNothing().when(visitanteRepository).salvar(any(Visitante.class));

        Visitante res = visitanteService.salvar(dto);

        assertNotNull(res);
        assertEquals(dto.nome(), res.getNome());
    }

    @Test
    void deveLancar_quandoNaoEncontrarPorId() {
        when(visitanteRepository.buscarPorId(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> visitanteService.buscarPorId(1L));
    }

    @Test
    void deveBuscarTodos_comSucesso() {
        Visitante v1 = Visitante.novo(1L, "X", "1");
        Visitante v2 = Visitante.novo(2L, "Y", "2");
        when(visitanteRepository.buscarTodos()).thenReturn(List.of(v1, v2));

        var res = visitanteService.buscarTodos();

        assertEquals(2, res.size());
    }

    @Test
    void deveRemover_semErro() {
        doNothing().when(visitanteRepository).remover(1L);
        assertDoesNotThrow(() -> visitanteService.remover(1L));
    }
}

