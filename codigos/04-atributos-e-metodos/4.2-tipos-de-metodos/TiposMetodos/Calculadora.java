// Arquivo: Calculadora.java

class Calculadora {

    // MÉTODO VOID: executa ação, não retorna valor
    void exibirSoma(int a, int b) {
        IO.println("Soma: " + (a + b));
    }

    // MÉTODO COM RETORNO: declara o tipo antes do nome
    int somar(int a, int b) {
        return a + b;
    }

    double calcularMedia(double[] notas) {
        double soma = 0;
        for (double nota : notas) soma += nota;
        return soma / notas.length;
    }

    String classificar(double media) {
        if (media >= 7.0)      return "Aprovado";
        else if (media >= 5.0) return "Recuperacao";
        else                   return "Reprovado";
    }

    // MÉTODO ESTÁTICO: chamado pela classe, sem criar objeto
    static double calcularImposto(double valor, double taxa) {
        return valor * taxa;
    }
}
