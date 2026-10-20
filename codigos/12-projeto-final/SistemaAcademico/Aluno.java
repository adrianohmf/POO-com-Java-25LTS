// Arquivo: Aluno.java

import java.util.ArrayList;

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
