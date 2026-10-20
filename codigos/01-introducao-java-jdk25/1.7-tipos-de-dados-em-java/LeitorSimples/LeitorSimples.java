// Arquivo: LeitorSimples.java
// JDK 25 compact source file — sem import nenhum!

void main() {
    IO.println("Qual o seu nome?");
    var nome = IO.readln();        // le uma linha do teclado

    IO.println("Qual a sua cidade?");
    var cidade = IO.readln();

    IO.println("Ola, " + nome + " de " + cidade + "!");
}
