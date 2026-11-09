// Arquivo: SistemaAcademico.java

import java.util.Scanner;

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
