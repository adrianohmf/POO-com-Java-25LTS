// Arquivo: Pessoa.java

// SUPERCLASSE
class Pessoa {
    protected String nome;   // protected: acessível nas subclasses
    protected int    idade;
    protected String cpf;

    Pessoa(String nome, int idade, String cpf) {
        this.nome  = nome;
        this.idade = idade;
        this.cpf   = cpf;
    }

    void apresentar() {
        IO.println("Nome:  " + nome);
        IO.println("Idade: " + idade);
        IO.println("CPF:   " + cpf);
    }

    String getNome() { return nome; }
}
