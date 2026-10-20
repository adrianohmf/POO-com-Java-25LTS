// Arquivo: ProgramaAluno.java

void main() {

    // "new" cria o objeto na memória
    // Tipo variável = new Tipo();
    Aluno a1 = new Aluno();
    Aluno a2 = new Aluno();   // objeto independente do a1

    // Atribuindo valores: objeto.atributo = valor;
    a1.nome  = "Maria Silva";
    a1.idade = 20;
    a1.curso = "Informatica para Internet";

    a2.nome  = "Joao Santos";
    a2.idade = 22;
    a2.curso = "Sistemas para Internet";

    // Chamando métodos: objeto.método();
    IO.println("=== Aluno 1 ===");
    a1.apresentar();
    IO.println("Situacao: " + a1.getSituacao());

    IO.println("=== Aluno 2 ===");
    a2.apresentar();
}
