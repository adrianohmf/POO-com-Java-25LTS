// Arquivo: MainAluno.java
// Programa principal adicionado para executar a classe Aluno desta seção.
// Para executar: java MainAluno.java

void main() {
    var a = new Aluno();
    a.nome  = "Maria";
    a.idade = 20;
    a.curso = "Informática";

    a.apresentar();
    IO.println("Situação: " + a.getSituacao());
}
