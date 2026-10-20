// Arquivo: Pessoa.java

class Pessoa {
    private String cpf;      // SÓ Pessoa acessa
    protected String nome;   // Pessoa E suas subclasses acessam

    Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf  = cpf;
    }
}
