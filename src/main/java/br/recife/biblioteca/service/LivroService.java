package br.recife.biblioteca.service;

import br.recife.biblioteca.util.IdUtils;
import br.recife.biblioteca.exception.EntityNotFoundException;
import br.recife.biblioteca.model.dto.LivroDTO;
import br.recife.biblioteca.model.lib.Capitulo;
import br.recife.biblioteca.model.lib.Livro;
import br.recife.biblioteca.repository.LivroRepository;
import br.recife.biblioteca.repository.impl.LivroRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class LivroService {

    private LivroRepository livroRepository = new LivroRepositoryImpl();

    public void salvar(LivroDTO dto) {
        List<Capitulo> capitulos = new ArrayList<>();
        for (int i = 1; i <= dto.capitulos(); i++) {
            Capitulo cap = Capitulo.novo(
                    i,
                    "Capítulo " + i,
                    dto.paginas()
            );
            capitulos.add(cap);
        }
        Livro livro = Livro.novo(
                IdUtils.gerarId(),
                dto.titulo(),
                dto.anoPublicacao(),
                Boolean.TRUE,
                capitulos
        );
        this.livroRepository.salvar(livro);
    }

    public Livro buscarPorId(Long id) {
        return this.livroRepository.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));
    }

    public List<Livro> buscarTodos() {
        return this.livroRepository.buscarTodos();
    }

    public void remover(Long id) {
        this.livroRepository.remover(id);
    }
}
