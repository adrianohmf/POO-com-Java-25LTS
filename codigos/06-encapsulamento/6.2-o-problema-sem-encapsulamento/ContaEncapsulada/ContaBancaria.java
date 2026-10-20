// Arquivo: ContaBancaria.java

class ContaBancaria {

    // ATRIBUTOS PRIVADOS: acessíveis SOMENTE dentro desta classe
    private String titular;
    private double saldo;

    ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo   = saldoInicial;
    }

    // GETTERS: métodos de leitura
    public String getTitular() { return titular; }
    public double getSaldo()   { return saldo; }

    // MÉTODOS DE NEGÓCIO: a única forma de alterar o saldo
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        } else {
            IO.println("Valor invalido.");
        }
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            IO.println("Valor invalido.");
        } else if (valor > saldo) {
            IO.println("Saldo insuficiente.");
        } else {
            saldo -= valor;
        }
    }
}
