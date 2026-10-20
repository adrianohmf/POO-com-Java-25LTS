// Arquivo: Conta.java

class Conta {
    private String titular;
    private double saldo;

    Conta(String titular, double saldo) {
        this.titular = titular;
        this.saldo   = saldo;
    }

    void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0)    throw new IllegalArgumentException("Valor invalido: " + valor);
        if (valor > saldo) throw new SaldoInsuficienteException(saldo, valor);
        saldo -= valor;
        IO.println("Saque de R$ " + valor + " realizado.");
    }

    double getSaldo() { return saldo; }
}
