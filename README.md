Recife Biblioteca
=================

Visão geral
----------
Aplicação de exemplo em Java que modela uma pequena biblioteca com recursos (Livros, Revistas, Mídias Digitais), usuários (Aluno, Visitante, Servidor) e um módulo de empréstimos. A interface principal é uma CLI (linha de comando) disponível em `br.recife.biblioteca.ui.BibliotecaCLI` e a classe de entrada é `br.recife.biblioteca.Main`.

Entrega
-------
- Código organizado por packages (model, repository, service, ui, exception, util).

Pilares de Orientação a Objetos aplicados
----------------------------------------
1. Abstração
   - `br.recife.biblioteca.model.lib.Recurso` é uma abstração que define o comportamento comum de recursos (título, ano, disponibilidade, cálculo de multa).
   - Serviços (`LivroService`, `EmprestimoService`, etc.) abstraem regras de negócio e operações sobre entidades.

2. Encapsulamento
   - A maioria das entidades usa campos privados com getters/setters (por exemplo, `Recurso`, `Emprestimo`, `Usuario`). O acesso ao estado é controlado via métodos públicos.

3. Herança
   - `br.recife.biblioteca.model.user.Usuario` é a superclasse para `Aluno`, `Servidor` e `Visitante`. Cada subclasse implementa comportamentos específicos (p.ex. `prazoDiasPadrao()` e `fatorMulta()`).

4. Polimorfismo
   - O método `calcularMulta(long diasAtraso)` é abstrato em `Recurso` e implementado por `Livro`, `Revista` e `MidiaDigital` com regras diferentes. O serviço de empréstimo usa a interface `Recurso` sem depender da implementação concreta.

Decisões de projeto (agregação / composição)
--------------------------------------------
- Livro e Capítulos
  - `Livro` possui uma lista de `Capitulo` (`List<Capitulo> capitulos`) — decisão por composição: os capítulos pertencem semanticamente ao livro e não fazem sentido fora dele. Logo, quando um `Livro` é criado, seus `Capitulo`s são gerados e mantidos dentro do objeto.

- Empréstimo como Agregação
  - `Emprestimo` agrega referências a `Recurso` e `Usuario`. Esse relacionamento é de agregação (não é ownership exclusivo): o recurso e o usuário existem independentemente do empréstimo. Ao devolver, o empréstimo atualiza o estado do recurso, mas não destrói o recurso.

- Repositórios em memória (Map estático)
  - Para simplificar a execução local e permitir que diferentes instâncias de repositório compartilhem o mesmo estado em memória, os `*RepositoryImpl` usam `static final Map<...>` internamente. Alternativas como injeção de dependência/singletons ou persistência em banco de dados foram consideradas. A decisão atual prioriza simplicidade e previsibilidade durante execução e testes.

Como compilar e executar
-----------------------
Pré-requisitos:
- Java 21
- Maven

Compilar o projeto:

```bash
mvn -q clean compile
```

Executar a aplicação (CLI):

```bash
mvn -q -Dexec.mainClass="br.recife.biblioteca.Main" exec:java
```

Observação: se o plugin `exec` não estiver configurado no `pom.xml`, execute via IDE ou com `java -cp target/classes br.recife.biblioteca.Main` após compilar.

Como rodar os testes
-------------------
Os testes unitários usam JUnit 5 e Mockito.

Executar a suíte de testes:

```bash
mvn -q test
```

Artefatos relevantes
--------------------
- Código-fonte: `src/main/java`
- Testes: `src/test/java`
- Classe principal: `br.recife.biblioteca.Main`
- CLI: `br.recife.biblioteca.ui.BibliotecaCLI`

