// Arquivo: MainAluno.java
// Programa principal adicionado para executar a classe Aluno desta seção.
// Para executar: java MainAluno.java

void main() {
    var a = new Aluno();
    a.nome  = "Maria";
    a.idade = 20;
    a.curso = "Informatica";

    a.apresentar();
    IO.println("Situacao: " + a.getSituacao());
}
