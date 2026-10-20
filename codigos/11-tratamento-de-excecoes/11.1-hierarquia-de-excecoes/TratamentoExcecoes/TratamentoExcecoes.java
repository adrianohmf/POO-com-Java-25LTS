// Arquivo: TratamentoExcecoes.java

import java.util.Scanner;

void main() {
    var sc = new Scanner(System.in);

    try {
        IO.println("Digite um numero inteiro:");
        int num      = sc.nextInt();
        int resultado = 100 / num;
        IO.println("100 / " + num + " = " + resultado);

    } catch (ArithmeticException e) {
        // Captura divisão por zero
        IO.println("Erro: divisao por zero!");
        IO.println("Detalhe: " + e.getMessage());

    } catch (Exception e) {
        // Captura qualquer outro erro
        IO.println("Erro inesperado: " + e.getMessage());

    } finally {
        // Executa SEMPRE, com ou sem erro
        IO.println("Operacao finalizada.");
        sc.close();
    }
}
