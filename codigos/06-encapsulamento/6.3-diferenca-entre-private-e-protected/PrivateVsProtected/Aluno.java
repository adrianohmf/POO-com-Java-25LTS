// Arquivo: Aluno.java

class Aluno extends Pessoa {
    Aluno(String nome, String cpf) {
        super(nome, cpf);
    }

    void exibir() {
        IO.println(nome);   // OK: protected e visível na subclasse
        // IO.println(cpf); // ERRO: cpf e private em Pessoa, Aluno não acessa
    }
}
