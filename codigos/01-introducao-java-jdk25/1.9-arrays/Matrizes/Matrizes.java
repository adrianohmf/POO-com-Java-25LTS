// Arquivo: Matrizes.java

void main() {
    // Matriz: array de arrays. Cada linha é um aluno; cada coluna, uma prova.
    double[][] notas = {
        {7.0, 8.0},
        {5.0, 6.5},
        {9.0, 10.0}
    };

    for (int linha = 0; linha < notas.length; linha++) {
        double soma = 0;
        for (int coluna = 0; coluna < notas[linha].length; coluna++) {
            soma += notas[linha][coluna];
        }
        System.out.format("Aluno %d: media %.2f%n", linha + 1, soma / notas[linha].length);
    }

    IO.println("Linhas: " + notas.length);
    IO.println("Colunas: " + notas[0].length);
}
