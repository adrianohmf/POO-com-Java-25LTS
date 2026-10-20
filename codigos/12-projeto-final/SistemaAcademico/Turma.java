// Arquivo: Turma.java

import java.util.ArrayList;

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
