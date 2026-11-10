// Arquivo: UsandoArrayList.java

import java.util.ArrayList;
import java.util.Collections;

void main() {

    var nomes = new ArrayList<String>();

    // Adicionando
    nomes.add("Maria");
    nomes.add("João");
    nomes.add("Ana");
    nomes.add("Pedro");

    IO.println("Lista: "    + nomes);
    IO.println("Tamanho: "  + nomes.size());
    IO.println("Primeiro: " + nomes.get(0));
    IO.println("Tem Ana? "  + nomes.contains("Ana"));

    // Removendo
    nomes.remove("João");   // por valor
    nomes.remove(0);        // por índice (remove Maria)

    // Percorrendo
    for (String nome : nomes) {
        IO.println("  * " + nome);
    }

    // Ordenando
    Collections.sort(nomes);
    IO.println("Ordenados: " + nomes);

    // ArrayList de objetos
    var alunos = new ArrayList<Aluno>();
    alunos.add(new Aluno("Maria", 20, "Informática"));
    alunos.add(new Aluno("João", 22, "Sistemas"));

    IO.println("Total: " + alunos.size());
    for (Aluno a : alunos) {
        a.apresentar();
    }
}
