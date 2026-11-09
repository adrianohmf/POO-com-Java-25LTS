// Arquivo: ArrayDeObjetos.java

void main() {
    // Um array pode guardar objetos (aqui, records)
    AlunoNota[] turma = {
        new AlunoNota("Ana", 8.5),
        new AlunoNota("Bruno", 5.5),
        new AlunoNota("Carla", 9.2),
        new AlunoNota("Diego", 6.8)
    };

    AlunoNota melhor = turma[0];
    int aprovados = 0;
    double soma = 0;

    for (int i = 0; i < turma.length; i++) {
        AlunoNota aluno = turma[i];
        System.out.format("%-6s %.1f %s%n", aluno.nome(), aluno.media(),
                aluno.aprovado() ? "Aprovado" : "Reprovado");

        soma += aluno.media();
        if (aluno.aprovado()) {
            aprovados++;
        }
        if (aluno.media() > melhor.media()) {
            melhor = aluno;
        }
    }

    System.out.format("Media da turma: %.2f%n", soma / turma.length);
    IO.println("Aprovados: " + aprovados + " de " + turma.length);
    IO.println("Melhor media: " + melhor.nome());

    // Posições ainda não preenchidas de um array de objetos valem null
    AlunoNota[] vagas = new AlunoNota[2];
    IO.println(vagas[0]);   // null
}
