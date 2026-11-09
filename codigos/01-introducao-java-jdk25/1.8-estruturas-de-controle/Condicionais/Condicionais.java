// Arquivo: Condicionais.java

void main() {
    double media = 6.2;
    int faltas = 4;
    int totalAulas = 40;

    // if / else if / else: executa apenas o primeiro bloco cuja condição for verdadeira
    if (media >= 7.0) {
        IO.println("Aprovado por nota");
    } else if (media >= 5.0) {
        IO.println("Em recuperacao");
    } else {
        IO.println("Reprovado por nota");
    }

    // Operadores lógicos combinam condições: && (E), || (OU), ! (NÃO)
    double frequencia = 100.0 * (totalAulas - faltas) / totalAulas;
    System.out.format("Frequencia: %.1f%%%n", frequencia);

    if (media >= 7.0 && frequencia >= 75.0) {
        IO.println("Situacao final: Aprovado");
    } else if (frequencia < 75.0) {
        IO.println("Situacao final: Reprovado por falta");
    } else {
        IO.println("Situacao final: Precisa de recuperacao");
    }

    // if sem chaves só vale para UMA instrução (evite: gera erros)
    if (media > 9.0) IO.println("Excelente!");

    // Operador ternário: forma curta de um if/else que devolve um valor
    String resultado = (media >= 5.0) ? "Segue na disciplina" : "Refaz a disciplina";
    IO.println(resultado);
}
