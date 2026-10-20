// Arquivo: Professor.java

class Professor extends Funcionario {
    private int    aulasPorSemana;
    private static final double VALOR_AULA = 50.0;

    Professor(String nome, double salarioBase, int aulasPorSemana) {
        super(nome, salarioBase);
        this.aulasPorSemana = aulasPorSemana;
    }

    @Override
    double calcularSalario() {
        return salarioBase + (aulasPorSemana * VALOR_AULA * 4);
    }

    @Override
    String getCargo() { return "Professor EBTT"; }
}
