// Arquivo: FormatandoSaida.java

void main() {

    // BÁSICO: substituir valores em uma string formatada
    String nome  = "Maria";
    int    idade = 20;
    double nota  = 8.756;

    // System.out.format — semelhante ao printf do C
    System.out.format("Aluno: %s%n", nome);
    System.out.format("Idade: %d anos%n", idade);
    System.out.format("Nota:  %.2f%n", nota);     // 8.76 (arredonda!)

    // Múltiplos valores na mesma linha
    System.out.format("Aluno: %s | Idade: %d | Nota: %.1f%n", nome, idade, nota);

    // TABELA ALINHADA — útil para relatórios no terminal
    System.out.format("%-15s %5s %8s%n", "Nome", "Idade", "Nota");
    System.out.format("%-15s %5d %8.2f%n", "Maria Silva",  20, 8.75);
    System.out.format("%-15s %5d %8.2f%n", "Joao Santos",  22, 7.30);
    System.out.format("%-15s %5d %8.2f%n", "Ana Lima",     19, 9.50);

    // NÚMERO COM ZEROS A ESQUERDA — código de matrícula
    int numero = 42;
    System.out.format("Matricula: 2025-%04d%n", numero); // 2025-0042

    // VALOR MONETÁRIO
    double preco = 1234.5;
    System.out.format("Preco: R$ %,.2f%n", preco); // R$ 1.234,50 (locale BR)

    // String.format — cria string formatada sem imprimir
    String linha = String.format("%-15s | %5.1f", nome, nota);
    IO.println(linha);  // pode usar com IO.println também

    // System.out.printf — idêntico ao format
    System.out.printf("Usando printf: %s tem %.1f%n", nome, nota);
}
