package br.recife.biblioteca.service;

import br.recife.biblioteca.exception.EntityNotFoundException;
import br.recife.biblioteca.model.dto.UsuarioDTO;
import br.recife.biblioteca.model.user.Servidor;
import br.recife.biblioteca.repository.ServidorRepository;
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
class ServidorServiceTest {

    @Mock
    private ServidorRepository servidorRepository;

    @InjectMocks
    private ServidorService servidorService;

    @Test
    void deveSalvarServidor_comSucesso() {
        UsuarioDTO dto = new UsuarioDTO("Ana", "999");
        doNothing().when(servidorRepository).salvar(any(Servidor.class));

        Servidor res = servidorService.salvar(dto);

        assertNotNull(res);
        assertEquals(dto.nome(), res.getNome());
    }

    @Test
    void deveLancar_quandoNaoEncontrarPorId() {
        when(servidorRepository.buscarPorId(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> servidorService.buscarPorId(1L));
    }

    @Test
    void deveBuscarTodos_comSucesso() {
        Servidor s1 = Servidor.novo(1L, "A", "1");
        Servidor s2 = Servidor.novo(2L, "B", "2");
        when(servidorRepository.buscarTodos()).thenReturn(List.of(s1, s2));

        var res = servidorService.buscarTodos();

        assertEquals(2, res.size());
    }

    @Test
    void deveRemover_semErro() {
        doNothing().when(servidorRepository).remover(1L);
        assertDoesNotThrow(() -> servidorService.remover(1L));
    }
}

