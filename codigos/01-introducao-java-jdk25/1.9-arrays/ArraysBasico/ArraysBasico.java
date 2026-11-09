// Arquivo: ArraysBasico.java

import java.util.Arrays;

void main() {
    // Criando com tamanho fixo: cada posição recebe o valor padrão (0 para int)
    int[] vagas = new int[3];
    IO.println(Arrays.toString(vagas));            // [0, 0, 0]

    // Criando já com os valores
    double[] notas = {7.5, 8.0, 6.5, 9.0};
    IO.println("Quantidade de notas: " + notas.length);   // length é atributo: sem parênteses
    IO.println("Primeira nota: " + notas[0]);
    IO.println("Ultima nota: " + notas[notas.length - 1]);

    // Alterando uma posição
    notas[2] = 7.0;

    // for com índice: use quando precisar da posição
    double soma = 0;
    for (int i = 0; i < notas.length; i++) {
        System.out.format("Nota %d: %.1f%n", i + 1, notas[i]);
        soma += notas[i];
    }
    IO.println("Media: " + soma / notas.length);

    // for-each: use quando só precisar dos valores
    double maior = notas[0];
    for (double nota : notas) {
        if (nota > maior) {
            maior = nota;
        }
    }
    IO.println("Maior nota: " + maior);

    // Utilitários da classe Arrays
    String[] nomes = {"Maria", "Joao", "Ana", "Pedro"};
    Arrays.sort(nomes);
    IO.println(Arrays.toString(nomes));            // [Ana, Joao, Maria, Pedro]
}
