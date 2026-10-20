// Arquivo: PessoaComEncapsulamento.java

class PessoaComEncapsulamento {
    private String nome;   // private: só esta classe acessa
    private int idade;

    // GETTERS: leitura
    public String getNome() { return nome; }
    public int getIdade()   { return idade; }

    // SETTERS: escrita
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        if (idade >= 0) {
            this.idade = idade;               // idade válida: guarda
        } else {
            IO.println("Idade invalida.");    // idade negativa: recusa
        }
    }
}
