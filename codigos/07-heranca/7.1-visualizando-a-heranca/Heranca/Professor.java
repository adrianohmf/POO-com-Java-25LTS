// Arquivo: Professor.java

class Professor extends Pessoa {
    private String disciplina;
    private String registro;

    Professor(String nome, int idade, String cpf,
              String disciplina, String registro) {
        super(nome, idade, cpf);
        this.disciplina = disciplina;
        this.registro   = registro;
    }

    @Override
    void apresentar() {
        super.apresentar();
        IO.println("Disciplina: " + disciplina);
        IO.println("Registro:   " + registro);
    }

    void ministrarAula() {
        IO.println(nome + " esta ministrando " + disciplina + ".");
    }
}
