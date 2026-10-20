// Arquivo: Funcionario.java

abstract class Funcionario {
    protected String nome;
    protected double salarioBase;

    Funcionario(String nome, double salarioBase) {
        this.nome        = nome;
        this.salarioBase = salarioBase;
    }

    // Método abstrato: obriga subclasses a implementar
    abstract double calcularSalario();
    abstract String getCargo();

    // Método concreto: compartilhado por todos
    void exibirContracheque() {
        IO.println("---");
        IO.println("FUNCIONARIO: " + nome);
        IO.println("CARGO:       " + getCargo());
        IO.println("SALARIO:     R$ " + String.format("%.2f", calcularSalario()));
        IO.println("---");
    }
}
