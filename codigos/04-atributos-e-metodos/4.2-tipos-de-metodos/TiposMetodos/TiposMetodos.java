// Arquivo: TiposMetodos.java

void main() {
    var calc = new Calculadora();

    calc.exibirSoma(10, 5);

    int resultado = calc.somar(10, 5);
    IO.println("Resultado: " + resultado);

    double[] notas = { 8.0, 7.5, 9.0, 6.5 };
    double media = calc.calcularMedia(notas);
    IO.println("Media:    " + media);
    IO.println("Situacao: " + calc.classificar(media));

    // Método estático: sem criar objeto
    IO.println("Imposto: R$ " + Calculadora.calcularImposto(100.0, 0.12));
}
