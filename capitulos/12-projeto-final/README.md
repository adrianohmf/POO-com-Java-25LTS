[Índice](../../README.md) | [← Anterior: 11. Tratamento de Exceções](../11-tratamento-de-excecoes/README.md) | [Próximo: 13. Boas Práticas e Convenções →](../13-boas-praticas-e-convencoes/README.md)

# 12. Projeto Final — Aplicação Completa

> **Códigos deste capítulo:** [codigos/12-projeto-final](../../codigos/12-projeto-final/)

> **O que você vai aprender neste capítulo:**
> Integrar todos os conceitos vistos no guia em um único sistema funcional: herança, encapsulamento, polimorfismo, interfaces, exceções personalizadas, coleções e controle de fluxo com menu interativo.

Chegamos ao ponto de chegada do guia. Tudo o que você aprendeu nos capítulos anteriores — classes, objetos, encapsulamento, herança, polimorfismo, interfaces, coleções e exceções — agora se junta em um sistema real e funcional.

O projeto e um **Sistema Acadêmico** simplificado, inspirado em sistemas que instituicoes como o IFPE usam no dia a dia. Ele permite matricular alunos, lançar notas, calcular medias, determinar situacoes e gerar relatorios — tudo via menu interativo no terminal.

O objetivo não é só que o sistema funcione, mas que ele demonstre cada conceito de POO de forma clara e intencional. Ao ler o código, deve ser possível identificar onde está o encapsulamento, onde está o polimorfismo, onde estão as interfaces. Esse e o sinal de um código orientado a objetos bem escrito.

Implemente em partes, testando cada etapa antes de avacar. Comece pelas exceções, depois as interfaces, depois as classes, e por fim o programa principal.


> Integra **todos os conceitos** vistos no guia. Implemente em partes, nesta ordem.

```java
// Arquivo: SistemaAcademico.java

import java.util.ArrayList;
import java.util.Scanner;

// EXCEÇÕES
class AlunoNaoEncontradoException extends Exception {
    AlunoNaoEncontradoException(String mat) {
        super("Aluno nao encontrado: matricula " + mat);
    }
}

class NotaInvalidaException extends IllegalArgumentException {
    NotaInvalidaException(double nota) {
        super("Nota invalida: " + nota + " (deve ser entre 0 e 10)");
    }
}

// INTERFACE
interface Relatorio {
    void gerarRelatorio();
}

// CLASSE ABSTRATA
abstract class Pessoa {
    protected String nome;
    protected String cpf;

    Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf  = cpf;
    }

    abstract void apresentar();
    String getNome() { return nome; }
}

// ALUNO
class Aluno extends Pessoa implements Relatorio {
    private String matricula;
    private String curso;
    private ArrayList<Double> notas = new ArrayList<>();

    Aluno(String nome, String cpf, String matricula, String curso) {
        super(nome, cpf);
        this.matricula = matricula;
        this.curso     = curso;
    }

    void adicionarNota(double nota) {
        if (nota < 0 || nota > 10) throw new NotaInvalidaException(nota);
        notas.add(nota);
        IO.println("Nota " + nota + " adicionada para " + nome);
    }

    double getMedia() {
        if (notas.isEmpty()) return 0;
        double soma = 0;
        for (double n : notas) soma += n;
        return soma / notas.size();
    }

    String getSituacao() {
        double m = getMedia();
        return m >= 7.0 ? "Aprovado" : m >= 5.0 ? "Recuperacao" : "Reprovado";
    }

    String getMatricula() { return matricula; }

    @Override
    public void apresentar() {
        IO.println("  " + nome + " | " + matricula);
    }

    @Override
    public void gerarRelatorio() {
        IO.println("+--------------------------------------+");
        IO.println("| BOLETIM                              |");
        IO.println("+--------------------------------------+");
        IO.println("| Nome:      " + nome);
        IO.println("| Matricula: " + matricula);
        IO.println("| Curso:     " + curso);
        IO.println("| Notas:     " + notas);
        IO.println("| Media:     " + String.format("%.1f", getMedia()));
        IO.println("| Situacao:  " + getSituacao());
        IO.println("+--------------------------------------+");
    }
}

// TURMA
class Turma implements Relatorio {
    private String codigo;
    private String disciplina;
    private ArrayList<Aluno> alunos = new ArrayList<>();

    Turma(String codigo, String disciplina) {
        this.codigo     = codigo;
        this.disciplina = disciplina;
    }

    void matricular(Aluno a) {
        alunos.add(a);
        IO.println(a.getNome() + " matriculado(a) em " + disciplina);
    }

    Aluno buscar(String matricula) throws AlunoNaoEncontradoException {
        for (Aluno a : alunos) {
            if (a.getMatricula().equals(matricula)) return a;
        }
        throw new AlunoNaoEncontradoException(matricula);
    }

    @Override
    public void gerarRelatorio() {
        IO.println("\n========================================");
        IO.println("  " + codigo + " — " + disciplina);
        IO.println("  Total: " + alunos.size() + " aluno(s)");
        IO.println("========================================");
        for (Aluno a : alunos) a.gerarRelatorio();
    }
}

// PROGRAMA PRINCIPAL
void main() {
    var sc    = new Scanner(System.in);
    var turma = new Turma("2025.1", "Programacao Orientada a Objetos");

    var a1 = new Aluno("Maria Silva",  "111.111.111-11", "2025-001", "Informatica");
    var a2 = new Aluno("Joao Santos",  "222.222.222-22", "2025-002", "Sistemas");
    var a3 = new Aluno("Ana Lima",     "333.333.333-33", "2025-003", "Informatica");

    turma.matricular(a1);
    turma.matricular(a2);
    turma.matricular(a3);

    boolean rodando = true;

    while (rodando) {
        IO.println("\n==========================================");
        IO.println("    SISTEMA ACADEMICO — IFPE");
        IO.println("==========================================");
        IO.println("  1. Lancar nota");
        IO.println("  2. Ver boletim de aluno");
        IO.println("  3. Relatorio da turma");
        IO.println("  0. Sair");
        IO.print("Opcao: ");

        try {
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    IO.print("Matricula: ");
                    String mat = sc.nextLine();
                    IO.print("Nota (0-10): ");
                    double nota = sc.nextDouble();
                    sc.nextLine();
                    turma.buscar(mat).adicionarNota(nota);
                }
                case 2 -> {
                    IO.print("Matricula: ");
                    turma.buscar(sc.nextLine()).gerarRelatorio();
                }
                case 3  -> turma.gerarRelatorio();
                case 0  -> { IO.println("Ate logo!"); rodando = false; }
                default -> IO.println("Opcao invalida.");
            }

        } catch (AlunoNaoEncontradoException e) {
            IO.println("Erro: " + e.getMessage());
        } catch (NotaInvalidaException e) {
            IO.println("Aviso: " + e.getMessage());
        } catch (Exception e) {
            IO.println("Erro: " + e.getMessage());
            sc.nextLine();
        }
    }

    sc.close();
}
```

---

[Índice](../../README.md) | [← Anterior: 11. Tratamento de Exceções](../11-tratamento-de-excecoes/README.md) | [Próximo: 13. Boas Práticas e Convenções →](../13-boas-praticas-e-convencoes/README.md)
