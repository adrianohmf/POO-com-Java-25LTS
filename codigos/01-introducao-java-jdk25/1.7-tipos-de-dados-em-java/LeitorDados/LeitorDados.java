// Arquivo: LeitorDados.java
// JDK 25 compact source file: import não necessário aqui
// Em arquivos com public class, adicione: import java.útil.Scanner;

void main() {

    // Cria o Scanner ligado ao teclado (System.in)
    var sc = new Scanner(System.in);

    // Lendo texto
    IO.println("Qual o seu nome?");
    var nome = sc.nextLine();    // le a linha inteira

    // Lendo inteiro
    IO.println("Qual a sua idade?");
    var idade = sc.nextInt();    // le apenas o número

    // Lendo decimal
    IO.println("Qual a sua nota final?");
    var nota = sc.nextDouble();  // le número com casas decimais

    // sc.nextLine() após nextInt/nextDouble: limpa o buffer
    // (explicado abaixo)
    sc.nextLine();

    // Exibindo os dados lidos
    System.out.format("%n=== Dados do Aluno ===%n");
    System.out.format("Nome:  %s%n", nome);
    System.out.format("Idade: %d anos%n", idade);
    System.out.format("Nota:  %.2f%n", nota);

    var situacao = nota >= 7.0 ? "Aprovado" : "Reprovado";
    System.out.format("Situacao: %s%n", situacao);

    // Boa prática: fechar o Scanner ao terminar
    sc.close();
}
