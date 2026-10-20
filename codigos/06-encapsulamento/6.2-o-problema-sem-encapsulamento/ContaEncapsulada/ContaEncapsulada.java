// Arquivo: ContaEncapsulada.java

void main() {
    var c1 = new ContaBancaria("Maria Silva", 500.00);
    IO.println("Saldo inicial: R$ " + c1.getSaldo());

    c1.depositar(300.00);
    c1.sacar(100.00);
    c1.sacar(1000.00);  // bloqueado!

    // c1.saldo = -999; // ERRO de compilação: saldo é private

    IO.println("Saldo atual: R$ " + c1.getSaldo());
}
