package br.recife.biblioteca.ui;

import br.recife.biblioteca.model.dto.LivroDTO;
import br.recife.biblioteca.model.dto.MidiaDigitalDTO;
import br.recife.biblioteca.model.dto.RevistaDTO;
import br.recife.biblioteca.model.dto.UsuarioDTO;
import br.recife.biblioteca.model.lib.Livro;
import br.recife.biblioteca.model.lib.MidiaDigital;
import br.recife.biblioteca.model.lib.Revista;
import br.recife.biblioteca.model.user.Aluno;
import br.recife.biblioteca.model.user.Servidor;
import br.recife.biblioteca.model.user.Visitante;
import br.recife.biblioteca.service.*;

import java.util.List;
import java.util.Scanner;
public class BibliotecaCLI {

    private RevistaService revistaService = new RevistaService();
    private MidiaDigitalService midiaDigitalService = new MidiaDigitalService();
    private LivroService livroService = new LivroService();
    private AlunoService alunoService = new AlunoService();
    private ServidorService servidorService = new ServidorService();
    private VisitanteService visitanteService = new VisitanteService();

    private final Scanner sc = new Scanner(System.in);

    public void init() {
        while (true) {
            System.out.println("\n=== RECIFE BIBLIOTECA ===");
            System.out.println("1 - Recursos");
            System.out.println("2 - Usuários");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> this.recursos();
                case 2 -> this.usuarios();
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
                    this.init();
                    sc.close();
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
                this.recursos();
                sc.close();
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
        this.servidorService.salvar(dto);
    }

    private void buscarServidor() {
        System.out.println("\n--- Pesquisa de Servidor ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        Servidor servidor = this.servidorService.buscarPorId(id);

        System.out.println("=== SERVIDOR ===");
        System.out.println("ID: " + servidor.getId());
        System.out.println("Nome: " + servidor.getNome());
        System.out.println("Documento: " + servidor.getDocumento());
    }

    private void buscarServidores() {
        List<Servidor> servidores = this.servidorService.buscarTodos();

        servidores.forEach(s -> {
            System.out.println("======");
            System.out.println("ID: " + s.getId());
            System.out.println("Nome: " + s.getNome());
            System.out.println("Documento: " + s.getDocumento());
        });
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
                this.recursos();
                sc.close();
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
        this.visitanteService.salvar(dto);
    }

    private void buscarVisitante() {
        System.out.println("\n--- Pesquisa de Visitante ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        Visitante visitante = this.visitanteService.buscarPorId(id);

        System.out.println("=== VISITANTE ===");
        System.out.println("ID: " + visitante.getId());
        System.out.println("Nome: " + visitante.getNome());
        System.out.println("Documento: " + visitante.getDocumento());
    }

    private void buscarVisitantes() {
        List<Visitante> visitantes = this.visitanteService.buscarTodos();

        visitantes.forEach(v -> {
            System.out.println("======");
            System.out.println("ID: " + v.getId());
            System.out.println("Nome: " + v.getNome());
            System.out.println("Documento: " + v.getDocumento());
        });
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
                this.recursos();
                sc.close();
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
        alunos.forEach(aluno -> {
            System.out.println("======");
            System.out.println("ID: " + aluno.getId());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Documento: " + aluno.getDocumento());
        });
    }

    private void buscarAluno() {
        System.out.println("\n--- Pesquisa de Alunos ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        Aluno al = this.alunoService.buscarPorId(id);

        System.out.println("=== REVISTA ===");
        System.out.println("ID: " + al.getId());
        System.out.println("Nome: " + al.getNome());
        System.out.println("Documento: " + al.getDocumento());
    }

    private void cadastrarAluno() {
        System.out.println("\n--- Cadastro de Alunos ---");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Documento: ");
        String documento = sc.nextLine();

        UsuarioDTO dto = new UsuarioDTO(nome, documento);
        this.alunoService.salvar(dto);
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
                    this.init();
                    sc.close();
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
        System.out.println("9 - Voltar");
        System.out.print("Escolha: ");

        int op = Integer.parseInt(sc.nextLine());

        switch (op) {
            case 1 -> this.cadastrarLivro();
            case 2 -> this.buscarLivro();
            case 3 -> this.buscarLivros();
            case 4 -> this.deletarLivro();
            case 9 -> {
                this.recursos();
                sc.close();
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
        livros.forEach(livro -> {
            System.out.println("======");
            System.out.println("ID: " + livro.getId());
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Ano de publicação: " + livro.getAnoPublicacao());
        });
    }

    private void buscarLivro() {
        System.out.println("\n--- Pesquisa de Livros ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        Livro livro = this.livroService.buscarPorId(id);

        System.out.println("=== LIVRO ===");
        System.out.println("ID: " + livro.getId());
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Ano de publicação: " + livro.getAnoPublicacao());
        System.out.println("Capitulos: ");
        livro.getCapitulos()
                .forEach(cap -> System.out.println(cap.getTitulo()));
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
        this.livroService.salvar(dto);
    }

    private void midiaDigital() {
        while (true) {
            System.out.println("\n=== RECIFE BIBLIOTECA RECURSOS MIDIA DIGITAL ===");
            System.out.println("1 - CADASTRAR MIDIA DIGITAL");
            System.out.println("2 - BUSCAR MIDIA DIGITAL");
            System.out.println("3 - BUSCAR TODAS AS MIDIAS DIGITAL");
            System.out.println("4 - DELETAR MIDIA DIGITAL");
            System.out.println("9 - Voltar");
            System.out.print("Escolha: ");

            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> this.cadastrarMidiaDigital();
                case 2 -> this.buscarMidiaDigital();
                case 3 -> this.buscarMidiasDigitais();
                case 4 -> this.deletarMidiaDigital();
                case 9 -> {
                    this.recursos();
                    sc.close();
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void revista() {
        while (true) {
            System.out.println("\n=== RECIFE BIBLIOTECA RECURSOS REVISTA ===");
            System.out.println("1 - CADASTRAR REVISTA");
            System.out.println("2 - BUSCAR REVISTA");
            System.out.println("3 - BUSCAR TODAS AS REVISTAS");
            System.out.println("4 - DELETAR REVISTA");
            System.out.println("9 - Voltar");
            System.out.print("Escolha: ");

            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> this.cadastrarRevista();
                case 2 -> this.buscarRevista();
                case 3 -> this.buscarRevistas();
                case 4 -> this.deletarRevista();
                case 9 -> {
                    this.recursos();
                    sc.close();
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
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
        revistas.forEach(revista -> {
            System.out.println("======");
            System.out.println("ID: " + revista.getId());
            System.out.println("Título: " + revista.getTitulo());
            System.out.println("Ano de publicação: " + revista.getAnoPublicacao());
            System.out.println("Tamanho (MB): " + revista.getTamanhoMB());
            System.out.println("Tipo do arquivo: " + revista.getTipoArquivo());
        });
    }

    private void buscarMidiaDigital() {
        System.out.println("\n--- Pesquisa de Midia Digital ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        MidiaDigital revista = this.midiaDigitalService.buscarPorId(id);

        System.out.println("=== REVISTA ===");
        System.out.println("ID: " + revista.getId());
        System.out.println("Título: " + revista.getTitulo());
        System.out.println("Ano de publicação: " + revista.getAnoPublicacao());
        System.out.println("Tamanho (MB): " + revista.getTamanhoMB());
        System.out.println("Tipo do arquivo: " + revista.getTipoArquivo());
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
        this.midiaDigitalService.salvar(dto);
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
        revistas.forEach(revista -> {
            System.out.println("======");
            System.out.println("ID: " + revista.getId());
            System.out.println("Título: " + revista.getTitulo());
            System.out.println("Edição: " + revista.getEdicao());
            System.out.println("Ano de publicação: " + revista.getAnoPublicacao());
        });
    }

    private void buscarRevista() {
        System.out.println("\n--- Pesquisa de Revistas ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(sc.nextLine());

        Revista revista = this.revistaService.buscarPorId(id);

        System.out.println("=== REVISTA ===");
        System.out.println("ID: " + revista.getId());
        System.out.println("Título: " + revista.getTitulo());
        System.out.println("Edição: " + revista.getEdicao());
        System.out.println("Ano de publicação: " + revista.getAnoPublicacao());
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
        this.revistaService.salvar(dto);
    }
}
