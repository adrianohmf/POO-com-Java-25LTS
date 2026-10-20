// Arquivo: Diretor.java

class Diretor extends Funcionario {
    private double gratificacao;

    Diretor(String nome, double salarioBase, double gratificacao) {
        super(nome, salarioBase);
        this.gratificacao = gratificacao;
    }

    @Override
    double calcularSalario() { return salarioBase + gratificacao; }

    @Override
    String getCargo() { return "Diretor"; }
}
