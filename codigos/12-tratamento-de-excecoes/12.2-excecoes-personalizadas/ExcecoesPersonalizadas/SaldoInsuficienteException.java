// Arquivo: SaldoInsuficienteException.java

class SaldoInsuficienteException extends Exception {
    private double saldoAtual;
    private double valorSolicitado;

    SaldoInsuficienteException(double saldo, double solicitado) {
        super(String.format(
            "Saldo insuficiente. Atual: R$ %.2f | Solicitado: R$ %.2f",
            saldo, solicitado
        ));
        this.saldoAtual      = saldo;
        this.valorSolicitado = solicitado;
    }

    double getSaldo()      { return saldoAtual; }
    double getSolicitado() { return valorSolicitado; }
}
