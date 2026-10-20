// Arquivo: Aluno.java

class Aluno {
    String nome; int idade; String curso;
    Aluno(String nome, int idade, String curso) {
        this.nome = nome; this.idade = idade; this.curso = curso;
    }
    void apresentar() {
        IO.println("  " + nome + " (" + idade + "a) — " + curso);
    }
}
