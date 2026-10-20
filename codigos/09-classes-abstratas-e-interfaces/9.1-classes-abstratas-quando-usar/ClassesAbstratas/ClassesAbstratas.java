// Arquivo: ClassesAbstratas.java

void main() {
    // Funcionario f = new Funcionario(...); // ERRO: não pode instanciar!

    Funcionario[] equipe = {
        new Professor("Adriano Franca", 4000.00, 20),
        new Diretor("Ana Souza", 6000.00, 2000.00),
        new Professor("Carlos Lima", 3800.00, 18)
    };

    for (Funcionario f : equipe) {
        f.exibirContracheque(); // polimorfismo!
    }
}
