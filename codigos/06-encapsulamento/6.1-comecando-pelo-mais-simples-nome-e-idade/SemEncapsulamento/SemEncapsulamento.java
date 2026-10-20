// Arquivo: SemEncapsulamento.java

void main() {
    var p = new PessoaSemEncapsulamento();

    // A idade é atribuída direto no atributo, sem nenhuma verificação
    p.nome  = IO.readln("Digite o nome: ");
    p.idade = Integer.parseInt(IO.readln("Digite a idade: "));

    IO.println("Nome: " + p.nome);
    IO.println("Idade: " + p.idade);
}
