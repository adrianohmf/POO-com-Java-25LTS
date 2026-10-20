// Arquivo: ExcecoesPersonalizadas.java

void main() {
    var conta = new Conta("Maria", 500.00);

    double[] tentativas = { 200.00, -50.00, 1000.00, 300.00 };

    for (double valor : tentativas) {
        try {
            IO.println("Tentando sacar R$ " + valor + "...");
            conta.sacar(valor);
            IO.println("Saldo: R$ " + conta.getSaldo());
        } catch (SaldoInsuficienteException e) {
            IO.println("Erro: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            IO.println("Aviso: " + e.getMessage());
        }
    }
}
