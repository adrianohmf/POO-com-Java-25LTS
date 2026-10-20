// Arquivo: ComEncapsulamento.java

void main() {
    var p = new PessoaComEncapsulamento();

    // Agora a idade passa pelo setter, que faz a validação
    p.setNome(IO.readln("Digite o nome: "));
    p.setIdade(Integer.parseInt(IO.readln("Digite a idade: ")));

    IO.println("Nome: " + p.getNome());
    IO.println("Idade: " + p.getIdade());

    // p.idade = -5;  // ERRO de compilação: idade é private
}
