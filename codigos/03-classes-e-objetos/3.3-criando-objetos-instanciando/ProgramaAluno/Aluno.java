// Arquivo: Aluno.java
// Mesma classe criada na seção 3.2, copiada aqui para este exemplo rodar sozinho.

class Aluno {

    // ATRIBUTOS: características do objeto
    String nome;
    int    idade;
    String curso;

    // MÉTODO void: executa uma ação, não retorna nada
    void apresentar() {
        IO.println("Nome:  " + nome);
        IO.println("Idade: " + idade);
        IO.println("Curso: " + curso);
    }

    // MÉTODO com retorno: devolve um valor a quem chamou
    String getSituacao() {
        return idade >= 18 ? "Maior de idade" : "Menor de idade";
    }
}
