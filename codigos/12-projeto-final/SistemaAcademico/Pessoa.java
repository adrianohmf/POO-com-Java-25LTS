// Arquivo: Pessoa.java

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
