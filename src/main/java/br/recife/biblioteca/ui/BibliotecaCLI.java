package br.recife.biblioteca.ui;

import br.recife.biblioteca.model.dto.LivroDTO;
import br.recife.biblioteca.model.dto.MidiaDigitalDTO;
import br.recife.biblioteca.model.dto.RevistaDTO;
import br.recife.biblioteca.model.dto.UsuarioDTO;
import br.recife.biblioteca.model.lib.Livro;
import br.recife.biblioteca.model.lib.MidiaDigital;
import br.recife.biblioteca.model.lib.Revista;
import br.recife.biblioteca.model.lib.Emprestimo;
import br.recife.biblioteca.model.user.Aluno;
import br.recife.biblioteca.model.user.Servidor;
import br.recife.biblioteca.model.user.Visitante;
import br.recife.biblioteca.service.*;
import br.recife.biblioteca.exception.RecursoIndisponivelException;
import br.recife.biblioteca.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaCLI {

    private RevistaService revistaService = new RevistaService();
    private MidiaDigitalService midiaDigitalService = new MidiaDigitalService();
    private LivroService livroService = new LivroService();
    private AlunoService alunoService = new AlunoService();
    private ServidorService servidorService = new ServidorService();
    private VisitanteService visitanteService = new VisitanteService();
    private EmprestimoService emprestimoService = new EmprestimoService();

    private final Scanner sc = new Scanner(System.in);

    public void init() {
        while (true) {
            System.out.println("\n=== RECIFE BIBLIOTECA ===");
            System.out.println("1 - Recursos");
            System.out.println("2 - Usuários");
            System.out.println("3 - Empréstimos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> this.recursos();
                case 2 -> this.usuarios();
                case 3 -> this.emprestimos();
                case 0 -> {
                    System.out.println("Saindo...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void usuarios() {
        while (true) {
            System.out.println("\n=== RECIFE BIBLIOTECA USUÁRIOS ===");
            System.out.println("1 - Aluno");
            System.out.println("2 - Visitante");
            System.out.println("3 - Servidor");
            System.out.println("9 - Voltar");
            System.out.print("Escolha: ");

            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> this.aluno();
                case 2 -> this.visitante();
                case 3 -> this.servidor();
                case 9 -> {
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void servidor() {
        System.out.println("\n=== RECIFE BIBLIOTECA USUÁRIOS SERVIDOR ===");
        System.out.println("1 - CADASTRAR SERVIDOR");
        System.out.println("2 - BUSCAR SERVIDOR");
        System.out.println("3 - BUSCAR TODOS OS SERVIDORES");
        System.out.println("4 - DELETAR SERVIDOR");
        System.out.println("9 - Voltar");
        System.out.print("Escolha: ");

        int op = Integer.parseInt(sc.nextLine());

        switch (op) {
            case 1 -> this.cadastrarServidor();
            case 2 -> this.buscarServidor();
            case 3 -> this.buscarServidores();
            case 4 -> this.deletarServidor();
            case 9 -> {
                return;
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    private void cadastrarServidor() {
        System.out.println("\n--- Cadastro de Servidor ---");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Documento: ");
        String documento = sc.nextLine();

        UsuarioDTO dto = new UsuarioDTO(nome, documento);
        Servidor servidor = this.servidorService.salvar(dto);
        System.out.println("Servidor cadastrado com ID: " + servidor.getId());
    }

    private void buscarServidor() {
        System.out.println("\n--- Pesquisa de Servidor ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        Servidor servidor = this.servidorService.buscarPorId(id);

        String[] headers = {"ID","Nome","Documento"};
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{String.valueOf(servidor.getId()), servidor.getNome(), servidor.getDocumento()});
        printTable(headers, rows);
    }

    private void buscarServidores() {
        List<Servidor> servidores = this.servidorService.buscarTodos();
        if (servidores.isEmpty()) {
            System.out.println("Nenhum servidor cadastrado.");
            return;
        }
        String[] headers = {"ID","Nome","Documento"};
        List<String[]> rows = new ArrayList<>();
        for (Servidor s : servidores) {
            rows.add(new String[]{String.valueOf(s.getId()), s.getNome(), s.getDocumento()});
        }
        printTable(headers, rows);
    }

    private void deletarServidor() {
        System.out.println("\n--- Exclusão de Servidor ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        this.servidorService.remover(id);
        System.out.println("Servidor removido com sucesso!");
    }

    private void visitante() {
        System.out.println("\n=== RECIFE BIBLIOTECA USUÁRIOS VISITANTE ===");
        System.out.println("1 - CADASTRAR VISITANTE");
        System.out.println("2 - BUSCAR VISITANTE");
        System.out.println("3 - BUSCAR TODOS OS VISITANTES");
        System.out.println("4 - DELETAR VISITANTE");
        System.out.println("9 - Voltar");
        System.out.print("Escolha: ");

        int op = Integer.parseInt(sc.nextLine());

        switch (op) {
            case 1 -> this.cadastrarVisitante();
            case 2 -> this.buscarVisitante();
            case 3 -> this.buscarVisitantes();
            case 4 -> this.deletarVisitante();
            case 9 -> {
                return;
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    private void cadastrarVisitante() {
        System.out.println("\n--- Cadastro de Visitante ---");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Documento: ");
        String documento = sc.nextLine();

        UsuarioDTO dto = new UsuarioDTO(nome, documento);
        Visitante visitante = this.visitanteService.salvar(dto);
        System.out.println("Visitante cadastrado com ID: " + visitante.getId());
    }

    private void buscarVisitante() {
        System.out.println("\n--- Pesquisa de Visitante ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        Visitante visitante = this.visitanteService.buscarPorId(id);

        String[] headers = {"ID","Nome","Documento"};
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{String.valueOf(visitante.getId()), visitante.getNome(), visitante.getDocumento()});
        printTable(headers, rows);
    }

    private void buscarVisitantes() {
        List<Visitante> visitantes = this.visitanteService.buscarTodos();
        if (visitantes.isEmpty()) {
            System.out.println("Nenhum visitante cadastrado.");
            return;
        }
        String[] headers = {"ID","Nome","Documento"};
        List<String[]> rows = new ArrayList<>();
        for (Visitante v : visitantes) {
            rows.add(new String[]{String.valueOf(v.getId()), v.getNome(), v.getDocumento()});
        }
        printTable(headers, rows);
    }

    private void deletarVisitante() {
        System.out.println("\n--- Exclusão de Visitante ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        this.visitanteService.remover(id);
        System.out.println("Visitante removido com sucesso!");
    }

    private void aluno() {
        System.out.println("\n=== RECIFE BIBLIOTECA USUÁRIOS ALUNO ===");
        System.out.println("1 - CADASTRAR ALUNO");
        System.out.println("2 - BUSCAR ALUNO");
        System.out.println("3 - BUSCAR TODOS OS ALUNOS");
        System.out.println("4 - DELETAR ALUNO");
        System.out.println("9 - Voltar");
        System.out.print("Escolha: ");

        int op = Integer.parseInt(sc.nextLine());

        switch (op) {
            case 1 -> this.cadastrarAluno();
            case 2 -> this.buscarAluno();
            case 3 -> this.buscarAlunos();
            case 4 -> this.deletarAluno();
            case 9 -> {
                return;
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    private void deletarAluno() {
        System.out.println("\n--- Exclusão de Alunos ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        this.alunoService.remover(id);
        System.out.println("Aluno removido com sucesso!");
    }

    private void buscarAlunos() {
        List<Aluno> alunos = this.alunoService.buscarTodos();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        String[] headers = {"ID","Nome","Documento"};
        List<String[]> rows = new ArrayList<>();
        for (Aluno a : alunos) {
            rows.add(new String[]{String.valueOf(a.getId()), a.getNome(), a.getDocumento()});
        }
        printTable(headers, rows);
    }

    private void buscarAluno() {
        System.out.println("\n--- Pesquisa de Alunos ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        Aluno al = this.alunoService.buscarPorId(id);

        String[] headers = {"ID","Nome","Documento"};
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{String.valueOf(al.getId()), al.getNome(), al.getDocumento()});
        printTable(headers, rows);
    }

    private void cadastrarAluno() {
        System.out.println("\n--- Cadastro de Alunos ---");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Documento: ");
        String documento = sc.nextLine();

        UsuarioDTO dto = new UsuarioDTO(nome, documento);
        Aluno aluno = this.alunoService.salvar(dto);
        System.out.println("Aluno cadastrado com ID: " + aluno.getId());
    }

    private void recursos() {
        while (true) {
            System.out.println("\n=== RECIFE BIBLIOTECA RECURSOS ===");
            System.out.println("1 - Revista");
            System.out.println("2 - Livro");
            System.out.println("3 - Midia Digital");
            System.out.println("9 - Voltar");
            System.out.print("Escolha: ");

            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> this.revista();
                case 2 -> this.livro();
                case 3 -> this.midiaDigital();
                case 9 -> {
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void livro() {
        System.out.println("\n=== RECIFE BIBLIOTECA RECURSOS LIVRO ===");
        System.out.println("1 - CADASTRAR LIVRO");
        System.out.println("2 - BUSCAR LIVRO");
        System.out.println("3 - BUSCAR TODOS OS LIVROS");
        System.out.println("4 - DELETAR LIVRO");
        System.out.println("5 - BUSCAR POR TÍTULO");
        System.out.println("9 - Voltar");
        System.out.print("Escolha: ");

        int op = Integer.parseInt(sc.nextLine());

        switch (op) {
            case 1 -> this.cadastrarLivro();
            case 2 -> this.buscarLivro();
            case 3 -> this.buscarLivros();
            case 4 -> this.deletarLivro();
            case 5 -> this.buscarLivrosPorTitulo();
            case 9 -> {
                return;
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    private void deletarLivro() {
        System.out.println("\n--- Exclusão de Livros ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        this.livroService.remover(id);
        System.out.println("Livro removido com sucesso!");
    }

    private void buscarLivros() {
        List<Livro> livros = this.livroService.buscarTodos();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        String[] headers = {"ID","Título","Ano","Disponível"};
        List<String[]> rows = new ArrayList<>();
        for (Livro livro : livros) {
            rows.add(new String[]{String.valueOf(livro.getId()), livro.getTitulo(), livro.getAnoPublicacao(), (Boolean.TRUE.equals(livro.getDisponivel()) ? "SIM" : "NÃO")});
        }
        printTable(headers, rows);
    }

    private void buscarLivrosPorTitulo() {
        System.out.print("Título (ou parte dele): ");
        String titulo = sc.nextLine();
        List<Livro> livros = this.livroService.buscarPorTitulo(titulo);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o filtro informado.");
            return;
        }
        String[] headers = {"ID","Título","Ano","Disponível"};
        List<String[]> rows = new ArrayList<>();
        for (Livro livro : livros) {
            rows.add(new String[]{String.valueOf(livro.getId()), livro.getTitulo(), livro.getAnoPublicacao(), (Boolean.TRUE.equals(livro.getDisponivel()) ? "SIM" : "NÃO")});
        }
        printTable(headers, rows);
    }

    private void buscarLivro() {
        System.out.println("\n--- Pesquisa de Livros ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        Livro livro = this.livroService.buscarPorId(id);

        String[] headers = {"ID","Título","Ano","Disponível"};
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{String.valueOf(livro.getId()), livro.getTitulo(), livro.getAnoPublicacao(), (Boolean.TRUE.equals(livro.getDisponivel()) ? "SIM" : "NÃO")});
        printTable(headers, rows);
        System.out.println("Capítulos:");
        livro.getCapitulos().forEach(cap -> System.out.println(" - " + cap.getTitulo()));
    }

    private void cadastrarLivro() {
        System.out.println("\n--- Cadastro de LIVRO ---");

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Ano de Publicação: ");
        String ano = sc.nextLine();

        System.out.print("Quantidade de capitulos: ");
        Integer capitulos = Integer.parseInt(sc.nextLine());

        System.out.print("Número de páginas por capitulo: ");
        Integer paginas = Integer.parseInt(sc.nextLine());

        LivroDTO dto = new LivroDTO(titulo, ano, capitulos, paginas);
        Livro livro = this.livroService.salvar(dto);
        System.out.println("Livro cadastrado com ID: " + livro.getId());
    }

    private void midiaDigital() {
        System.out.println("\n=== RECIFE BIBLIOTECA RECURSOS MIDIA DIGITAL ===");
        System.out.println("1 - CADASTRAR MIDIA DIGITAL");
        System.out.println("2 - BUSCAR MIDIA DIGITAL");
        System.out.println("3 - BUSCAR TODAS AS MIDIAS DIGITAL");
        System.out.println("4 - DELETAR MIDIA DIGITAL");
        System.out.println("5 - BUSCAR POR TÍTULO");
        System.out.println("9 - Voltar");
        System.out.print("Escolha: ");

        int op = Integer.parseInt(sc.nextLine());

        switch (op) {
            case 1 -> this.cadastrarMidiaDigital();
            case 2 -> this.buscarMidiaDigital();
            case 3 -> this.buscarMidiasDigitais();
            case 4 -> this.deletarMidiaDigital();
            case 5 -> this.buscarMidiasPorTitulo();
            case 9 -> {
                return;
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    private void deletarMidiaDigital() {
        System.out.println("\n--- Exclusão de Midias Digitais ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        this.midiaDigitalService.remover(id);
        System.out.println("Midia Digital removida com sucesso!");
    }

    private void buscarMidiasDigitais() {
        List<MidiaDigital> revistas = this.midiaDigitalService.buscarTodos();
        if (revistas.isEmpty()) {
            System.out.println("Nenhuma mídia cadastrada.");
            return;
        }
        String[] headers = {"ID","Título","Ano","Tamanho(MB)","Tipo","Disponível"};
        List<String[]> rows = new ArrayList<>();
        for (MidiaDigital m : revistas) {
            rows.add(new String[]{String.valueOf(m.getId()), m.getTitulo(), m.getAnoPublicacao(), String.valueOf(m.getTamanhoMB()), m.getTipoArquivo(), (Boolean.TRUE.equals(m.getDisponivel()) ? "SIM" : "NÃO")});
        }
        printTable(headers, rows);
    }

    private void buscarMidiasPorTitulo() {
        System.out.print("Título (ou parte dele): ");
        String titulo = sc.nextLine();
        List<MidiaDigital> midias = this.midiaDigitalService.buscarPorTitulo(titulo);
        if (midias.isEmpty()) {
            System.out.println("Nenhuma mídia encontrada para o filtro informado.");
            return;
        }
        String[] headers = {"ID","Título","Ano","Tamanho(MB)","Tipo","Disponível"};
        List<String[]> rows = new ArrayList<>();
        for (MidiaDigital m : midias) {
            rows.add(new String[]{String.valueOf(m.getId()), m.getTitulo(), m.getAnoPublicacao(), String.valueOf(m.getTamanhoMB()), m.getTipoArquivo(), (Boolean.TRUE.equals(m.getDisponivel()) ? "SIM" : "NÃO")});
        }
        printTable(headers, rows);
    }

    private void buscarMidiaDigital() {
        System.out.println("\n--- Pesquisa de Midia Digital ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        MidiaDigital revista = this.midiaDigitalService.buscarPorId(id);

        String[] headers = {"ID","Título","Ano","Tamanho(MB)","Tipo","Disponível"};
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{String.valueOf(revista.getId()), revista.getTitulo(), revista.getAnoPublicacao(), String.valueOf(revista.getTamanhoMB()), revista.getTipoArquivo(), (Boolean.TRUE.equals(revista.getDisponivel()) ? "SIM" : "NÃO")});
        printTable(headers, rows);
    }

    private void cadastrarMidiaDigital() {
        System.out.println("\n--- Cadastro de Midia Digital ---");

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Ano de Publicação: ");
        String ano = sc.nextLine();

        System.out.print("Tamanho(MB): ");
        Integer tamanhoMB = Integer.parseInt(sc.nextLine());

        System.out.print("Tipo do arquivo: ");
        String tipoArquivo = sc.nextLine();

        MidiaDigitalDTO dto = new MidiaDigitalDTO(tamanhoMB, tipoArquivo, titulo, ano );
        MidiaDigital midia = this.midiaDigitalService.salvar(dto);
        System.out.println("Midia Digital cadastrada com ID: " + midia.getId());
    }

    private void deletarRevista() {
        System.out.println("\n--- Exclusão de Revistas ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        this.revistaService.remover(id);
        System.out.println("Revista removida com sucesso!");
    }

    private void buscarRevistas() {
        List<Revista> revistas = this.revistaService.buscarTodos();
        if (revistas.isEmpty()) {
            System.out.println("Nenhuma revista cadastrada.");
            return;
        }
        String[] headers = {"ID","Título","Edição","Ano","Disponível"};
        List<String[]> rows = new ArrayList<>();
        for (Revista r : revistas) {
            rows.add(new String[]{String.valueOf(r.getId()), r.getTitulo(), r.getEdicao(), r.getAnoPublicacao(), (Boolean.TRUE.equals(r.getDisponivel()) ? "SIM" : "NÃO")});
        }
        printTable(headers, rows);
    }

    private void buscarRevistasPorTitulo() {
        System.out.print("Título (ou parte dele): ");
        String titulo = sc.nextLine();
        List<Revista> revistas = this.revistaService.buscarPorTitulo(titulo);
        if (revistas.isEmpty()) {
            System.out.println("Nenhuma revista encontrada para o filtro informado.");
            return;
        }
        String[] headers = {"ID","Título","Edição","Ano","Disponível"};
        List<String[]> rows = new ArrayList<>();
        for (Revista r : revistas) {
            rows.add(new String[]{String.valueOf(r.getId()), r.getTitulo(), r.getEdicao(), r.getAnoPublicacao(), (Boolean.TRUE.equals(r.getDisponivel()) ? "SIM" : "NÃO")});
        }
        printTable(headers, rows);
    }

    private void buscarRevista() {
        System.out.println("\n--- Pesquisa de Revistas ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        Revista revista = this.revistaService.buscarPorId(id);

        String[] headers = {"ID","Título","Edição","Ano","Disponível"};
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{String.valueOf(revista.getId()), revista.getTitulo(), revista.getEdicao(), revista.getAnoPublicacao(), (Boolean.TRUE.equals(revista.getDisponivel()) ? "SIM" : "NÃO")});
        printTable(headers, rows);
    }

    private void cadastrarRevista() {
        System.out.println("\n--- Cadastro de Revista ---");

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Ano de Publicação: ");
        String ano = sc.nextLine();

        System.out.print("Edição: ");
        String edicao = sc.nextLine();

        System.out.print("Periodicidade: ");
        int periodicidade = Integer.parseInt(sc.nextLine());

        RevistaDTO dto = new RevistaDTO(edicao, periodicidade, ano, titulo);
        Revista revista = this.revistaService.salvar(dto);
        System.out.println("Revista cadastrada com ID: " + revista.getId());
    }

    private void emprestimos() {
        System.out.println("\n=== RECIFE BIBLIOTECA EMPRÉSTIMOS ===");
        System.out.println("1 - Emprestar Livro para Aluno");
        System.out.println("2 - Emprestar MidiaDigital para Visitante");
        System.out.println("3 - Devolver (informe ID do empréstimo)");
        System.out.println("4 - Relatório de itens atualmente emprestados");
        System.out.println("5 - Relatório de itens em atraso");
        System.out.println("9 - Voltar");
        System.out.print("Escolha: ");

        int op = Integer.parseInt(sc.nextLine());

        switch (op) {
            case 1 -> this.emprestarLivroParaAluno();
            case 2 -> this.emprestarMidiaParaVisitante();
            case 3 -> this.devolverEmprestimo();
            case 4 -> this.relatorioEmprestimosAtuais();
            case 5 -> this.relatorioAtrasados();
            case 9 -> {
                return;
            }
            default -> System.out.println("Opção inválida!");
        }
    }

    private void emprestarLivroParaAluno() {
        try {
            System.out.println("\n--- Empréstimo de Livro para Aluno ---");
            System.out.print("ID do Livro: ");
            Long livroId = Long.parseLong(sc.nextLine());
            System.out.print("ID do Aluno: ");
            Long alunoId = Long.parseLong(sc.nextLine());

            Emprestimo emp = this.emprestimoService.emprestarLivroParaAluno(livroId, alunoId);
            System.out.println("Empréstimo realizado com sucesso. ID do empréstimo: " + emp.getId());
        } catch (RecursoIndisponivelException e) {
            System.out.println("Não foi possível emprestar: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }

    private void emprestarMidiaParaVisitante() {
        try {
            System.out.println("\n--- Empréstimo de MidiaDigital para Visitante ---");
            System.out.print("ID da MidiaDigital: ");
            Long midiaId = Long.parseLong(sc.nextLine());
            System.out.print("ID do Visitante: ");
            Long visitanteId = Long.parseLong(sc.nextLine());

            Emprestimo emp = this.emprestimoService.emprestarMidiaParaVisitante(midiaId, visitanteId);
            System.out.println("Empréstimo realizado com sucesso. ID do empréstimo: " + emp.getId());
        } catch (RecursoIndisponivelException e) {
            System.out.println("Não foi possível emprestar: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }

    private void devolverEmprestimo() {
        try {
            System.out.println("\n--- Devolução de Empréstimo ---");
            System.out.print("ID do Empréstimo: ");
            Long empId = Long.parseLong(sc.nextLine());

            double multa = this.emprestimoService.devolver(empId);
            System.out.println("Devolução processada. Multa: R$ " + String.format("%.2f", multa));
        } catch (EntityNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }

    private void relatorioEmprestimosAtuais() {
        List<Emprestimo> lista = this.emprestimoService.relatorioEmprestimosAtuais();
        if (lista.isEmpty()) {
            System.out.println("Nenhum empréstimo ativo no momento.");
            return;
        }
        String[] headers = {"ID", "Recurso", "Recurso ID", "Usuário", "Usuário ID", "Data Empréstimo", "Data Prevista"};
        List<String[]> rows = new ArrayList<>();
        for (Emprestimo e : lista) {
            rows.add(new String[]{String.valueOf(e.getId()), e.getRecurso().getDescricao(), String.valueOf(e.getRecurso().getId()), e.getUsuario().getNome(), String.valueOf(e.getUsuario().getId()), String.valueOf(e.getDataEmprestimo()), String.valueOf(e.getDataPrevista())});
        }
        printTable(headers, rows);
    }

    private void relatorioAtrasados() {
        List<Emprestimo> lista = this.emprestimoService.relatorioAtrasados();
        if (lista.isEmpty()) {
            System.out.println("Nenhum empréstimo em atraso no momento.");
            return;
        }
        String[] headers = {"ID", "Recurso", "Recurso ID", "Usuário", "Usuário ID", "Data Empréstimo", "Data Prevista"};
        List<String[]> rows = new ArrayList<>();
        for (Emprestimo e : lista) {
            rows.add(new String[]{String.valueOf(e.getId()), e.getRecurso().getDescricao(), String.valueOf(e.getRecurso().getId()), e.getUsuario().getNome(), String.valueOf(e.getUsuario().getId()), String.valueOf(e.getDataEmprestimo()), String.valueOf(e.getDataPrevista())});
        }
        printTable(headers, rows);
    }

    private void revista() {
        while (true) {
            System.out.println("\n=== RECIFE BIBLIOTECA RECURSOS REVISTA ===");
            System.out.println("1 - CADASTRAR REVISTA");
            System.out.println("2 - BUSCAR REVISTA");
            System.out.println("3 - BUSCAR TODAS AS REVISTAS");
            System.out.println("4 - DELETAR REVISTA");
            System.out.println("5 - BUSCAR POR TÍTULO");
            System.out.println("9 - Voltar");
            System.out.print("Escolha: ");

            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> this.cadastrarRevista();
                case 2 -> this.buscarRevista();
                case 3 -> this.buscarRevistas();
                case 4 -> this.deletarRevista();
                case 5 -> this.buscarRevistasPorTitulo();
                case 9 -> {
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void printTable(String[] headers, List<String[]> rows) {
        int cols = headers.length;
        int[] widths = new int[cols];
        for (int i = 0; i < cols; i++) {
            widths[i] = headers[i].length();
        }
        for (String[] r : rows) {
            for (int i = 0; i < cols; i++) {
                if (r.length > i && r[i] != null) {
                    widths[i] = Math.max(widths[i], r[i].length());
                }
            }
        }
        StringBuilder sep = new StringBuilder();
        for (int w : widths) {
            sep.append("+");
            for (int i = 0; i < w + 2; i++) sep.append("-");
        }
        sep.append("+");
        System.out.println(sep);
        StringBuilder headerLine = new StringBuilder();
        for (int i = 0; i < cols; i++) {
            headerLine.append("| ");
            headerLine.append(padRight(headers[i], widths[i]));
            headerLine.append(" ");
        }
        headerLine.append("|");
        System.out.println(headerLine);
        System.out.println(sep);
        for (String[] r : rows) {
            StringBuilder rowLine = new StringBuilder();
            for (int i = 0; i < cols; i++) {
                rowLine.append("| ");
                String cell = (r.length > i && r[i] != null) ? r[i] : "";
                rowLine.append(padRight(cell, widths[i]));
                rowLine.append(" ");
            }
            rowLine.append("|");
            System.out.println(rowLine);
        }
        System.out.println(sep);
    }

    private String padRight(String s, int n) {
        if (s == null) s = "";
        if (s.length() >= n) return s;
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < n) sb.append(' ');
        return sb.toString();
    }
}