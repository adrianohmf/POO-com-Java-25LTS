// Arquivo: MainPessoa.java
// Programa principal adicionado para executar o exemplo desta seção.
// Para executar: java MainPessoa.java

void main() {
    var a1 = new Aluno("Ana Souza", "123.456.789-00");
    a1.exibir();

    // IO.println(a1.cpf);  // ERRO: cpf é private em Pessoa
}
