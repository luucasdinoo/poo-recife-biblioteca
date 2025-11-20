package br.recife.biblioteca.service;

import br.recife.biblioteca.exception.EntityNotFoundException;
import br.recife.biblioteca.exception.RecursoIndisponivelException;
import br.recife.biblioteca.model.lib.Emprestimo;
import br.recife.biblioteca.model.lib.Recurso;
import br.recife.biblioteca.model.user.Usuario;
import br.recife.biblioteca.repository.EmprestimoRepository;
import br.recife.biblioteca.repository.impl.EmprestimoRepositoryImpl;
import br.recife.biblioteca.util.IdUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository = new EmprestimoRepositoryImpl();
    private LivroService livroService = new LivroService();
    private MidiaDigitalService midiaService = new MidiaDigitalService();
    private AlunoService alunoService = new AlunoService();
    private VisitanteService visitanteService = new VisitanteService();

    public Emprestimo emprestarLivroParaAluno(Long livroId, Long alunoId) {
        Recurso recurso = livroService.buscarPorId(livroId);
        Usuario usuario = alunoService.buscarPorId(alunoId);

        if (!Boolean.TRUE.equals(recurso.getDisponivel())) {
            throw new RecursoIndisponivelException("Livro já está emprestado.");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime prevista = now.plusDays(usuario.prazoDiasPadrao());

        Emprestimo emprestimo = Emprestimo.novo(
                IdUtils.gerarId(),
                recurso,
                usuario,
                now,
                prevista,
                "EMPRESTADO"
        );
        recurso.setDisponivel(Boolean.FALSE);
        this.emprestimoRepository.salvar(emprestimo);
        return emprestimo;
    }

    public Emprestimo emprestarMidiaParaVisitante(Long midiaId, Long visitanteId) {
        Recurso recurso = midiaService.buscarPorId(midiaId);
        Usuario usuario = visitanteService.buscarPorId(visitanteId);

        if (!Boolean.TRUE.equals(recurso.getDisponivel())) {
            throw new RecursoIndisponivelException("Mídia digital já está emprestada.");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime prevista = now.plusDays(usuario.prazoDiasPadrao());

        Emprestimo emprestimo = Emprestimo.novo(
                IdUtils.gerarId(),
                recurso,
                usuario,
                now,
                prevista,
                "EMPRESTADO"
        );
        recurso.setDisponivel(Boolean.FALSE);
        this.emprestimoRepository.salvar(emprestimo);
        return emprestimo;
    }

    public double devolver(Long emprestimoId) {
        Emprestimo emp = this.emprestimoRepository.buscarPorId(emprestimoId)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado."));

        if (!"EMPRESTADO".equals(emp.getStatus())) {
            return 0.0;
        }

        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime prevista = emp.getDataPrevista();

        long diasAtraso = 0;
        if (agora.isAfter(prevista)) {
            diasAtraso = ChronoUnit.DAYS.between(prevista.toLocalDate(), agora.toLocalDate());
        }

        Recurso recurso = emp.getRecurso();
        Usuario usuario = emp.getUsuario();

        double multaBase = recurso.calcularMulta(diasAtraso);
        double multa = multaBase * usuario.fatorMulta();

        recurso.setDisponivel(Boolean.TRUE);
        emp.setStatus("DEVOLVIDO");
        this.emprestimoRepository.salvar(emp);

        return multa;
    }

    public List<Emprestimo> relatorioEmprestimosAtuais() {
        LocalDateTime agora = LocalDateTime.now();
        return this.emprestimoRepository.buscarTodos().stream()
                .filter(e -> "EMPRESTADO".equals(e.getStatus()))
                .collect(Collectors.toList());
    }

    public List<Emprestimo> relatorioAtrasados() {
        LocalDateTime agora = LocalDateTime.now();
        return this.emprestimoRepository.buscarTodos().stream()
                .filter(e -> "EMPRESTADO".equals(e.getStatus()) && e.getDataPrevista().isBefore(agora))
                .collect(Collectors.toList());
    }
}

