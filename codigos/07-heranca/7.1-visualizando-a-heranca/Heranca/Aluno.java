// Arquivo: Aluno.java

// SUBCLASSE: herda tudo de Pessoa
class Aluno extends Pessoa {
    private String matricula;
    private String curso;
    private double mediaGeral;

    Aluno(String nome, int idade, String cpf, String matricula, String curso) {
        super(nome, idade, cpf); // chama o construtor da superclasse — SEMPRE PRIMEIRO
        this.matricula  = matricula;
        this.curso      = curso;
        this.mediaGeral = 0.0;
    }

    void definirMedia(double media) { this.mediaGeral = media; }

    @Override  // garante que estamos realmente sobrescrevendo
    void apresentar() {
        super.apresentar(); // reutiliza o apresentar() de Pessoa
        IO.println("Matricula: " + matricula);
        IO.println("Curso:     " + curso);
        IO.println("Media:     " + mediaGeral);
    }
}
